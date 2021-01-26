package urn.iso.std.iso20022.tech.xsd.pacs_004_001_04;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "documentoSolicitud", namespace = "http://www.bce.fin.ec/wsdl/snp/ReversoTecnicoSNPWS")
@XmlType(name = "documentoSolicitud", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.004.001.04")
public class DocumentReverso implements Serializable{

	private PaymentReturnV04 PmtRtr;

	public PaymentReturnV04 getPmtRtr() {
		return PmtRtr;
	}

	public void setPmtRtr(PaymentReturnV04 pmtRtr) {
		PmtRtr = pmtRtr;
	}
	
}
