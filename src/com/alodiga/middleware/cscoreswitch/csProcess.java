package com.alodiga.middleware.cscoreswitch;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeLog;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.logger.LoggerConfig.TypeWriteLog;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.StringUtils;
import com.alodiga.temporal.cache.MemoryGlobal;


public class csProcess implements Callable<Iso8583> {
	
	private Logger log;
	private StopWatch timmerMidd = null;
	private boolean continueTrx;
	private Iso8583 iso;
	private String IP;
	
	public csProcess(){
		this.log = new Logger();
		this.continueTrx = false;
	}
	
	public csProcess(Iso8583 iso, String IP){
		this();
		this.IP = IP;
		this.iso = iso;
	}
	
	public Iso8583 getIso() {
		return iso;
	}

	public void setIso(Iso8583 iso) {
		this.iso = iso;
	}

	public Iso8583 ProcessTransactionMain(Iso8583 iso, String Ip) {
		System.out.println("ProcessTransactionMainkkkkkkk");
		timmerMidd = new StopWatch();
		int responseSql = 0;
		wIso8583 wiso = null;
		//IsoSqlMaintenance sql = null;
		timmerMidd.start();
		if(MemoryGlobal.flagSystemReady){
			try {				
			    log.WriteLog(iso, TypeLog.report, TypeWriteLog.file);			
				wiso = new wIso8583(iso, Ip);			
				if(wiso.getISO_039_ResponseCode().equals("000")){
				}
			} 
			catch (Exception e) {
				log.WriteLogMonitor("Error modulo csProcess::ProcessTransactionMain ", TypeMonitor.error, e);
				iso.setISO_039_ResponseCode("909");
				iso.setISO_039p_ResponseDetail(GeneralUtils.ExceptionToString("ERROR EN PROCESOS ", e));
			}	
			finally{
				
				if(iso.getISO_000_Message_Type().startsWith("12") || iso.getISO_000_Message_Type().startsWith("14"))
					iso.setISO_000_Message_Type(iso.getISO_000_Message_Type().replace("00", "10"));
				else if (iso.getISO_000_Message_Type().startsWith("18")) {
					iso.setISO_000_Message_Type("1810");
				}
					
				timmerMidd.stop();
				wiso.setTickMidd(timmerMidd);
				wiso.setWsTempTrx((timmerMidd.getTime(TimeUnit.MILLISECONDS)/1000.0));
			}
			EndTransaction endTrx = new EndTransaction(wiso, iso, this.continueTrx);
			endTrx.start();
		}
		else{
			iso.setISO_000_Message_Type(iso.getISO_000_Message_Type().replace("00", "10"));
			iso.setISO_039_ResponseCode("901");
			iso.setISO_039p_ResponseDetail("ERROR AL INICIALIZAR PROCESOS A MEMORIA... GET MEMORY_CACHED NULL");
		}
		return iso;
	}
	
	private Iso8583 ParseISO8583toWiso(wIso8583 iso){
		Iso8583 ISO = null;
		try {			
			if( StringUtils.IsNullOrEmpty(iso.getISO_038_AutorizationNumber().trim()) 
					&& iso.getWsIso_LogStatus() == 2 && iso.getISO_039_ResponseCode().equals("000"))
				 iso.setISO_038_AutorizationNumber(GeneralUtils.GetSecuencial(6).toUpperCase());
			ISO = new Iso8583(iso);	
			return ISO;
		} catch (Exception e) {
			log.WriteLogMonitor("Error modulo csProcess::ParseISO8583toWiso ", TypeMonitor.error, e);
			return null;
		}					
	}
	
//	public wIso8583 ExecStoreAndForwardMain(wIso8583 iso){
		
//		timmerMidd = new StopWatch();
//		try {
//			
//			iso.setTickAut(new StopWatch());
//			iso.setTickBdd(new StopWatch());
//			iso.setTickMidd(new StopWatch());
//			
//			String messageClass = iso.getWsTransactionConfig().getMessage_Class();					
//			List<String> aa = Arrays.asList(messageClass.split("\\."));
//			String methodName = aa.get(aa.size() -1);
//			String classname = messageClass.replace("." + methodName, "");
//			
//			Class<?> instanceClass = Class.forName(classname);																			
//			Object classInstance = instanceClass.getConstructor(new Class[]{}).newInstance();					
//			Method methodToInvoke = instanceClass.getMethod(methodName, wIso8583.class);	
//			
//			timmerMidd.reset();
//			timmerMidd.start();
//			iso = (wIso8583) methodToInvoke.invoke(classInstance, iso);
//			timmerMidd.stop();
//			iso.setTickMidd(timmerMidd);
//			
//			if(iso.getWsISO_FlagStoreForward()){
//	
//			}else {
//				
//			
//			}
//		} catch (Exception e) {
//			
//			if(timmerMidd.isStarted())
//				timmerMidd.stop();
//			log.WriteLogMonitor("Error modulo csProcess::ExecStoreAndForwardMain ", TypeMonitor.error, e);
//			e.printStackTrace();
//			
//		}finally {
//			
//			iso.setWsTempTrx((timmerMidd.getTime(TimeUnit.MILLISECONDS)/1000.0));
//		}
//		return iso;
//	}

	@Override
	public Iso8583 call() throws Exception {
		
		return ProcessTransactionMain(this.iso, this.IP);
	}
}
