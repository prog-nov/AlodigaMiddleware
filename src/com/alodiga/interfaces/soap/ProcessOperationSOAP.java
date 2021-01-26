package com.alodiga.interfaces.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;



@WebService
public interface ProcessOperationSOAP {
	@WebMethod
	public String processTransacction(String isoMsg);

}
