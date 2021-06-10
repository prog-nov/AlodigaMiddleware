package com.alodiga.init.start;



import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.alodiga.middleware.cscoreswitch.Iso8583;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.queueadmin.ReceiveAsyncMessage;
import com.alodiga.temporal.cache.MemoryGlobal;

import com.thoughtworks.xstream.XStream;


@WebServlet("/GlobalServletInit")
public class GlobalServletInit extends HttpServlet implements ServletContextListener {
	private static ExecutorService threadPoolReceiveQueueIni = null; 
	private static ExecutorService threadPoolReceiveQueueSF = null; 
	private static final long serialVersionUID = 1L;
    private Logger log;
    private Thread tt;
    private Thread ts;
    
    
    public GlobalServletInit() {
        super();
        log = new Logger();
    }	
    
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("paso111111111111111111111111111111111");
		XStream xstream = new XStream();
		xstream.processAnnotations(Iso8583.class);
		
		MemoryGlobal.currentPath = this.getClass().getClassLoader().getResource("").getPath();
		String splitter = "\\/";
        String [] pather = MemoryGlobal.currentPath.split(splitter);
        MemoryGlobal.currentPath = MemoryGlobal.currentPath.replace(pather[pather.length - 1] + "/", "");
	
		System.out.println("Servlet Init Java......"+ MemoryGlobal.currentPath);
		LoggerConfig logConfig = new LoggerConfig();
		logConfig.InitLoggerService(config);	
		
		log.WriteLogMonitor("Cargando variables Globales......", TypeMonitor.monitor, null);
		
		if(MemoryGlobal.LoadParamsConfig()){
			System.out.println("Variables cargadas exitosamente...");
			log.WriteLogMonitor("Variables cargadas exitosamente...", TypeMonitor.monitor, null);
			System.out.println("Creando Pool conexion a la BDD");
			log.WriteLogMonitor("Creando Pool conexion a la BDD...", TypeMonitor.monitor, null);
			
			
				if(MemoryGlobal.LoadMemory()){
					//log.WriteLogMonitor("Current Path ===> " + MemoryGlobal.currentPath, TypeMonitor.monitor, null);
					System.out.println("entro en el load Memory");	
					/** Pendiente proceso en paralelo para inicializacion**/
					System.out.println("flagUseQueueInit : "+MemoryGlobal.flagUseQueueInit);	
					System.out.println("sendMsgControlFlag : "+MemoryGlobal.sendMsgControlFlag);	
					if(MemoryGlobal.sendMsgControlFlag) 
						System.out.println("entro en. sendMsgControlFlag");	
						MemoryGlobal.InitMessageControl();
					if(MemoryGlobal.flagUseQueueInit){
						System.out.println("flagUseQueueInit");	
						/*Queue queue = new Queue();
						queue.ReceiveMessage(typeMessage.initialMessage);
						queue.ReceiveMessage(typeMessage.storeAndForwardType);*/
						//esta clase es la que levanta todo!!! lo que va en cache!
						
						//ReceiveAsyncMessage 
						threadPoolReceiveQueueIni = Executors.newFixedThreadPool(200);
						System.out.println("threadPoolReceiveQueueIni");	
						threadPoolReceiveQueueIni.execute(new ReceiveAsyncMessage(MemoryGlobal.queueNameIni, 
							    MemoryGlobal.sessionQueueIni));
						
						System.out.println("Creo el Hilo Receptor de Mensaje!!!");
						System.out.println("threadPoolReceiveQueueIni.execute(new Receiv");
						threadPoolReceiveQueueSF = Executors.newFixedThreadPool(100);
						System.out.println("threadPoolReceiveQueueIni.execute(new newFixedThreadPool");
						threadPoolReceiveQueueSF.execute(new ReceiveAsyncMessage(MemoryGlobal.queueNameSf, 
							    MemoryGlobal.sessionQueueSf));
						System.out.println("threadPoolReceiveQueueIni.execute(eueSF.execute(new ReceiveAsyncMess");
						/*tt = new Thread(new ReceiveAsyncMessage(MemoryGlobal.queueNameIni, 
								    MemoryGlobal.sessionQueueIni));
						tt.setDaemon(true);
						tt.start();*/
						
						/*
						 * ts = new Thread(new ReceiveAsyncMessage(MemoryGlobal.queueNameSf,
						 * MemoryGlobal.sessionQueueSf)); ts.setDaemon(true); ts.start();
						 */

					}
	
				}

			
		}
		
			
	}
		
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {		
		System.out.println("Termina Middleware!!!!!!!!!!!!!!!");
		log.WriteLogMonitor("Termina Middleware!!!!!!!!!!!!!!!", TypeMonitor.error, null);
		log.WriteLogMonitor("Termina Middleware!!!!!!!!!!!!!!!", TypeMonitor.monitor, null);
		try {
			MemoryGlobal.conn.close();
			if(MemoryGlobal.serviceSchedule!=null){
				if(!MemoryGlobal.serviceSchedule.isShutdown())
					MemoryGlobal.serviceSchedule.shutdown();
			}
			if(MemoryGlobal.flagUseQueueInit){
				if(MemoryGlobal.sessionQueueIni!=null){
					MemoryGlobal.sessionQueueIni.close();
					threadPoolReceiveQueueIni.shutdown();
				}
				if(MemoryGlobal.sessionQueueSf!=null){
					MemoryGlobal.sessionQueueSf.close();
					threadPoolReceiveQueueSF.shutdown();
				}
			}
			
			if(tt != null)
				tt.interrupt();
			if(ts != null)
				ts.interrupt();
			
		} catch (SQLException e) {			
			log.WriteLogMonitor("Error modulo [contextDestroyed] Servlet ", TypeMonitor.error, e);
			log.WriteLogMonitor("Error modulo [contextDestroyed] Servlet", TypeMonitor.monitor, null);
			
		}catch (Exception e) {
			log.WriteLogMonitor("Error modulo [contextDestroyed] Servlet ", TypeMonitor.error, e);
			e.printStackTrace();
		}
	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("Inicia Sistema...... Middleware Alodiga 1.0");
	}
	
}
