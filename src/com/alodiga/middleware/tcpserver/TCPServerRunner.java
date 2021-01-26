package com.alodiga.middleware.tcpserver;

import java.net.InetSocketAddress;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.temporal.cache.MemoryGlobal;


public class TCPServerRunner extends Thread{

	private static final int PORT = Integer.parseInt(System.getProperty("port", 
	           String.valueOf(MemoryGlobal.portTCPServer)));
	private static final boolean USE_SSL = MemoryGlobal.flagUseTCPSSL;
	private Logger log;
	private static SocketAcceptor acceptor;
	
	public TCPServerRunner(){
		log = new Logger();
	}
	
	private void InitServer(){
		try {
			acceptor = new NioSocketAcceptor(MemoryGlobal.maxThreadsTCPServer);
			acceptor.setReuseAddress( true );
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			if (USE_SSL) {
			    try {
					addSSLSupport(chain);
				} catch (Exception e) {
					e.printStackTrace();
					log.WriteLogMonitor("Error modulo InitServer[TCPServerRunner]::addSSLSupport ", TypeMonitor.error, e);
				}
			}
			acceptor.setHandler(/*new TCPServerProtocolHandler()*/new TCPServerProtcolISO8583Binary());
			acceptor.getSessionConfig().setReadBufferSize(41096);
			acceptor.getSessionConfig().setMaxReadBufferSize(41096);
			
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("TCP SSL SERVER [GENERIC] INIT!, PORT A : " + PORT);
			log.WriteLogMonitor("TCP SSL SERVER [GENERIC] INIT!, PORT: " + PORT, TypeMonitor.monitor, null);
			
		} catch (Exception e) {
			log.WriteLogMonitor("Error modulo TCPServerRunner[General]::InitServer ", TypeMonitor.error, e);
		}
	}
	
	
	private  void addSSLSupport(DefaultIoFilterChainBuilder chain){
		
		try {
			
			SslFilter sslFilter = new SslFilter(BogusSslContextFactory
		            .getInstance(true));
			sslFilter.setUseClientMode(false);
			sslFilter.setWantClientAuth(false);
			sslFilter.setNeedClientAuth(false);
		    chain.addLast("sslFilter", sslFilter);
		    System.out.println("Protocolo SSL es soportado en TCPServer..");
		    log.WriteLogMonitor("Protocolo SSL es soportado en TCPServer..", TypeMonitor.monitor, null);
			
		} catch (Exception e) {
			
			log.WriteLogMonitor("Error modulo TCPServerRunner[General]::addSSLSupport ", TypeMonitor.error, e);
		}
		
	}
	
	 public void CloseServer(){
			
			try {
				if(acceptor.isActive())
					acceptor.unbind();
				
			} catch (Exception e) {
				
				log.WriteLogMonitor("Error modulo CloseServer[TCPServerRunner]::CloseServer ", TypeMonitor.error, e);
			}
	}

	@Override
	public void run() {
		InitServer();
	}
	
}
