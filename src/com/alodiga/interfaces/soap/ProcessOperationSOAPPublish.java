package com.alodiga.interfaces.soap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

@WebServlet("/ProcessOperationSOAPPublish")
public class ProcessOperationSOAPPublish extends HttpServlet implements ServletContextListener {
	private static final long serialVersionUID = -8886470335381490870L;
	
    private Endpoint ep;

	public ProcessOperationSOAPPublish() {
		//aqui el levanta la interfaz SOAP
		
		try {
			ep = Endpoint.publish("http://localhost:1515/WS/processTransacction", new ProcessOpetationSOAPImplent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ep.stop();
		System.out.println("finalizando Servicio...SOAP");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("iniciando Servicio...SOAP");
		
	}


}
