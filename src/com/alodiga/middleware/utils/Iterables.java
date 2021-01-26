package com.alodiga.middleware.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Iterables implements Serializable {

	private static final long serialVersionUID = -8226132961673893858L;
	private Map<String,String> iterarors;
	
	public Iterables(){
		
		this.iterarors = new HashMap<String, String>();
	}
	public Map<String, String> getIterarors() {
		return iterarors;
	}
	public void setIterarors(Map<String, String> iterarors) {
		this.iterarors = iterarors;
	}
	public void addMapper(String etiqueta, String Valor){
		this.iterarors.put(etiqueta, Valor);
	}
	
	
}