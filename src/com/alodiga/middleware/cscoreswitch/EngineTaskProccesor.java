package com.alodiga.middleware.cscoreswitch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EngineTaskProccesor {

	private final Collection<Runnable> tasksi = new ArrayList<Runnable>();
	@SuppressWarnings("unused")
	private long numProccess = 0;
	public EngineTaskProccesor(long numProccessors){
		this.numProccess = numProccessors;
	}
	public void add(final Runnable task)
    {
        tasksi.add(task);
    }
	public void go() throws InterruptedException
    {        
    	final ExecutorService threads = Executors.newFixedThreadPool(10);
    	//System.out.println(Runtime.getRuntime().availableProcessors()); 
        try
        {
            final CountDownLatch latch = new CountDownLatch(tasksi.size());
            //System.out.println("Numero de Tareas" + tasksi.size());
            for (final Runnable task : tasksi)
                threads.execute(new Runnable() {
                    public void run()
                    {
                        try
                        {
                            task.run();
                        }
                        finally
                        {
                            latch.countDown();
                        }
                    }
                });
            latch.await();
        }
        finally
        {
            threads.shutdown();
        }
    }
}
