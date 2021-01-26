package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Data implements Serializable{

	private static final long serialVersionUID = 6266002634739204083L;
	private List<DField> dfield;
	
	public Data(){
		
	}
	@XmlElement(name="DField")
	public List<DField> getDfield() {
		return dfield;
	}

	public void setDfield(List<DField> dfield) {
		this.dfield = dfield;
	}
	
	public String getValueTag(String tagName){
		
		String value = null;
		try {
			
			DField sec = this.dfield.stream()
    			    .filter(a -> a.getName().equals(tagName))
    			    .findFirst().orElseGet(() -> null);
    	
	    	if(sec != null)
	    		value = sec.getValue();
			
		} catch (Exception e) {
			
		}
		return value;
	}
}
