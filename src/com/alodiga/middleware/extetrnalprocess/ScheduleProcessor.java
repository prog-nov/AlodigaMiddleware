package com.alodiga.middleware.extetrnalprocess;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;


public class ScheduleProcessor {
	
	private Logger log;
	
	public ScheduleProcessor(){
		log = new Logger();
	}

	public void ExecuteProcessPersistence(ScheduledExecutorService service, Runnable runnable, TimeUnit unit, int interval){
		try {
			service = Executors
                    .newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnable, 0, interval, unit);
			
		} catch (Exception e) {
			if(!service.isTerminated())
				service.shutdown();
			log.WriteLogMonitor("Error modulo ScheduleProcessor::ExecuteProcessPersistence ", TypeMonitor.error, e);
			log.WriteLogMonitor("Error en modulo de Persistencia [Timer] ", TypeMonitor.monitor, e);
		}
	}
}
