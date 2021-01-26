package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Cuotas implements Serializable{

	private static final long serialVersionUID = -6422295022353653645L;
	
	
	public Cuotas() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<Registro> registro;
	
	@XmlElement(name="Reg")
	public List<Registro> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Registro> registro) {
		this.registro = registro;
	}
}
