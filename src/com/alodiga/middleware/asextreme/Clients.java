package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Clients implements Serializable {
	
	private static final long serialVersionUID = -96099429350114333L;

	public Clients(){
		super();
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
