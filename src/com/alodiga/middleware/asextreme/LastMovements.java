package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class LastMovements implements Serializable{

	private static final long serialVersionUID = 5696771875684734883L;

	public LastMovements() {
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
