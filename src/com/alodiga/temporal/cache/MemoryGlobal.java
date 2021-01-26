package com.alodiga.temporal.cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import com.alodiga.middleware.extetrnalprocess.ControlPersistence;
import com.alodiga.middleware.extetrnalprocess.ScheduleProcessor;
import com.alodiga.middleware.logger.LoggerConfig;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.utils.GeneralUtils;




public final class MemoryGlobal{
	
	public static final String ListTrxConfigMem = null;
	public static String currentPath;
	public static boolean flagSystemReady = true;
	public static Connection conn;
	public static Map<String, String> MapFacilito = new HashMap<>();
 	public static ScheduledExecutorService serviceSchedule = null;
	public static String commonCodSursal;
	public static String commonCodOficina;
	public static String commonRol;
	public static String commonIdioma;
	public static String commonArea;
	public static String commonTPP;
	public static String commonCompania;
	public static String commonSesion;
	public static String commonTrxReverso;
	public static String commonFechaContable;
	public static String bddConnectionString;
	public static String bddUserName;
	public static String bddPassword;
	public static int    bddMaxPoolSize;
	public static int    bddMinPoolSize;
	public static int    bddInitialPoolSize;
	public static String sessionSys;
	public static String IpUci;
	public static int portUci;
	public static int timeOutUci;
	public static String messageReverseUCI;
	public static String ownNetwork;
	public static String ownChannel;
	public static String proccodeMessageControl;
	public static boolean sendMsgControlFlag;
	public static int sendMsgControlInterval;
	public static String urlBce;
	public static String urlBceAdmin;
	public static String ipBCEAdmin;
	public static int portBCEAdmin;
	public static String userBce;
	public static String pwdBce;
	public static String idiomBce;
	public static String urlFacilito;
	public static String urlFacilitoConfirm;
	public static boolean flagPutCert;
	public static boolean flagPutCertII;
	public static boolean flagSecuenceInReverse;
	public static String queueNameSf;
	public static MessageProducer producerQueueSf;
	public static ObjectMessage messageQueueSf;
	public static Session sessionQueueSf;
	public static Session sessionQueueExternal;
	public static String queueNameIni;
	public static String queueNameCoonecta;
	public static MessageProducer producerQueueIni;
	public static MessageProducer producerQueueExternal;
	public static ObjectMessage messageQueueIni;
	public static Session sessionQueueIni;
	public static Map<String,Object> concurrentIso;
	public static boolean flagUseQueueInit;
	public static int portTCPServer;
	public static int maxThreadsTCPServer;
	public static String pwdCertI;
	public static String pwdCertII;
	public static String nameCertBceI;
	public static String nameCertBceII;
	public static String nameSSLTCPFile; 
	public static String passSSLTCPFile;
	public static int typeTCPServerService;
	public static boolean flagUseTCPSSL;
	public static String facilitoABA;
	public static String facilitoTOKEN;
	public static String facilitoTRACE_SW;
	public static String facilitoUSUARIO;
	public static String facilitoCLAVE;
	public static String facilitoCODIGO_AUTORIZACION;
	public static String facilitoNUMERO_CONTRATO;
	public static String facilitoSEGURIDAD;
	public static boolean flagCertficatefacilito;
	public static String facilitoNameCert;
	public static String facilitoPasswordCert;
	public static String BCE_Efi_VC;
	
	@SuppressWarnings("static-access")
	public static boolean LoadParamsConfig(){
		
		System.out.println("Entro en el load parameter config");
		LoggerConfig log = new LoggerConfig();
		try {					
	        Properties p = new Properties();
	   
	        p.load(new FileInputStream(currentPath + "config.properties"));
	        commonCodSursal = p.getProperty("commonCodSursal");
	        commonCodOficina = p.getProperty("commonCodOficina");
	        commonRol = p.getProperty("commonRol");
	        commonIdioma = p.getProperty("commonIdioma");
	        commonArea = p.getProperty("commonArea");
	        commonTPP = p.getProperty("commonTPP");
	        commonCompania = p.getProperty("commonCompania");        
	        commonSesion = p.getProperty("commonSesion");
	        commonTrxReverso = p.getProperty("commonTrxReverso");	        
	        commonFechaContable = p.getProperty("commonFechaContable");
	        bddConnectionString = p.getProperty("bddconnectionurl");
	        bddUserName = p.getProperty("bdduser");
	        bddPassword = p.getProperty("bddpassword");	 
	        sessionSys = GeneralUtils.GetSecuencial(32);
	        IpUci = p.getProperty("ipUci");	 
	        portUci =  Integer.parseInt(p.getProperty("portUci"));	
	        timeOutUci = Integer.parseInt(p.getProperty("timeOutUci"));
	        messageReverseUCI = p.getProperty("uciReverseTrx");
	        bddMaxPoolSize = Integer.parseInt(p.getProperty("MaxPoolSize"));
	        bddMinPoolSize = Integer.parseInt(p.getProperty("MinPoolSize"));
	        bddInitialPoolSize = Integer.parseInt(p.getProperty("InitialPoolSize"));
	        ownNetwork = p.getProperty("ownNetwork");
	        ownChannel = p.getProperty("ownChannel");
	        proccodeMessageControl = p.getProperty("proccodeMsgControl");
	        sendMsgControlFlag = Boolean.parseBoolean(p.getProperty("sendMsgControlFlag"));
	        sendMsgControlInterval = Integer.parseInt(p.getProperty("sendMsgControlInterval"));
	        
	        System.out.println("sendMsgControlFlag ="+ sendMsgControlFlag);
	        System.out.println("sendMsgControlInterval ="+ sendMsgControlInterval);
	        urlBce = p.getProperty("urlServiceBce");
	        userBce = p.getProperty("userNameBce");
	        pwdBce = p.getProperty("pwdBce");
	        idiomBce = p.getProperty("idiomBce");
	        urlFacilito = p.getProperty("serviceFacilito");
	        urlFacilitoConfirm = p.getProperty("serviceFacilitoConfirm");
	        flagPutCert = Boolean.parseBoolean(p.getProperty("flagPutCertificate"));
	        flagPutCertII = Boolean.parseBoolean(p.getProperty("flagPutCertificateII"));
	        queueNameSf = p.getProperty("queueStoreForward");
	        queueNameIni = p.getProperty("queueNameIni");
	        queueNameCoonecta = p.getProperty("queueCoonecta");
	        concurrentIso =  new ConcurrentHashMap<>();
	        flagUseQueueInit = Boolean.parseBoolean(p.getProperty("flagUSeQueueInit"));
	        
	        System.out.println("flagUseQueueInit ="+ flagUseQueueInit);
	        
	        
	        flagSecuenceInReverse = Boolean.parseBoolean(p.getProperty("flagRandomSecuenceRever"));
	        portTCPServer = Integer.parseInt(p.getProperty("TCPServerPort"));
	        maxThreadsTCPServer = Integer.parseInt(p.getProperty("TCPServerNroThreads"));
	        pwdCertI = p.getProperty("pwdCertI");
	        pwdCertII = p.getProperty("pwdCertII");
	        urlBceAdmin = p.getProperty("urlServiceBceAdmin");
	        ipBCEAdmin = p.getProperty("ipBCEServiceAdmin");
	        portBCEAdmin = Integer.parseInt(p.getProperty("portBCEServiceAdmin"));
	        nameCertBceI = p.getProperty("nameCertBceI");
	        nameCertBceII = p.getProperty("nameCertBceII");
	        nameSSLTCPFile = p.getProperty("SSLTCPFile");
	        passSSLTCPFile = p.getProperty("SSLFilePassword");
	        typeTCPServerService = Integer.parseInt(p.getProperty("TypeTCPServer"));	
	        flagUseTCPSSL = Boolean.parseBoolean(p.getProperty("TCPServerUseSSL"));
	        facilitoABA = p.getProperty("facilitoABA");
	        facilitoTOKEN = p.getProperty("facilitoTOKEN");
	        facilitoTRACE_SW = p.getProperty("facilitoTRACE_SW");
	        facilitoUSUARIO = p.getProperty("facilitoUSUARIO");
	        facilitoCLAVE = p.getProperty("facilitoCLAVE");
	        facilitoCODIGO_AUTORIZACION = p.getProperty("facilitoCODIGO_AUTORIZACION");
	        facilitoNUMERO_CONTRATO = p.getProperty("facilitoNUMERO_CONTRATO");
	        facilitoSEGURIDAD = p.getProperty("facilitoSEGURIDAD");
	        flagCertficatefacilito = Boolean.parseBoolean(p.getProperty("flagCertficatefacilito"));
	        facilitoPasswordCert = p.getProperty("facilitoPasswordCert");
	        return true;	
	        
		} catch (IOException ex){
			log.WriteMonitor("ERROR Modulo MemoryGlobal::LoadParamsConfig()", TypeMonitor.error, ex);
			return false;
		} catch (Exception e) {			
			log.WriteMonitor("ERROR General Modulo MemoryGlobal::LoadParamsConfig()", TypeMonitor.error, e);
			return false;
		}
	
	}
	
	
	@SuppressWarnings("static-access")
	public static boolean LoadMemory(){
		
		LoggerConfig log = new LoggerConfig();
		try {
		
			System.out.println("cargo memoria");
			return true;
			
		} catch (Exception e) {
			log.WriteMonitor("ERROR Modulo MemoryGlobal::LoadMemory() ", TypeMonitor.error, e);
			return true;
		}
	}
	
    public static void InitMessageControl(){
		
		ControlPersistence ctrlPersistense = new ControlPersistence();
		ScheduleProcessor schedule = new ScheduleProcessor();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				schedule.ExecuteProcessPersistence(serviceSchedule, 
							ctrlPersistense.executeMessageControl(), 
							TimeUnit.SECONDS, MemoryGlobal.sendMsgControlInterval);
			}
		});
		t.start();
	}

}
