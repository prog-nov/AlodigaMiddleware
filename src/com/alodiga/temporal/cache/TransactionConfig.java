package com.alodiga.temporal.cache;

import java.io.Serializable;

import com.alodiga.middleware.logger.Logger;


public class TransactionConfig extends Thread implements Serializable  {

	private static final long serialVersionUID = -5719435359576196239L;
	private String proccode;
	private String proccodeDescription;
	private String proccodeDesShort;
	private int proccodestatus;
	private String canal_Cod;
	private String canal_Des;
	private int canal_status;
	private String proccodeTransactionFit;
	private String proccodeParams;
	private int proccodeTimeOutValue;
	private int proccodeReverFlag;
	private int net_Id;
	private String net_Descripcion;
	private String net_Tipo;
	private int net_Status;
	private String validTerm;
	private String user_Fit;
	private String validIp;
	private int trxNroPermission;
	private double trxCupoMax;
	private String term_Name;
	private double ammountDebit;
	private int isSaved;
	private String message_Class;
	private int store_Forward_Num;
	private String store_Forward_Type;
	private int store_Forward_Time;
	private int isLoged;
	private String notif_Mail;
	private String notif_Sms;
	private int alert_Trx;
	private String isNotif;
	private int trx_status;	
	
	public String getProccode() {
		return proccode;
	}
	public void setProccode(String proccode) {
		this.proccode = proccode;
	}
	public String getProccodeDescription() {
		return proccodeDescription;
	}
	public void setProccodeDescription(String proccodeDescription) {
		this.proccodeDescription = proccodeDescription;
	}
	public String getProccodeDesShort() {
		return proccodeDesShort;
	}
	public void setProccodeDesShort(String proccodeDesShort) {
		this.proccodeDesShort = proccodeDesShort;
	}
	public int getProccodestatus() {
		return proccodestatus;
	}
	public void setProccodestatus(int proccodestatus) {
		this.proccodestatus = proccodestatus;
	}
	public String getCanal_Cod() {
		return canal_Cod;
	}
	public void setCanal_Cod(String canal_Cod) {
		this.canal_Cod = canal_Cod;
	}
	public String getCanal_Des() {
		return canal_Des;
	}
	public void setCanal_Des(String canal_Des) {
		this.canal_Des = canal_Des;
	}
	public int getCanal_status() {
		return canal_status;
	}
	public void setCanal_status(int canal_status) {
		this.canal_status = canal_status;
	}
	public String getProccodeTransactionFit() {
		return proccodeTransactionFit;
	}
	public void setProccodeTransactionFit(String proccodeTransactionFit) {
		this.proccodeTransactionFit = proccodeTransactionFit;
	}
	public String getProccodeParams() {
		return proccodeParams;
	}
	public void setProccodeParams(String proccodeParams) {
		this.proccodeParams = proccodeParams;
	}
	public int getProccodeTimeOutValue() {
		return proccodeTimeOutValue;
	}
	public void setProccodeTimeOutValue(int proccodeTimeOutValue) {
		this.proccodeTimeOutValue = proccodeTimeOutValue;
	}
	public int getProccodeReverFlag() {
		return proccodeReverFlag;
	}
	public void setProccodeReverFlag(int proccodeReverFlag) {
		this.proccodeReverFlag = proccodeReverFlag;
	}
	public int getNet_Id() {
		return net_Id;
	}
	public void setNet_Id(int net_Id) {
		this.net_Id = net_Id;
	}
	public String getNet_Descripcion() {
		return net_Descripcion;
	}
	public void setNet_Descripcion(String net_Descripcion) {
		this.net_Descripcion = net_Descripcion;
	}
	public String getNet_Tipo() {
		return net_Tipo;
	}
	public void setNet_Tipo(String net_Tipo) {
		this.net_Tipo = net_Tipo;
	}
	public int getNet_Status() {
		return net_Status;
	}
	public void setNet_Status(int net_Status) {
		this.net_Status = net_Status;
	}
	public String getValidTerm() {
		return validTerm;
	}
	public void setValidTerm(String validTerm) {
		this.validTerm = validTerm;
	}
	public String getUser_Fit() {
		return user_Fit;
	}
	public void setUser_Fit(String user_Fit) {
		this.user_Fit = user_Fit;
	}
	public String getValidIp() {
		return validIp;
	}
	public void setValidIp(String validIp) {
		this.validIp = validIp;
	}
	public int getTrxNroPermission() {
		return trxNroPermission;
	}
	public void setTrxNroPermission(int trxNroPermission) {
		this.trxNroPermission = trxNroPermission;
	}
	public double getTrxCupoMax() {
		return trxCupoMax;
	}
	public void setTrxCupoMax(double trxCupoMax) {
		this.trxCupoMax = trxCupoMax;
	}
	public String getTerm_Name() {
		return term_Name;
	}
	public void setTerm_Name(String term_Name) {
		this.term_Name = term_Name;
	}
	public double getAmmountDebit() {
		return ammountDebit;
	}
	public void setAmmountDebit(double ammountDebit) {
		this.ammountDebit = ammountDebit;
	}
	public int getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(int isSaved) {
		this.isSaved = isSaved;
	}
	public String getMessage_Class() {
		return message_Class;
	}
	public void setMessage_Class(String message_Class) {
		this.message_Class = message_Class;
	}
	public int getStore_Forward_Num() {
		return store_Forward_Num;
	}
	public void setStore_Forward_Num(int store_Forward_Num) {
		this.store_Forward_Num = store_Forward_Num;
	}
	public String getStore_Forward_Type() {
		return store_Forward_Type;
	}
	public void setStore_Forward_Type(String store_Forward_Type) {
		this.store_Forward_Type = store_Forward_Type;
	}
	public int getStore_Forward_Time() {
		return store_Forward_Time;
	}
	public void setStore_Forward_Time(int store_Forward_Time) {
		this.store_Forward_Time = store_Forward_Time;
	}
	public int getIsLoged() {
		return isLoged;
	}
	public void setIsLoged(int isLoged) {
		this.isLoged = isLoged;
	}
	public String getNotif_Mail() {
		return notif_Mail;
	}
	public void setNotif_Mail(String notif_Mail) {
		this.notif_Mail = notif_Mail;
	}
	public String getNotif_Sms() {
		return notif_Sms;
	}
	public void setNotif_Sms(String notif_Sms) {
		this.notif_Sms = notif_Sms;
	}
	public int getAlert_Trx() {
		return alert_Trx;
	}
	public void setAlert_Trx(int alert_Trx) {
		this.alert_Trx = alert_Trx;
	}
	public String getIsNotif() {
		return isNotif;
	}
	public void setIsNotif(String isNotif) {
		this.isNotif = isNotif;
	}
	public int getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(int trx_status) {
		this.trx_status = trx_status;
	}
	public TransactionConfig() {
		super();
		this.proccodeReverFlag = 0;
	}
	public TransactionConfig(String Proccode, int NetId, String CanalCod, double AmmountDebit){		
		TransactionConfig tt = new TransactionConfig();
		
		if(tt != null){			
	        this.proccode = tt.getProccode();
	    	this.proccodeDescription = tt.getProccodeDescription();
	    	this.proccodeDesShort = tt.getProccodeDesShort();
	    	this.proccodestatus = tt.getProccodestatus();
	    	this.canal_Cod = tt.getCanal_Cod();
	    	this.canal_Des = tt.getCanal_Des();
	    	this.canal_status = tt.getCanal_status();
	    	this.proccodeTransactionFit = tt.getProccodeTransactionFit();
	    	this.proccodeParams = tt.getProccodeParams();
	    	this.proccodeTimeOutValue = tt.getProccodeTimeOutValue();
	    	this.proccodeReverFlag = tt.getProccodeReverFlag();
	    	this.net_Id = tt.getNet_Id();
	    	this.net_Descripcion = tt.getNet_Descripcion();
	    	this.net_Status = tt.getNet_Status();
	    	this.net_Tipo =  tt.getNet_Tipo();
	    	this.validTerm = tt.getValidTerm();
	    	this.user_Fit =  tt.getUser_Fit();
	    	this.validIp = tt.getValidIp();
	    	this.trxNroPermission = tt.getTrxNroPermission();
	    	this.trxCupoMax = tt.getTrxCupoMax();
	    	this.term_Name = tt.getTerm_Name();
	    	this.ammountDebit = tt.getAmmountDebit();
	    	this.isSaved = tt.getIsSaved();
	    	this.message_Class = tt.getMessage_Class();
	    	this.store_Forward_Num =  tt.getStore_Forward_Num();
	    	this.store_Forward_Type = tt.getStore_Forward_Type();
	    	this.store_Forward_Time = tt.getStore_Forward_Time();
	    	this.isLoged =  tt.getIsLoged();
	    	this.notif_Mail = tt.getNotif_Mail();
	    	this.notif_Sms =  tt.getNotif_Sms();
	    	this.alert_Trx = tt.getAlert_Trx();
	    	this.isNotif = tt.getIsNotif();
	    	this.trx_status = tt.getTrx_status();
		}
	}

	
	
}
