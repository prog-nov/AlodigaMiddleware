package com.alodiga.interfaces.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "com.alodiga.interfaces.soap.ProcessOperationSOAP")
public class ProcessOpetationSOAPImplent implements ProcessOperationSOAP {

	@Override
	public String processTransacction(String isoMsg) {
		
		return "0200";
	}

}
