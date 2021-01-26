package com.alodiga.middleware.tcpserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.GeneralSecurityException;
import javax.net.ServerSocketFactory;

public class SslServerSocketFactory extends javax.net.ServerSocketFactory {

	private static boolean sslEnabled = false;
	private static ServerSocketFactory factory = null;	
	private static javax.net.ServerSocketFactory sslFactory = null;
	public SslServerSocketFactory() {
	       super();
    }
	
	@Override
	public ServerSocket createServerSocket(int port, int backlog)
	        throws IOException {
	    return new ServerSocket(port, backlog);
	}

	@Override
	public ServerSocket createServerSocket(int port, int backlog,
	        InetAddress ifAddress) throws IOException {
	    return new ServerSocket(port, backlog, ifAddress);
	}

	public static javax.net.ServerSocketFactory getServerSocketFactory()
	        throws IOException {
	    if (isSslEnabled()) {
	        if (sslFactory == null) {
	            try {
	                sslFactory = BogusSslContextFactory.getInstance(true)
	                        .getServerSocketFactory();
	            } catch (GeneralSecurityException e) {
	                IOException ioe = new IOException(
	                        "could not create SSL socket");
	                ioe.initCause(e);
	                throw ioe;
	            }
	        }
	        return sslFactory;
	    } else {
	        if (factory == null) {
	            factory = new SslServerSocketFactory();
	        }
	        return factory;
	    }

	}

	public static boolean isSslEnabled() {
	    return sslEnabled;
	}

	public static void setSslEnabled(boolean newSslEnabled) {
	    sslEnabled = newSslEnabled;
	}

	@Override
	public ServerSocket createServerSocket(int arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
