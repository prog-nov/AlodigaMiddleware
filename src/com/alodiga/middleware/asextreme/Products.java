package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Products implements Serializable {

	private static final long serialVersionUID = -7546426709392110158L;

	public Products(){
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
