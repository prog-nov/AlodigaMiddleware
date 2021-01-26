package com.alodiga.middleware.cscoreswitch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.queueadmin.Queue;
import com.alodiga.middleware.queueadmin.typeMessage;
import com.alodiga.temporal.cache.MemoryGlobal;


import urn.iso.std.iso20022.tech.xsd.pacs_002_001_05.DocumentRespuesta;
import urn.iso.std.iso20022.tech.xsd.pacs_003_001_04.DocumentRetiro;
import urn.iso.std.iso20022.tech.xsd.pacs_004_001_04.DocumentReverso;
import urn.iso.std.iso20022.tech.xsd.pacs_008_001_04.DocumentDeposito;
import urn.iso.std.iso20022.tech.xsd.pacs_008_001_04.DocumentTransferencia;


public class CallersAsync extends Thread {

	private ContainerIsoQueue<?> cont;
	private Message message;
	private Logger log;
	
	public CallersAsync(){
		
		log = new Logger();
	}
	
	public CallersAsync(Message message){
		
		this.message = message;
		this.cont = new ContainerIsoQueue<>();
	}
	
	public void executeTrx(){
		try {
			//1. Toma la transacción de la cola
			System.out.println("executeTrx...............Entro en callerAsy");
			ObjectMessage objectMessage = (ObjectMessage) message;	
			cont = (ContainerIsoQueue<?>) objectMessage.getObject();
			ISOMsg isoMsg = (ISOMsg) cont.getIso();
			
			switch (isoMsg.getMTI()) {
			case "0100":
				isoMsg.set(39, "00");
				printISOMessage(isoMsg);
				/////////////////////////////////
				//2.Consulta contra el CMS//////
				////////////////////////////////
				//3. Envia una respuesta
				MemoryGlobal.concurrentIso.put("96", isoMsg);
				break;
            case "0200":
            	
				
				break;
           case "0400":
				
				break;
           case "0800":
				
				break;

			default:
				break;
			}
			
			
			isoMsg.set(39, "00");
			printISOMessage(isoMsg);
			
			//2.Consulta contra el CMS
			//3. Envia una respuesta
			//Respuesta
			MemoryGlobal.concurrentIso.put("96", isoMsg);

			
//			  if(cont.getIso().getClass().equals(Iso8583.class)){
//			    System.out.println("cont.getIso().getClass().equals(Iso8583.class)");
//				Iso8583 iso = (Iso8583)cont.getIso();
//				ExecutorService pool = Executors.newFixedThreadPool(3);
//				Callable<Iso8583> exec = new csProcess(iso, cont.getIP());
//				Future<Iso8583> futureIso = pool.submit(exec);
//				Iso8583 isoRes = futureIso.get();
//			    System.out.println("MemoryGlobal.concurrentIso.put(isoRes.\r\n" + 
//			    		"						     getISO_011_SysAuditNumber(), isoRes);");
//				MemoryGlobal.concurrentIso.put(isoRes.
//						     getISO_011_SysAuditNumber(), isoRes);
//				System.out.println("Antes: " + MemoryGlobal.concurrentIso.size());
//			}else if(cont.getIso().getClass().equals(wIso8583.class)){
				
//				wIso8583 iso = (wIso8583)cont.getIso();
//				AdminProcessStoreAndForward sf = new AdminProcessStoreAndForward();
//				Thread t = new Thread(sf.ProccessStoreAndForwardThreading(iso));
//				t.start();
				
//			}else if(cont.getIso() instanceof String){
				
//			    String msg = (String)cont.getIso();
//			    CoonectaIsAcq coon = new CoonectaIsAcq();
//			    String response = coon.TransactionIso(msg);
//			    String secuencial = response.substring(55,61);
//			    //MemoryGlobal.concurrentIso.put(secuencial, response);
//			    ContainerIsoQueue<Object> cont = new ContainerIsoQueue<>(response,"127.0.0.1");
//			    cont.setSecuencial(secuencial);
//			    Queue queue = new Queue(cont.getNameQueueOrigin());
//				queue.SendMessage(typeMessage.externalMessageStation, cont, 1, 1, 0);
			    
//			}else if (cont.getIso().getClass().equals(DocumentDeposito.class)) {
				
//				DocumentDeposito documentoDeposito = (DocumentDeposito) cont.getIso();
//				DocumentRespuesta documentorespuesta = null;
//				BridgeVC_BCE bridge = new BridgeVC_BCE();
//				documentorespuesta = bridge.ProcesaDepositoVC_BCE(documentoDeposito, cont.getIP());
//				ContainerIsoQueue<DocumentRespuesta> strcuRes = new 
//						ContainerIsoQueue<DocumentRespuesta>(documentorespuesta, "127.0.0.1");
//				strcuRes.setSecuencial(bridge.getSecuencialTrx());
//				Queue queue = new Queue(cont.getNameQueueOrigin());
//				queue.SendMessage(typeMessage.externalMessageStation, strcuRes, 1, 1, 0);	
				
//			}else if (cont.getIso().getClass().equals(DocumentRetiro.class)) {
				
//				DocumentRetiro documentRetiro = (DocumentRetiro) cont.getIso();
//				DocumentRespuesta documentorespuesta = null;
//				BridgeVC_BCE bridge = new BridgeVC_BCE();
//				documentorespuesta = bridge.ProcesaRetiroVC_BCE(documentRetiro, cont.getIP());
//				ContainerIsoQueue<DocumentRespuesta> strcuRes = new 
//						ContainerIsoQueue<DocumentRespuesta>(documentorespuesta, "127.0.0.1");
//				strcuRes.setSecuencial(bridge.getSecuencialTrx());
//				Queue queue = new Queue(cont.getNameQueueOrigin());
//				queue.SendMessage(typeMessage.externalMessageStation, strcuRes, 1, 1, 0);
				
//			}else if (cont.getIso().getClass().equals(DocumentReverso.class)){
				
//				DocumentReverso documentReverso = (DocumentReverso) cont.getIso();
//				DocumentRespuesta documentorespuesta = null;
//				BridgeVC_BCE bridge = new BridgeVC_BCE();
//				documentorespuesta = bridge.ProcesarreversoVC_BCE(documentReverso, cont.getIP());
//				ContainerIsoQueue<DocumentRespuesta> strcuRes = new 
//						ContainerIsoQueue<DocumentRespuesta>(documentorespuesta, "127.0.0.1");
//				strcuRes.setSecuencial(bridge.getSecuencialTrx());
//				Queue queue = new Queue(cont.getNameQueueOrigin());
//				queue.SendMessage(typeMessage.externalMessageStation, strcuRes, 1, 1, 0);	
//				
//			}else if(cont.getIso().getClass().equals(DocumentTransferencia.class)){
				
//				DocumentTransferencia documentTransferencia = (DocumentTransferencia) cont.getIso();
//				DocumentRespuesta documentorespuesta = null;
//				BridgeVC_BCE bridge = new BridgeVC_BCE();
//				documentorespuesta = bridge.ProcesarTransferenciaVC_BCE(documentTransferencia, cont.getIP());
//				ContainerIsoQueue<DocumentRespuesta> strcuRes = new 
//						ContainerIsoQueue<DocumentRespuesta>(documentorespuesta, "127.0.0.1");
//				strcuRes.setSecuencial(bridge.getSecuencialTrx());
//				Queue queue = new Queue(cont.getNameQueueOrigin());
//				queue.SendMessage(typeMessage.externalMessageStation, strcuRes, 1, 1, 0);
//			}else if(cont.getIso().getClass().equals(Iso8583Binary.class)){
//				
//				Iso8583Binary isoBin = (Iso8583Binary) cont.getIso();
//				Iso8583Binary isoRes = null;
//				CardProcessorIsAcq processor = new CardProcessorIsAcq();
//				isoRes = processor.ProcessTransactionBinary(isoBin, cont.getIP());
//				if(isoRes.getDe11_STAN().equals("000000")){
//					MemoryGlobal.concurrentIso.put(isoBin.getDe11_STAN(), isoRes);
//					
//				}else
//					MemoryGlobal.concurrentIso.put(isoRes.getDe11_STAN(), isoRes);
//			}
			
		} catch (Exception e) {
			
			log.WriteLogMonitor("Error Modulo " + this.getClass().getName() + "::executeTrx" , TypeMonitor.error, e);
		}	
	}

	@Override
	public void run() {
		
		executeTrx();
	}
	
	
	  protected void printISOMessage(ISOMsg isoMsg) {
	        try {
	            System.out.println("MTI = " + isoMsg.getMTI());
	            
	            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
	                if (isoMsg.hasField(i)) {
	                    System.out.println("Field ("+ i +")"+ isoMsg.getString(i));
	                } 
	            }
	            System.out.println(isoMsg.toString());
	        } catch (ISOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
}
