package com.alodiga.middleware.message.internal;

import java.sql.Date;

public class PurchagePinRequest {
	
    String cardNumber;
    String cardHolder;
    String CVV;
    String cardDueDate;
    Long messageMiddlewareId;
    Integer transactionTypeId;
    Integer channelId;                      
    Date transactionDate;
    String localTimeTransaction;
    String acquirerTerminalCodeId;
    String transactionNumberAcquirer;
    String acquirerCountryId;
    Float purchaseAmount;
    String documentNumber;
    String pinBlock;
    String ARQC;
    String terminalId;
    String oPMode;
    String schemeEMV;
    String seqNumber;
    String atc;
    String unpredictableNumber;
    String transactionData;
    String tradeName;
    
    
	public PurchagePinRequest() {
		
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public String getCardDueDate() {
		return cardDueDate;
	}
	public void setCardDueDate(String cardDueDate) {
		this.cardDueDate = cardDueDate;
	}
	public Long getMessageMiddlewareId() {
		return messageMiddlewareId;
	}
	public void setMessageMiddlewareId(Long messageMiddlewareId) {
		this.messageMiddlewareId = messageMiddlewareId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getLocalTimeTransaction() {
		return localTimeTransaction;
	}
	public void setLocalTimeTransaction(String localTimeTransaction) {
		this.localTimeTransaction = localTimeTransaction;
	}
	public String getAcquirerTerminalCodeId() {
		return acquirerTerminalCodeId;
	}
	public void setAcquirerTerminalCodeId(String acquirerTerminalCodeId) {
		this.acquirerTerminalCodeId = acquirerTerminalCodeId;
	}
	public String getAcquirerCountryId() {
		return acquirerCountryId;
	}
	public void setAcquirerCountryId(String acquirerCountryId) {
		this.acquirerCountryId = acquirerCountryId;
	}
	public Float getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(Float purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getPinBlock() {
		return pinBlock;
	}
	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
	}
	public String getARQC() {
		return ARQC;
	}
	public void setARQC(String aRQC) {
		ARQC = aRQC;
	}
	public String getoPMode() {
		return oPMode;
	}
	public void setoPMode(String oPMode) {
		this.oPMode = oPMode;
	}
	public String getSchemeEMV() {
		return schemeEMV;
	}
	public void setSchemeEMV(String schemeEMV) {
		this.schemeEMV = schemeEMV;
	}
	public String getAtc() {
		return atc;
	}
	public void setAtc(String atc) {
		this.atc = atc;
	}
	public Integer getTransactionTypeId() {
		return transactionTypeId;
	}
	public void setTransactionTypeId(Integer transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionNumberAcquirer() {
		return transactionNumberAcquirer;
	}
	public void setTransactionNumberAcquirer(String transactionNumberAcquirer) {
		this.transactionNumberAcquirer = transactionNumberAcquirer;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getUnpredictableNumber() {
		return unpredictableNumber;
	}
	public void setUnpredictableNumber(String unpredictableNumber) {
		this.unpredictableNumber = unpredictableNumber;
	}
	public String getTransactionData() {
		return transactionData;
	}
	public void setTransactionData(String transactionData) {
		this.transactionData = transactionData;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
    
    

}
