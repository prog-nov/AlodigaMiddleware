package com.alodiga.middleware.asextreme;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name", "value", "type"})
public class HField implements Serializable{
	
	private static final long serialVersionUID = -7075031930333061905L;
	private String name;
	private String value;
	private String type;
	
	public HField(){
		
	}
	
	public HField(String name, String value, String type){
		
		this.name = name;
		this.value = value;
		if(type == null)
			this.type = null;
		else	
			this.type = type;
	}
	 @XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 @XmlAttribute
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	 @XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
