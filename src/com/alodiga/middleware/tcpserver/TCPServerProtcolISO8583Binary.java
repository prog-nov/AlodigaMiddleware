package com.alodiga.middleware.tcpserver;

import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;
import com.alodiga.middleware.cscoreswitch.AdminConcurrentMap;
import com.alodiga.middleware.cscoreswitch.ContainerIsoQueue;
import com.alodiga.middleware.cscoreswitch.Iso8583Binary;
import com.alodiga.middleware.cscoreswitch.ResponseMsgIsoBinary;
import com.alodiga.middleware.queueadmin.Queue;
import com.alodiga.middleware.queueadmin.typeMessage;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.temporal.cache.MemoryGlobal;

public class TCPServerProtcolISO8583Binary extends IoHandlerAdapter {

	private class SecuencialClass {
		private String Sec;

		public String getSec() {
			return Sec;
		}

		public void setSec(String sec) {
			Sec = sec;
		}
	}

	public TCPServerProtcolISO8583Binary() {
		super();
	}

	@Override
	public void sessionCreated(IoSession session) {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		session.setAttribute(SslFilter.USE_NOTIFICATION);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("SESSION CLOSED: " + session.getRemoteAddress().toString());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("SESSION OPENED " + session.toString());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		System.out.println("En espera de Transacciones KKKKKKKK, Count: " + session.getIdleCount(IdleStatus.BOTH_IDLE)
				+ " [Espera.....]");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.println("Error!!!! " + cause.getMessage());
		session.close(true);
	}

	@SuppressWarnings("static-access")
	@Override
	public void messageReceived(IoSession session, Object message) {
		System.out.println("Object message = " + message.toString());
		org.apache.mina.core.buffer.IoBuffer data = null;
		try {
			ResponseMsgIsoBinary res = new ResponseMsgIsoBinary();
			ContainerIsoQueue<Object> cont = null;
			IoBuffer buffer = null;
			String mensajeIso = null;
			byte[] b = null;
			SecuencialClass secu = new SecuencialClass();
			if (message instanceof IoBuffer) {
				buffer = (IoBuffer) message;
				b = new byte[buffer.remaining()];
				buffer.get(b);
				buffer.flip();
				mensajeIso = new String(b);
				System.out.println("mensaje en crudo :" + mensajeIso);

				// byte[] bufferTrama = processMessageWithHeader(b);
				System.out.println("Mensaje ISO Procesado");
				ISOMsg isoMsg = parseISOMessage(mensajeIso);
				printISOMessage(isoMsg);

				if (isoMsg.getString(39).equals("96")) {
					secu.setSec(isoMsg.getString(39));
					cont = new ContainerIsoQueue<>(isoMsg, getIpAddresClient(session));
					cont.setSecuencial(secu.getSec());
					System.out.println("crea secuencual" + secu.getSec());
					Queue queue = new Queue();
					queue.SendMessage(typeMessage.initialMessage, cont, 1, 1, 0);
					final CountDownLatch semaphore = new CountDownLatch(1);
					System.out.println("Secuencial: " + secu.getSec());
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							while (true) {

								synchronized (MemoryGlobal.concurrentIso) {

									if (MemoryGlobal.concurrentIso.containsKey(secu.getSec())) {
										System.out.println("Mensaje");
										ISOMsg isoMsgResponse = new ISOMsg();
										isoMsgResponse = (ISOMsg) MemoryGlobal.concurrentIso.get(secu.getSec());
										InputStream is = getClass().getResourceAsStream("/fields.xml");
										try {
											isoMsgResponse.setMTI("0210");
											ISOPackager packager = new GenericPackager(is);
											isoMsgResponse.setPackager(packager);
											byte b[] = isoMsgResponse.pack();
											String s = new String(b);
											org.apache.mina.core.buffer.IoBuffer data2 = null;
											byte[] response = s.getBytes();
											data2 = DataSend(response);
											data2.flip();
											session.write(data2);
											session.write(data2);
										} catch (ISOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										@SuppressWarnings("unused")
										AdminConcurrentMap map = new AdminConcurrentMap(secu.getSec());
										semaphore.countDown();
										System.out.println("semaphore.countDown();");
										break;
									}

								}
							}
						}
					});
					t.start();
					if (!semaphore.await(50000, TimeUnit.MILLISECONDS))
						res.setIsoBin(new Iso8583Binary().GenericError());

//						Iso8583Binary binary = new Iso8583Binary();
//						byte[] respuesta = binary.getIso8583BinaryResponse(res.getIsoBin());
//						data = DataSend(respuesta);
//		            	data.flip();
//		            	session.write(data);

				} else {

					Iso8583Binary bin = new Iso8583Binary();
					// bin = isoBinary.GenericError();
					byte[] responseError = bin.getIso8583BinaryResponse(bin);
					data = DataSend(responseError);
					data.flip();
					session.write(data);
					session.write(data);
				}
			}

		} catch (Exception e) {

			System.out.println("Error modulo: " + this.getClass().getName() + "::messageReceived"
					+ GeneralUtils.ExceptionToString(null, e));
		}
	}

	protected ISOMsg parseISOMessage(String msg) throws Exception {
		System.out.println(msg);
		try {
			// Load package from resources directory.
			InputStream is = getClass().getResourceAsStream("/fields.xml");
			System.out.println("paso");
			GenericPackager packager = new GenericPackager(is);
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.unpack(msg.getBytes());
			return isoMsg;
		} catch (ISOException e) {
			throw new Exception(e);
		}
	}

	protected void printISOMessage(ISOMsg isoMsg) {
		try {
			System.out.println("MTI = " + isoMsg.getMTI());

			for (int i = 1; i <= isoMsg.getMaxField(); i++) {
				if (isoMsg.hasField(i)) {
					System.out.println("Field (" + i + ")" + isoMsg.getString(i));
				}
			}
			System.out.println(isoMsg.toString());
		} catch (ISOException e) {
			e.printStackTrace();
		}
	}

	protected byte[] processMessageWithHeader(byte[] msg) {
		// 02003220000000808000000010000000001500120604120000000112340001840
		System.out.println("paso por aqui");
		System.out.println("msg = " + Arrays.toString(msg));
		byte[] message = null;
		try {

			byte[] bytes;
			System.out.println(msg.toString());
			bytes = msg;
			System.out.println("Llego total: " + bytes.length);
			int a = bytes[0];
			System.out.println("Byte A: " + a);
			int b;
			System.out.println("bytes[1] = " + bytes[1]);
			if (bytes[1] < 0)
				b = 256 - Math.abs(bytes[1]);
			else
				b = bytes[1];
			System.out.println("Byte B: " + b);
			int longitudTrama = a;
			System.out.println("Total lonfitud trama: " + longitudTrama);
			byte[] buffer = new byte[longitudTrama + 2];
			System.out.println("bytes = " + Arrays.toString(bytes));
			System.out.println("longitud = " + bytes.length);
			System.out.println("buffer = " + Arrays.toString(buffer));
			System.out.println("longitud = " + bytes.length);
			System.arraycopy(bytes, 2, buffer, 0, longitudTrama);
			System.out.println("arraycopy");
			message = buffer;
			System.out.println("message = " + Arrays.toString(message));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return message;
	}

	protected String getIpAddresClient(IoSession session) {

		try {

			String ipAddress = session.getRemoteAddress().toString();
			ipAddress = Arrays.asList(ipAddress.split(":")).get(0).substring(1);
			return ipAddress;

		} catch (Exception e) {

			System.out.println("Error modulo: " + this.getClass().getName() + "::getIpAddresClient"
					+ GeneralUtils.ExceptionToString(null, e));
			return "127.0.0.1";
		}

	}

	private org.apache.mina.core.buffer.IoBuffer DataSend(byte[] messageIso) {

		org.apache.mina.core.buffer.IoBuffer data = null;
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				GeneralUtils.setterLoggerBinary(messageIso);
			}
		});
		th.start();
		try {
			byte[] tramaTotal = new byte[messageIso.length + 2];
			data = org.apache.mina.core.buffer.IoBuffer.allocate(tramaTotal.length + 2);
			System.arraycopy(messageIso, 0, tramaTotal, 2, messageIso.length);
			data.put(tramaTotal);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	private static org.apache.mina.core.buffer.IoBuffer DataSendString(String messageIso) {
		org.apache.mina.core.buffer.IoBuffer data = null;
		try {
			data = org.apache.mina.core.buffer.IoBuffer.allocate(messageIso.length());
			data.put(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
}
