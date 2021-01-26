package com.alodiga.middleware.cscoreswitch;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContainerIsoQueue<T> implements Serializable{

	public ContainerIsoQueue(T iso, String value){
		
		this.iso  = (T) iso;
		this.IP = value;
	}
	public ContainerIsoQueue(){
		
	}
	
	private T iso;
	private String IP;
	private String Secuencial;
	private String nameQueueOrigin;

	public T getIso() {
		return iso;
	}

	public void setIso(T iso) {
		this.iso = iso;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}
	public String getSecuencial() {
		return Secuencial;
	}
	public void setSecuencial(String secuencial) {
		Secuencial = secuencial;
	}
	public String getNameQueueOrigin() {
		return nameQueueOrigin;
	}
	public void setNameQueueOrigin(String nameQueueOrigin) {
		this.nameQueueOrigin = nameQueueOrigin;
	}
	
	
	
}
