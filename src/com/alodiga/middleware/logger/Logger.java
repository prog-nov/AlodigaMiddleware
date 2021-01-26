package com.alodiga.middleware.logger;

import com.alodiga.middleware.logger.LoggerConfig.TypeLog;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.logger.LoggerConfig.TypeWriteLog;

public class Logger {

	
	public void WriteLog(final Object mensaje, TypeLog typeLog, TypeWriteLog typeWriteLog){
		LoggerConfig log = new LoggerConfig(mensaje, typeLog, typeWriteLog);
		log.start();
	}
	public void WriteLogMonitor(final String message, TypeMonitor type, Exception ex){
		LoggerConfig log = new LoggerConfig();
		Thread tr = new Thread(log.WriteMonitorRun(message, type, ex));
		tr.start();
	}
}
