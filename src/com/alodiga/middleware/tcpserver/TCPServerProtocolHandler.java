package com.alodiga.middleware.tcpserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;

import com.alodiga.middleware.asextreme.ExtremeRequest;
import com.alodiga.middleware.cscoreswitch.AdminConcurrentMap;
import com.alodiga.middleware.cscoreswitch.ContainerIsoQueue;
import com.alodiga.middleware.cscoreswitch.Response;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeLog;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.logger.LoggerConfig.TypeWriteLog;
import com.alodiga.middleware.queueadmin.Queue;
import com.alodiga.middleware.queueadmin.typeMessage;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.StringUtils;
import com.alodiga.temporal.cache.MemoryGlobal;


public class TCPServerProtocolHandler extends IoHandlerAdapter{
	
	private Logger log;
	private boolean flagError;
	private String causeError;
	private class SecuencialClass{ private String Sec;

		public String getSec() {
			return Sec;
		}
		public void setSec(String sec) {
			Sec = sec;
		}  
    }
	
	public TCPServerProtocolHandler() {
		
		log = new Logger();
		this.flagError = false;
		this.causeError = "PROCESO EXITOSO";
	}

	@Override
	  public void sessionCreated(IoSession session) {
	      session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	      session.setAttribute(SslFilter.USE_NOTIFICATION);
	      
	  }

	  @Override
	  public void sessionClosed(IoSession session) throws Exception {
		  System.out.println("SESSION CLOSED: "  +  session.getRemoteAddress().toString());
		  log.WriteLogMonitor("SESSION CLOSED: "  +  session.getRemoteAddress().toString(), TypeMonitor.monitor, null);
	  }

	  @Override
	  public void sessionOpened(IoSession session) throws Exception {
		  
		  log.WriteLogMonitor("SESSION OPENED " + session.toString(), TypeMonitor.monitor, null);
		  System.out.println("SESSION OPENED " + session.toString());
	  }

	  @Override
	  public void sessionIdle(IoSession session, IdleStatus status) {
		  System.out.println("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
	  }

	  @SuppressWarnings("deprecation")
	  @Override
	  public void exceptionCaught(IoSession session, Throwable cause) {
		  System.out.println("Error!!!! " + cause.getMessage());
	      session.close(true);
	  }

	  @Override
	  public void messageReceived(IoSession session, Object message) {
		  
		  org.apache.mina.core.buffer.IoBuffer data = null;
		  log.WriteLogMonitor("MENSAJE RECIBIDO IP: " + session.getRemoteAddress().toString(), TypeMonitor.monitor, null);

			  
			  System.out.println(message.getClass());
			  System.out.println( "Received : " + message );
			  IoBuffer buffer = null;
			  byte[] b = null;
			  Response res = new Response();
			  ContainerIsoQueue<Object> cont = null;
			  
			  if (message instanceof IoBuffer) {
				  
				  
				    SecuencialClass secu = new SecuencialClass();
	                buffer = (IoBuffer) message;
	                b = new byte[buffer.remaining()];
	                buffer.get(b);
	                buffer.flip();
	                String mensajeDepurado = processMessageWithHeader(b);
	                System.out.println("---------------- " + mensajeDepurado);
	                
//	                if(!this.flagError){
	                	
//		              	log.WriteLog(mensajeDepurado, TypeLog.alexsoft, TypeWriteLog.file);
//		          		ExtremeRequest messageReq = (ExtremeRequest) 
//				    			       SerializationObject.StringToObject(mensajeDepurado);
		          		
//		          		if(messageReq != null){
//				    		
//				    		HField sec1 = messageReq.getHeader().getHfield().stream()
//				    			    .filter(a -> a.getName().equals("TraceId"))
//				    			    .findFirst().orElseGet(() -> null);
//				    		
//					    	HField sec = messageReq.getHeader().getHfield().stream()
//					    			    .filter(a -> a.getName().equals("TerminalSeq"))
//					    			    .findFirst().orElseGet(() -> null);
//					    	
//					    	if(sec != null && sec1 != null){
//					    		
//					    		cont = new ContainerIsoQueue<>(messageReq , getIpAddresClient(session));
//						    	cont.setSecuencial(sec1.getValue() + "_" + sec.getValue());
//						    	secu.setSec(sec1.getValue() + "_" + sec.getValue());
//						    	
//						    	
//						    	Queue queue = new Queue();
//								queue.SendMessage(typeMessage.initialMessage, cont, 1, 1, 0);
//
//								final CountDownLatch semaphore = new CountDownLatch(1);
//								Thread t = new Thread(new Runnable() {
//									@Override
//									public void run() {
//										
//										while (true) {
//											synchronized(MemoryGlobal.concurrentIso) {
//												if(MemoryGlobal.concurrentIso.containsKey(secu.getSec())){
//													res.setMessResponse((String) MemoryGlobal.concurrentIso.get(secu.getSec()));
//													@SuppressWarnings("unused")
//													AdminConcurrentMap map = new AdminConcurrentMap(secu.getSec());
//													semaphore.countDown();
//													break;
//												}
//											}
//										}
//									}
//								});
//								t.start();
//				            	
//								if(!semaphore.await(50000, TimeUnit.MILLISECONDS))
//									res.setMessResponse("909TIEMPO EXPIRADO");
//								
//								//Respuesta correcta final
//								data = DataSend(res.getMessResponse());
//				            	data.flip();
//				            	session.write(data);
//				            	
//						    	
//						    	
//					    	}else {
//					    		
//					    		data = DataSend("TRAMA INVALIDA");
//				            	data.flip();
//				            	session.write(data);
//							}
//				    		
//				    	}else{
//				    		
//				    		data = DataSend("ERROR AL SERIALIAR/DESERIALIZAR MENSAJE ALEXSTREME");
//			            	data.flip();
//			            	session.write(data);
//				    	}
//		          		
//	                }else {
//						
//	                	data = DataSend("ERROR EN PROCESOS: " + this.causeError.toUpperCase());
//		            	data.flip();
//		            	session.write(data);
//					}
//	                
//				}else {
//					
//					log.WriteLogMonitor("==> " + message.toString(), TypeMonitor.monitor, null);
//				}
            	
//		 }catch (Exception e) {

//			this.flagError = true;
//			this.causeError = GeneralUtils.ExceptionToString(null, e);
//			data = DataSend(this.causeError);
//			data.flip();
//			session.write(data);
		 }
	
		
	}
	protected String processMessageWithHeader(byte[] msg){
	    	
	    	String message = null;
	    	try {
				
	    		byte[] bytes;
				bytes = msg;
				System.out.println("Llego total: " + bytes.length);
				int a = bytes[0];
				System.out.println("Byte A: " + a);
				int b;
				if(bytes[1] < 0)
					b = 256 - Math.abs(bytes[1]);
				else
					b = bytes[1];
				System.out.println("Byte B: " + b);
				int longitudTrama = (a * 256) + b;
				System.out.println("Total lonfitud trama: " + longitudTrama);
				byte[] buffer = new byte[longitudTrama -2];	
				System.arraycopy(bytes, 2, buffer, 0, (longitudTrama - 2));
				byte[] barr = Base64.decodeBase64(buffer);
				message = StringUtils.decompress(barr, "UTF-8").replace("??", "");
				this.flagError = false;
						  
			} catch (Exception e) {
				
				this.flagError = true;
				this.causeError = GeneralUtils.ExceptionToString(null, e);
				e.printStackTrace();
				log.WriteLogMonitor("Error modulo TCPServerProtocolHandler::processMessageHeader ", TypeMonitor.error, e);
			}
	    	return message;
	    }
	    protected byte[] processMessageReturn(String messageResponse){
	    	
	    	try {
	    		messageResponse = messageResponse.replace("&lt;**&gt;", "")
	    				.replace(" &lt;*&gt;", "");
	    		log.WriteLog(messageResponse, TypeLog.alexsoft, TypeWriteLog.file);
	    		byte[] compress = StringUtils.compress(messageResponse, "UTF-8");
	    		byte[] trama = Base64.encodeBase64(compress);
				byte[] header = new byte[]{ (byte)((trama.length + 2)/ 256), (byte) ((trama.length + 2) % 256)};
				System.out.println("Longitud respuesta texto: " + trama.length);
				System.out.println("Respuesta header respuesta A: " + header[0]);
				System.out.println("header respuesta B: " + header[1]);
				System.out.println("Respuesta header respuesta A: " + (header[1] > 0 ? header[1] : (256 - header[1])));
				byte[] totalTrama = new byte[trama.length + 2];
				System.arraycopy(header, 0, totalTrama, 0, header.length);
				System.arraycopy(trama, 0, totalTrama, 2, trama.length);	
				return totalTrama;
	    		
			} catch (Exception e) {
				log.WriteLogMonitor("Error Modulo " + this.getClass().getName() + "::processMessageReturn" , TypeMonitor.error, e);
				return null;
			}
	    }
	    
	    protected String getIpAddresClient(IoSession session){
	    
	    	try {
				
	    		String ipAddress = session.getRemoteAddress().toString();
		    	ipAddress = Arrays.asList(ipAddress.split(":")).get(0).substring(1);
		        return ipAddress;
	    		
			} catch (Exception e) {
				
				log.WriteLogMonitor("Error Modulo " + this.getClass().getName() + "::getIpAddresClient" , TypeMonitor.error, e);
				return "127.0.0.1";
			}
	    
	    }
	  
	    private org.apache.mina.core.buffer.IoBuffer DataSend(String mensaje){
			 
	    	org.apache.mina.core.buffer.IoBuffer data = null;
			 try {
				 
				  mensaje = mensaje.replace("&lt;**&gt;", "")
		    				.replace(" &lt;*&gt;", "");
		    		log.WriteLog(mensaje, TypeLog.alexsoft, TypeWriteLog.file);
					byte[] compress = StringUtils.compress(mensaje, "UTF-8");
					byte[] tramas = Base64.encodeBase64(compress);
					byte[] header = new byte[]{ (byte)((tramas.length + 2)/ 256), (byte) ((tramas.length + 2) % 256) };
					byte[] tramaTotal = new byte[tramas.length + 2];
					
					data = org.apache.mina.core.buffer.IoBuffer.allocate(tramaTotal.length + 2);
					System.arraycopy(header, 0, tramaTotal, 0, header.length);
					System.arraycopy(tramas, 0, tramaTotal, 2, tramas.length);
	         	    data.put(tramaTotal);
				
			} catch (Exception e) {
				
				log.WriteLogMonitor("Error modulo SSLServerHandler::DataSend ", TypeMonitor.error, e);
				return null;
			}
			 return data;
		 }
}
