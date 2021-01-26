package com.alodiga.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessOpetationServlet")
public class ProcessOpetationServlet extends HttpServlet {

	
	public ProcessOpetationServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<h1>Mensaje ISO  desde ProcessOpetationServlet</h1>");
	         
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<h1>Mensaje ISO respuesta doPost desde MyServlet</h1>");
	    }
	
}
