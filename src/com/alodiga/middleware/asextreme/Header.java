package com.alodiga.middleware.asextreme;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Header implements Serializable{

	private static final long serialVersionUID = -5951752440498891232L;
	private List<HField> hfield;
	@XmlElement(name="HField")
	public List<HField> getHfield() {
		return hfield;
	}

	public void setHfield(List<HField> hfield) {
		this.hfield = hfield;
	}	
	
	public String getValueTag(String tagName){
		
		String value = null;
		try {
			
			HField sec = this.hfield.stream()
    			    .filter(a -> a.getName().equals(tagName))
    			    .findFirst().orElseGet(() -> null);
    	
	    	if(sec != null)
	    		value = sec.getValue();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return value;
	}
	public String setValueTag(String tagName, String Value){
		
		String value = null;
		try {
			
			HField sec = this.hfield.stream()
    			    .filter(a -> a.getName().equals(tagName))
    			    .findFirst().orElseGet(() -> null);
    	
	    	if(sec != null)
	    		sec.setValue(Value);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return value;
	}
}


