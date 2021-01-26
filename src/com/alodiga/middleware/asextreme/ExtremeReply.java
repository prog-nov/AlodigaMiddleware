package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExtremeMsgReply")
public class ExtremeReply extends ExtremeRequest implements Serializable {

	private static final long serialVersionUID = -5811770506454858793L;
	
	private FxTables tables;
	
	public ExtremeReply(){
		
		super();
	}
	public ExtremeReply(ExtremeRequest req){
		
		try {
			
			this.setHeader(req.getHeader());
			this.setData(req.getData());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	@XmlElement(name="FxTables")
	public FxTables getTables() {
		return tables;
	}

	public void setTables(FxTables tables) {
		this.tables = tables;
	}

	
}
