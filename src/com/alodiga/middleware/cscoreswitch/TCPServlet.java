package com.alodiga.middleware.cscoreswitch;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alodiga.middleware.tcpserver.TCPServerRunner;




/**
 * Servlet implementation class TCPServlet
 */
@WebServlet("/TCPServlet")
public class TCPServlet extends HttpServlet implements ServletContextListener {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TCPServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
			TCPServerRunner tcpRun = new TCPServerRunner();
			tcpRun.setDaemon(true);
			tcpRun.start();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//TCPServer tcp = new TCPServer();
		//tcp.CloseServer();
		TCPServerRunner tcp = new TCPServerRunner();
		tcp.CloseServer();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
