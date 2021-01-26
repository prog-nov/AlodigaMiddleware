package com.alodiga.middleware.cscoreswitch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EngineCallableProcessor<T> {
	
	private final Collection<Callable<T>> runnableCollections = new ArrayList<Callable<T>>();
	private int numProcess;
	
	public void add(final Callable<T> task)
    {
		runnableCollections.add((Callable<T>) task);
    }
	public EngineCallableProcessor(int numProcess){
		this.numProcess = numProcess;
	}
	public List<T> goProcess() throws InterruptedException, ExecutionException{
		
		 List<T> object = new ArrayList<T>();
		 final ExecutorService pool = Executors.newFixedThreadPool(this.numProcess);
		 try {
			
			 Set<Future<T>> futureSetter = new HashSet<Future<T>>();
			 for (final Callable<T> caller : runnableCollections) {
				
				 try {
					 
					Callable<T> callable = (Callable<T>) caller;
					Future<T> future = pool.submit(callable);
					futureSetter.add((Future<T>) future);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 for (Future<T> futureList : futureSetter) {
					object.add((T)futureList.get());
			 }
			 
		 } finally {
			 
			 pool.shutdown();
		 }
		 return object;
	}
	  
}
