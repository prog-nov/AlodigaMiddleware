package com.alodiga.middleware.cscoreswitch;

import com.alodiga.temporal.cache.MemoryGlobal;

public class AdminConcurrentMap extends Thread {
	
	private String key;
	
	public AdminConcurrentMap(String key) {
		
		this.key = key;
		this.start();
	}
	public void DeleteKey(){
		
		try {
			System.out.println("Antes: " + MemoryGlobal.concurrentIso.size());
			Thread.sleep(5000);
			MemoryGlobal.concurrentIso.remove(this.key);
			System.out.println("Despues: " + MemoryGlobal.concurrentIso.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		DeleteKey();
	}
	
}
