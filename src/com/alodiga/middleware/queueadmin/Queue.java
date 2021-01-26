package com.alodiga.middleware.queueadmin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.alodiga.temporal.cache.MemoryGlobal;


public class Queue {
	
	private String nameQueue;
	
	public Queue(){
		super();
	}
	
	public Queue(String nameQueue){
		this.nameQueue = nameQueue;
	}
	
	@SuppressWarnings("static-access")
	public  void SendMessage(typeMessage type, Object obj, int prioridad, int tipo, long seconds){
		System.out.println("encolo SendMessage");
		@SuppressWarnings("unused")
		AdminQueueMessage queue = null;
		if(type == type.initialMessage){
			System.out.println("type == type.initialMessage");
			queue = new AdminQueueMessage(MemoryGlobal.queueNameIni, 
					    MemoryGlobal.sessionQueueIni, MemoryGlobal.producerQueueIni,
					    obj, prioridad,tipo, seconds);
		}else if (type == type.storeAndForwardType) {
			System.out.println("type == type.storeAndForwardType");
			queue = new AdminQueueMessage(MemoryGlobal.queueNameSf, 
				    MemoryGlobal.sessionQueueSf, MemoryGlobal.producerQueueIni,
				    obj, prioridad,tipo, seconds);
		}else if (type == type.externalMessageStation) {
			System.out.println("type == type.externalMessageStation");
			queue = new AdminQueueMessage(this.nameQueue, 
				    MemoryGlobal.sessionQueueExternal, MemoryGlobal.producerQueueExternal,
				    obj, prioridad,tipo, seconds);
		}
		 
	}
	@SuppressWarnings("static-access")
	public  void ReceiveMessage(typeMessage type){
		System.out.println("recibe ReceiveMessage static-access en cola");	
		
		ExecutorService threadPool = Executors.newFixedThreadPool(120);
		try {
			
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					
					@SuppressWarnings("unused")
					ReceiveAsyncMessage message = null;
					if(type == type.initialMessage){
						
						message = new ReceiveAsyncMessage(MemoryGlobal.queueNameIni, 
								  MemoryGlobal.sessionQueueIni);
					}else if (type == type.storeAndForwardType) {
					
						message = new ReceiveAsyncMessage(MemoryGlobal.queueNameSf, 
								  MemoryGlobal.sessionQueueSf);
					}
				}
			});
			
		} finally {
			
			threadPool.shutdown();
		}
			
		
	}
}
