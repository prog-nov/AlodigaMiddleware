package com.alodiga.middleware.logger;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.alodiga.middleware.cscoreswitch.Iso8583;
import com.alodiga.middleware.cscoreswitch.wIso8583;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.StringUtils;
import com.alodiga.temporal.cache.MemoryGlobal;
import urn.iso.std.iso20022.tech.xsd.pacs_002_001_05.DocumentRespuesta;
import urn.iso.std.iso20022.tech.xsd.pacs_003_001_04.DocumentRetiro;
import urn.iso.std.iso20022.tech.xsd.pacs_004_001_04.DocumentReverso;
import urn.iso.std.iso20022.tech.xsd.pacs_008_001_04.DocumentDeposito;
import urn.iso.std.iso20022.tech.xsd.pacs_008_001_04.DocumentTransferencia;



public class LoggerConfig extends Thread {

	public enum TypeMonitor{monitor, error};
	public enum TypeLog { debug, report, monitor, bceaut, bceacq, facilito, alexsoft, isoBin };
	public enum TypeWriteLog { consoleAndFile, console, file };
	static final Logger debugLog = Logger.getLogger("debugLogger");
	static final Logger resultLog = Logger.getLogger("reportsLogger");
	static final Logger rootLog = Logger.getLogger(LoggerConfig.class);
	static final Logger consoleLog = Logger.getLogger("consoleLogger");
	static final Logger monitorLog = Logger.getLogger("monitorsLogger");
	static final Logger errorLog = Logger.getLogger("errorsLogger");
	static final Logger bceAutLog = Logger.getLogger("bceLogger");
	static final Logger bceAcqLog = Logger.getLogger("bceAcqLogger");
	static final Logger facilAutLog = Logger.getLogger("facilitoAutLogger");
	static final Logger alexAcqLog = Logger.getLogger("alexAcqLogger");
	static final Logger isoBinLog = Logger.getLogger("iso8583BinLogger");
	
	private Object mensaje;	
	private TypeLog typeLog;
	private TypeWriteLog typeWriteLog;
	public LoggerConfig(){
		
	}
	public LoggerConfig(final Object mensaje, TypeLog typeLog, TypeWriteLog typeWriteLog ){
		this.mensaje = mensaje;
		this.typeLog = typeLog;
		this.typeWriteLog = typeWriteLog;
	}
	
	public void InitLoggerService(ServletConfig config){
		try {
			System.out.println("Inicia Configuracion de Log4j!!!!!!");
			System.out.println("Clase GlobalServletInit esta incializando log4j...");
			String log4jLocation = config.getInitParameter("log4j-properties-location");			
			ServletContext sc = config.getServletContext();
			if(sc != null){
				
				String splitter = "\\/";
		        String [] pather = MemoryGlobal.currentPath.split(splitter);
		        String _path = MemoryGlobal.currentPath.replace(pather[pather.length -1] + "/", "");
				String webAppPath = _path;//"D:/MiddlewareFitBankJava/slnMiddlewareFitBank/WebContent/";				
				String log4jProp = webAppPath + log4jLocation;
				File yoMamaYesThisSaysYoMama = new File(log4jProp);
				if (yoMamaYesThisSaysYoMama.exists()) {
					System.out.println("Se ha inicializado Log4j en el siguiente Path: " + log4jProp);
					PropertyConfigurator.configure(log4jProp);
					System.out.println("Proceso inicializacion Log4j exitoso...");	
				} else {
					System.err.println("*** " + log4jProp + " Archivo de propiedades no encontrado!!!");					
				}
			}						
			System.out.println("Termina configuracion Log4j.....");						
		} catch (Exception e) {			
			System.out.println("Error al inicial componente log4j: " + e.getMessage());			
		}
	}
	
	

	
	
	
	
	public static void WriteMonitor(String msg, TypeMonitor type, Exception ex){
		
		if(type == TypeMonitor.monitor)
			monitorLog.info(msg);
		else{
			if(ex != null)
				errorLog.error(GeneralUtils.ExceptionToString(msg, ex));
			else
				errorLog.error(msg);
		}
	}
	
	public static void WriteLogExtern(final Object msg, TypeLog tl, TypeWriteLog tw) throws InterruptedException{
		Thread.sleep(10000);
		ExecutorService es = Executors.newFixedThreadPool(3);       
		@SuppressWarnings("unused")
		Future<?> future = es.submit(new Callable<Object>() {
        			@Override
                    public Object call() throws Exception {        				
        			//	LoggerConfig.writeTest(msg, tl, tw);
                        return null;
                    }
                }); 
	}
	@Override
	public void run() {
		try {			
			//writeTest(this.mensaje, this.typeLog, this.typeWriteLog);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	public Runnable WriteLogRunnable(final Object msg, final TypeLog tl, final TypeWriteLog tw){
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {				
				try {
				//	writeTest(msg, tl, tw);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		};
		return runnable;
	}
	public Runnable WriteMonitorRun(wIso8583 iso, Iso8583 iso8583){
		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {				
				try {
				//	WriteMonitor(iso, iso8583);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		};
		return runnable;
	}
	public Runnable WriteMonitorRun(String msg, TypeMonitor type, Exception ex){
		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {				
				try {
					WriteMonitor(msg, type, ex);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		};
		return runnable;
	}
	public Runnable WriteMonitorStoreAndForwardAsycn(wIso8583 iso){
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				
				//WriteMonitorStoreAndForward(iso);
			}
		};
		
		return r;
	}
	
}
