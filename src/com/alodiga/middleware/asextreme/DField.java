package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name", "value","type"})
public class DField implements Serializable{
	

	private static final long serialVersionUID = 5244572423065396346L;
	
	private String name;
	private String value;
	private String type;
	
	public DField() {
		super();
		
	}
	public DField(String name, String value) {
		
		this();
		this.name = name;
		this.value = value;
		
	}
	
	public DField(String name, String value, String type) {
		
		this();
		this.name = name;
		this.value = value;
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

