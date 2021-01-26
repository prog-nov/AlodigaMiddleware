package urn.iso.std.iso20022.tech.xsd.pacs_008_001_04;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "documentoDeposito", namespace = "http://www.bce.fin.ec/wsdl/srr/VentanillaCompartidaSRRWS")
@XmlType(name = "documentoDeposito", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.04")
public class DocumentDeposito implements Serializable{

	private FIToFICustomerCreditTransferV04 FIToFICstmrCdtTrf;

	public FIToFICustomerCreditTransferV04 getFIToFICstmrCdtTrf() {
		return FIToFICstmrCdtTrf;
	}

	public void setFIToFICstmrCdtTrf(FIToFICustomerCreditTransferV04 fIToFICstmrCdtTrf) {
		FIToFICstmrCdtTrf = fIToFICstmrCdtTrf;
	}
	
	
}
