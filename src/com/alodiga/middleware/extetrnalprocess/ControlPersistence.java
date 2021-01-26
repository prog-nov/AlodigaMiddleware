package com.alodiga.middleware.extetrnalprocess;

import com.alodiga.middleware.cscoreswitch.Iso8583;
import com.alodiga.middleware.cscoreswitch.csProcess;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.temporal.cache.MemoryGlobal;




public class ControlPersistence {
	
	private Logger log;
	public ControlPersistence(){
		this.log = new Logger();
	}

	public Runnable executeMessageControl(){
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				log.WriteLogMonitor("Inicia mensaje de Control....", TypeMonitor.monitor, null);
				csProcess process = new csProcess();
				Iso8583 iso = new Iso8583("1800", MemoryGlobal.proccodeMessageControl, 
												  MemoryGlobal.ownChannel, MemoryGlobal.ownNetwork);
				iso.setISO_002_PAN("6272470000000000");
				iso = process.ProcessTransactionMain(iso, "127.0.0.1");			
			}
		};
		return runnable;		
	}
}
