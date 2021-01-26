package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ExtremeMsg")
@XmlType(propOrder={"header", "data"})
public class ExtremeRequest implements Serializable {

	private static final long serialVersionUID = -2519575748369657736L;
	private Header header;
	private Data data;
	
	public ExtremeRequest(){
		
	}
	@XmlElement(name="Header")
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	@XmlElement(name="Data")
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}

