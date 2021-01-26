package urn.iso.std.iso20022.tech.xsd.pacs_002_001_05;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
/*@XmlRootElement(name = "documentoRespuesta", namespace = "http://www.bce.fin.ec/wsdl/srr/VentanillaCompartidaSRRWS")
@XmlType(name = "documentoRespuesta", namespace = "http://www.bce.fin.ec/wsdl/srr/VentanillaCompartidaSRRWS")*/
@XmlRootElement(name = "documentoRespuesta", namespace = "http://www.bce.fin.ec/wsdl/srr/VentanillaCompartidaSRRWS")
@XmlType(name = "documentoRespuesta", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.05")
public class DocumentRespuesta implements Serializable{

	private FIToFIPaymentStatusReportV05 FIToFIPmtStsRpt;

	public FIToFIPaymentStatusReportV05 getFIToFIPmtStsRpt() {
		return FIToFIPmtStsRpt;
	}

	public void setFIToFIPmtStsRpt(FIToFIPaymentStatusReportV05 fIToFIPmtStsRpt) {
		FIToFIPmtStsRpt = fIToFIPmtStsRpt;
	}
	
}
