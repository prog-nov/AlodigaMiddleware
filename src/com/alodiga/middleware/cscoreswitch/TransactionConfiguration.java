package com.alodiga.middleware.cscoreswitch;

import java.io.Serializable;
import java.util.Date;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.temporal.cache.MemoryGlobal;
import com.alodiga.temporal.cache.TransactionConfig;


public class TransactionConfiguration extends TransactionConfig implements Cloneable, Serializable {

	private static final long serialVersionUID = 1535578478722038796L;
	private String commonCodSursal;
	private String commonCodOficina;
	private String commonRol;
	private String commonIdioma;
	private String commonArea;
	private String commonTPP;
	private String commonCompania;
	private String commonSesion;
	private String commonTrxReverso;
	private String commonFechaContable;
	private String Ip;
	private boolean isInserted;
	/*Atributos para reverso*/
	private String revRetrievalIso011fromBdd;
	private String proccodeReverseFitOriginal;
	private Date timeProcessingStoreAndForward;
	/*Atributos para paginacion y ordenacion de Detail FitBank*/
	private String propDetailBlq;
	private String propDetailMpg;
	private String propDetailNpg;
	private String propDetailNrg;
	private String propDetailCriterionOrdBy;
	
	public String getIp() {
		return Ip;
	}
	public Date getTimeProcessingStoreAndForward() {
		return timeProcessingStoreAndForward;
	}
	public void setTimeProcessingStoreAndForward(Date timeProcessingStoreAndForward) {
		this.timeProcessingStoreAndForward = timeProcessingStoreAndForward;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getCommonCodSursal() {
		return commonCodSursal;
	}
	public void setCommonCodSursal(String commonCodSursal) {
		this.commonCodSursal = commonCodSursal;
	}
	public String getCommonCodOficina() {
		return commonCodOficina;
	}
	public void setCommonCodOficina(String commonCodOficina) {
		this.commonCodOficina = commonCodOficina;
	}
	public String getCommonRol() {
		return commonRol;
	}
	public void setCommonRol(String commonRol) {
		this.commonRol = commonRol;
	}
	public String getCommonIdioma() {
		return commonIdioma;
	}
	public void setCommonIdioma(String commonIdioma) {
		this.commonIdioma = commonIdioma;
	}
	public String getCommonArea() {
		return commonArea;
	}
	public void setCommonArea(String commonArea) {
		this.commonArea = commonArea;
	}
	public String getCommonTPP() {
		return commonTPP;
	}
	public void setCommonTPP(String commonTPP) {
		this.commonTPP = commonTPP;
	}
	public String getCommonCompania() {
		return commonCompania;
	}
	public void setCommonCompania(String commonCompania) {
		this.commonCompania = commonCompania;
	}
	public String getCommonSesion() {
		return commonSesion;
	}
	public void setCommonSesion(String commonSesion) {
		this.commonSesion = commonSesion;
	}
	public String getCommonTrxReverso() {
		return commonTrxReverso;
	}
	public void setCommonTrxReverso(String commonTrxReverso) {
		this.commonTrxReverso = commonTrxReverso;
	}
	public String getCommonFechaContable() {
		return commonFechaContable;
	}
	public void setCommonFechaContable(String commonFechaContable) {
		this.commonFechaContable = commonFechaContable;
	}
	
	public String getRevRetrievalIso011fromBdd() {
		return revRetrievalIso011fromBdd;
	}
	public void setRevRetrievalIso011fromBdd(String revRetrievalIso011fromBdd) {
		this.revRetrievalIso011fromBdd = revRetrievalIso011fromBdd;
	}
	
	public String getProccodeReverseFitOriginal() {
		return proccodeReverseFitOriginal;	
	}
	public void setProccodeReverseFitOriginal(String proccodeReverseFitOriginal) {
		this.proccodeReverseFitOriginal = proccodeReverseFitOriginal;
	}
	public boolean isInserted() {
		return isInserted;
	}
	public void setInserted(boolean isInserted) {
		this.isInserted = isInserted;
	}
	
	public String getPropDetailBlq() {
		return propDetailBlq;
	}
	public void setPropDetailBlq(String propDetailBlq) {
		this.propDetailBlq = propDetailBlq;
	}
	public String getPropDetailMpg() {
		return propDetailMpg;
	}
	public void setPropDetailMpg(String propDetailMpg) {
		this.propDetailMpg = propDetailMpg;
	}
	public String getPropDetailNpg() {
		return propDetailNpg;
	}
	public void setPropDetailNpg(String propDetailNpg) {
		this.propDetailNpg = propDetailNpg;
	}
	public String getPropDetailNrg() {
		return propDetailNrg;
	}
	public void setPropDetailNrg(String propDetailNrg) {
		this.propDetailNrg = propDetailNrg;
	}
	public String getPropDetailCriterionOrdBy() {
		return propDetailCriterionOrdBy;
	}
	public void setPropDetailCriterionOrdBy(String propDetailCriterionOrdBy) {
		this.propDetailCriterionOrdBy = propDetailCriterionOrdBy;
	}
	public TransactionConfiguration() {
		super();
		if(MemoryGlobal.flagSystemReady){
			this.setCommonArea(MemoryGlobal.commonArea);
			this.setCommonCodOficina(MemoryGlobal.commonCodOficina);
			this.setCommonCodSursal(MemoryGlobal.commonCodSursal);
			this.setCommonCompania(MemoryGlobal.commonCompania);
			this.setCommonFechaContable(MemoryGlobal.commonFechaContable);
			this.setCommonIdioma(MemoryGlobal.commonIdioma);
			this.setCommonRol(MemoryGlobal.commonRol);
			this.setCommonSesion(MemoryGlobal.commonSesion);
			this.setCommonTPP(MemoryGlobal.commonTPP);
			this.setCommonTrxReverso(MemoryGlobal.commonTrxReverso);	
			this.isInserted = false;
		}				
	}
	public TransactionConfiguration(TransactionConfig config){
		this();
		Logger log = null;
		try {
			
			this.setCanal_Cod(config.getCanal_Cod());
			this.setCanal_status(config.getCanal_status());
			this.setCanal_Des(config.getCanal_Des());
			this.setAlert_Trx(config.getAlert_Trx());
			this.setAmmountDebit(config.getAmmountDebit());
			this.setIsLoged(config.getIsLoged());
			this.setIsNotif(config.getIsNotif());
			this.setIsSaved(config.getIsSaved());
			this.setMessage_Class(config.getMessage_Class());
			this.setNet_Descripcion(config.getNet_Descripcion());
			this.setNet_Id(config.getNet_Id());
			this.setNet_Tipo(config.getNet_Tipo());
			this.setNet_Status(config.getNet_Status());
			this.setNotif_Mail(config.getNotif_Mail());
			this.setNotif_Sms(config.getNotif_Sms());
			this.setProccode(config.getProccode());
			this.setProccodeDescription(config.getProccodeDescription());
			this.setProccodeDesShort(config.getProccodeDesShort());
			this.setProccodestatus(config.getProccodestatus());
			this.setProccodeParams(config.getProccodeParams());
			this.setProccodeReverFlag(config.getProccodeReverFlag());
			this.setProccodeTimeOutValue(config.getProccodeTimeOutValue());
			this.setProccodeTransactionFit(config.getProccodeTransactionFit());
			this.setStore_Forward_Num(config.getStore_Forward_Num());
			this.setStore_Forward_Time(config.getStore_Forward_Time());
			this.setStore_Forward_Type(config.getStore_Forward_Type());
			this.setTrxCupoMax(config.getTrxCupoMax());
			this.setTrxNroPermission(config.getTrxNroPermission());
			this.setUser_Fit(config.getUser_Fit());
			this.setValidIp(config.getValidIp());
			this.setValidTerm(config.getValidTerm());
			this.setTerm_Name(config.getTerm_Name());	
			this.setTrx_status(config.getTrx_status());
		} catch (Exception e) {	
			log = new Logger();
			log.WriteLogMonitor("Error modulo TransactionConfiguration::TransactionConfiguration(TransactionConfig config) [Constructor]", TypeMonitor.error, e);
			this.setProccode("-1");
			this.setProccodeDescription("ERROR " + e.getMessage().toString().toUpperCase());			
		}
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
	public TransactionConfig CloneObject(){		
		TransactionConfiguration clone = new TransactionConfiguration(this);
		return clone;
	}
	
	
	
}
