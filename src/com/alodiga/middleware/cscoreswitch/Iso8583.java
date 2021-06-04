package com.alodiga.middleware.cscoreswitch;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlRootElement;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.utils.FormatUtils;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.StringUtils;
import com.thoughtworks.xstream.XStream;


@XmlRootElement
public class Iso8583 implements Serializable, Cloneable {

	private static final long serialVersionUID = 1436076052703387344L;
	private String ISO_000_Message_Type;
	private String ISO_BitMap;
	private String ISO_002_PAN;
	private String ISO_003_ProcessingCode;
	private double ISO_004_AmountTransaction;
	private double ISO_006_BillAmount;
	private Date ISO_007_TransDatetime;
	private double ISO_008_BillFeeAmount;
	private String ISO_011_SysAuditNumber;	
	private Date ISO_012_LocalDatetime;
	private Date ISO_013_LocalDate;
	private Date ISO_015_SettlementDatel;
	private String ISO_018_MerchantType;
	private String ISO_019_AcqCountryCode;
	private String ISO_022_PosEntryMode;
	private String ISO_023_CardSeq;
	private String ISO_024_NetworkId;
	//ok 

	
	
	
	private double ISO_028_TranFeeAmount;
	private double ISO_029_SettlementFee;
	private double ISO_030_ProcFee;
	private String ISO_032_ACQInsID;
	private String ISO_033_FWDInsID;
	private String ISO_034_PANExt;
	private String ISO_035_Track2;
	private String ISO_036_Track3;
	private String ISO_037_RetrievalReferenceNumber;
	private String ISO_038_AutorizationNumber;
	private String ISO_039_ResponseCode;
	private String ISO_039p_ResponseDetail;
	private String ISO_041_CardAcceptorID;
	private String ISO_042_Card_Acc_ID_Code;
	private String ISO_043_CardAcceptorLoc;
	private String ISO_044_AddRespData;
	private double ISO_049_TranCurrCode;
	private double ISO_051_CardCurrCode;
	private String ISO_052_PinBlock;
	private String ISO_054_AditionalAmounts;
	private String ISO_055_EMV;
	private String ISO_090_OriginalData;
	private String ISO_102_AccountID_1;
	private String ISO_103_AccountID_2;
	private String ISO_104_TranDescription;
	private String ISO_114_ExtendedData;
	private String ISO_115_ExtendedData;
	private String ISO_120_ExtendedData;
	private String ISO_121_ExtendedData;
	private String ISO_122_ExtendedData;
	private String ISO_123_ExtendedData;
	private String ISO_124_ExtendedData;	
	
	
	public Iso8583(String messagetype, String procCode, String canalCode, String networkId){
		this();
		
		this.ISO_000_Message_Type = messagetype;
		this.ISO_003_ProcessingCode = procCode;
		this.ISO_018_MerchantType = canalCode;
		this.ISO_024_NetworkId = networkId;
		this.ISO_011_SysAuditNumber = GeneralUtils.GetSecuencial(8);
		this.ISO_041_CardAcceptorID = "USERMIDD";
	}
	
	public String getISO_000_Message_Type() {
		return ISO_000_Message_Type;
	}

	public void setISO_000_Message_Type(String iSO_000_Message_Type) {
		ISO_000_Message_Type = iSO_000_Message_Type;
	}

	public String getISO_BitMap() {
		return ISO_BitMap;
	}

	public void setISO_BitMap(String iSO_BitMap) {
		ISO_BitMap = iSO_BitMap;
	}

	public String getISO_002_PAN() {
		return ISO_002_PAN;
	}

	public void setISO_002_PAN(String iSO_002_PAN) {
		ISO_002_PAN = iSO_002_PAN;
	}

	public String getISO_003_ProcessingCode() {
		return ISO_003_ProcessingCode;
	}

	public void setISO_003_ProcessingCode(String iSO_003_ProcessingCode) {
		ISO_003_ProcessingCode = iSO_003_ProcessingCode;
	}

	public double getISO_004_AmountTransaction() {
		return ISO_004_AmountTransaction;
	}

	public void setISO_004_AmountTransaction(double iSO_004_AmountTransaction) {
		ISO_004_AmountTransaction = iSO_004_AmountTransaction;
	}

	public double getISO_006_BillAmount() {
		return ISO_006_BillAmount;
	}

	public void setISO_006_BillAmount(double iSO_006_BillAmount) {
		ISO_006_BillAmount = iSO_006_BillAmount;
	}

	public Date getISO_007_TransDatetime() {
		return ISO_007_TransDatetime;
	}

	public void setISO_007_TransDatetime(Date iSO_007_TransDatetime) {
		ISO_007_TransDatetime = iSO_007_TransDatetime;
	}

	public double getISO_008_BillFeeAmount() {
		return ISO_008_BillFeeAmount;
	}

	public void setISO_008_BillFeeAmount(double iSO_008_BillFeeAmount) {
		ISO_008_BillFeeAmount = iSO_008_BillFeeAmount;
	}

	public String getISO_011_SysAuditNumber() {
		return ISO_011_SysAuditNumber;
	}

	public void setISO_011_SysAuditNumber(String iSO_011_SysAuditNumber) {
		ISO_011_SysAuditNumber = iSO_011_SysAuditNumber;
	}

	public Date getISO_012_LocalDatetime() {
		return ISO_012_LocalDatetime;
	}

	public void setISO_012_LocalDatetime(Date iSO_012_LocalDatetime) {
		ISO_012_LocalDatetime = iSO_012_LocalDatetime;
	}

	public Date getISO_013_LocalDate() {
		return ISO_013_LocalDate;
	}

	public void setISO_013_LocalDate(Date iSO_013_LocalDate) {
		ISO_013_LocalDate = iSO_013_LocalDate;
	}

	public Date getISO_015_SettlementDatel() {
		return ISO_015_SettlementDatel;
	}

	public void setISO_015_SettlementDatel(Date iSO_015_SettlementDatel) {
		ISO_015_SettlementDatel = iSO_015_SettlementDatel;
	}

	public String getISO_018_MerchantType() {
		return ISO_018_MerchantType;
	}

	public void setISO_018_MerchantType(String iSO_018_MerchantType) {
		ISO_018_MerchantType = iSO_018_MerchantType;
	}

	public String getISO_019_AcqCountryCode() {
		return ISO_019_AcqCountryCode;
	}

	public void setISO_019_AcqCountryCode(String iSO_019_AcqCountryCode) {
		ISO_019_AcqCountryCode = iSO_019_AcqCountryCode;
	}

	public String getISO_022_PosEntryMode() {
		return ISO_022_PosEntryMode;
	}

	public void setISO_022_PosEntryMode(String iSO_022_PosEntryMode) {
		ISO_022_PosEntryMode = iSO_022_PosEntryMode;
	}

	public String getISO_023_CardSeq() {
		return ISO_023_CardSeq;
	}

	public void setISO_023_CardSeq(String iSO_023_CardSeq) {
		ISO_023_CardSeq = iSO_023_CardSeq;
	}

	public String getISO_024_NetworkId() {
		return ISO_024_NetworkId;
	}

	public void setISO_024_NetworkId(String iSO_024_NetworkId) {
		ISO_024_NetworkId = iSO_024_NetworkId;
	}

	public double getISO_028_TranFeeAmount() {
		return ISO_028_TranFeeAmount;
	}

	public void setISO_028_TranFeeAmount(double iSO_028_TranFeeAmount) {
		ISO_028_TranFeeAmount = iSO_028_TranFeeAmount;
	}

	public double getISO_029_SettlementFee() {
		return ISO_029_SettlementFee;
	}

	public void setISO_029_SettlementFee(double iSO_029_SettlementFee) {
		ISO_029_SettlementFee = iSO_029_SettlementFee;
	}

	public double getISO_030_ProcFee() {
		return ISO_030_ProcFee;
	}

	public void setISO_030_ProcFee(double iSO_030_ProcFee) {
		ISO_030_ProcFee = iSO_030_ProcFee;
	}

	public String getISO_032_ACQInsID() {
		return ISO_032_ACQInsID;
	}

	public void setISO_032_ACQInsID(String iSO_032_ACQInsID) {
		ISO_032_ACQInsID = iSO_032_ACQInsID;
	}

	public String getISO_033_FWDInsID() {
		return ISO_033_FWDInsID;
	}

	public void setISO_033_FWDInsID(String iSO_033_FWDInsID) {
		ISO_033_FWDInsID = iSO_033_FWDInsID;
	}

	public String getISO_034_PANExt() {
		return ISO_034_PANExt;
	}

	public void setISO_034_PANExt(String iSO_034_PANExt) {
		ISO_034_PANExt = iSO_034_PANExt;
	}

	public String getISO_035_Track2() {
		return ISO_035_Track2;
	}

	public void setISO_035_Track2(String iSO_035_Track2) {
		ISO_035_Track2 = iSO_035_Track2;
	}

	public String getISO_036_Track3() {
		return ISO_036_Track3;
	}

	public void setISO_036_Track3(String iSO_036_Track3) {
		ISO_036_Track3 = iSO_036_Track3;
	}

	public String getISO_037_RetrievalReferenceNumber() {
		return ISO_037_RetrievalReferenceNumber;
	}

	public void setISO_037_RetrievalReferenceNumber(
			String iSO_037_RetrievalReferenceNumber) {
		ISO_037_RetrievalReferenceNumber = iSO_037_RetrievalReferenceNumber;
	}

	public String getISO_038_AutorizationNumber() {
		return ISO_038_AutorizationNumber;
	}

	public void setISO_038_AutorizationNumber(String iSO_038_AutorizationNumber) {
		ISO_038_AutorizationNumber = iSO_038_AutorizationNumber;
	}

	public String getISO_039_ResponseCode() {
		return ISO_039_ResponseCode;
	}

	public void setISO_039_ResponseCode(String iSO_039_ResponseCode) {
		ISO_039_ResponseCode = iSO_039_ResponseCode;
	}

	public String getISO_039p_ResponseDetail() {
		return ISO_039p_ResponseDetail;
	}

	public void setISO_039p_ResponseDetail(String iSO_039p_ResponseDetail) {
		ISO_039p_ResponseDetail = iSO_039p_ResponseDetail;
	}

	public String getISO_041_CardAcceptorID() {
		return ISO_041_CardAcceptorID;
	}

	public void setISO_041_CardAcceptorID(String iSO_041_CardAcceptorID) {
		ISO_041_CardAcceptorID = iSO_041_CardAcceptorID;
	}

	public String getISO_042_Card_Acc_ID_Code() {
		return ISO_042_Card_Acc_ID_Code;
	}

	public void setISO_042_Card_Acc_ID_Code(String iSO_042_Card_Acc_ID_Code) {
		ISO_042_Card_Acc_ID_Code = iSO_042_Card_Acc_ID_Code;
	}

	public String getISO_043_CardAcceptorLoc() {
		return ISO_043_CardAcceptorLoc;
	}

	public void setISO_043_CardAcceptorLoc(String iSO_043_CardAcceptorLoc) {
		ISO_043_CardAcceptorLoc = iSO_043_CardAcceptorLoc;
	}

	public String getISO_044_AddRespData() {
		return ISO_044_AddRespData;
	}

	public void setISO_044_AddRespData(String iSO_044_AddRespData) {
		ISO_044_AddRespData = iSO_044_AddRespData;
	}

	public double getISO_049_TranCurrCode() {
		return ISO_049_TranCurrCode;
	}
	
	
	public void setISO_049_TranCurrCode(double iSO_049_TranCurrCode) {
		ISO_049_TranCurrCode = iSO_049_TranCurrCode;
	}

	public double getISO_051_CardCurrCode() {
		return ISO_051_CardCurrCode;
	}

	public void setISO_051_CardCurrCode(double iSO_051_CardCurrCode) {
		ISO_051_CardCurrCode = iSO_051_CardCurrCode;
	}

	public String getISO_052_PinBlock() {
		return ISO_052_PinBlock;
	}

	public void setISO_052_PinBlock(String iSO_052_PinBlock) {
		ISO_052_PinBlock = iSO_052_PinBlock;
	}

	public String getISO_054_AditionalAmounts() {
		return ISO_054_AditionalAmounts;
	}

	public void setISO_054_AditionalAmounts(String iSO_054_AditionalAmounts) {
		ISO_054_AditionalAmounts = iSO_054_AditionalAmounts;
	}

	public String getISO_055_EMV() {
		return ISO_055_EMV;
	}

	public void setISO_055_EMV(String iSO_055_EMV) {
		ISO_055_EMV = iSO_055_EMV;
	}

	public String getISO_090_OriginalData() {
		return ISO_090_OriginalData;
	}

	public void setISO_090_OriginalData(String iSO_090_OriginalData) {
		ISO_090_OriginalData = iSO_090_OriginalData;
	}

	public String getISO_102_AccountID_1() {
		return ISO_102_AccountID_1;
	}

	public void setISO_102_AccountID_1(String iSO_102_AccountID_1) {
		ISO_102_AccountID_1 = iSO_102_AccountID_1;
	}

	public String getISO_103_AccountID_2() {
		return ISO_103_AccountID_2;
	}

	public void setISO_103_AccountID_2(String iSO_103_AccountID_2) {
		ISO_103_AccountID_2 = iSO_103_AccountID_2;
	}

	public String getISO_104_TranDescription() {
		return ISO_104_TranDescription;
	}

	public void setISO_104_TranDescription(String iSO_104_TranDescription) {
		ISO_104_TranDescription = iSO_104_TranDescription;
	}

	public String getISO_114_ExtendedData() {
		return ISO_114_ExtendedData;
	}

	public void setISO_114_ExtendedData(String iSO_114_ExtendedData) {
		ISO_114_ExtendedData = iSO_114_ExtendedData;
	}

	public String getISO_115_ExtendedData() {
		return ISO_115_ExtendedData;
	}

	public void setISO_115_ExtendedData(String iSO_115_ExtendedData) {
		ISO_115_ExtendedData = iSO_115_ExtendedData;
	}

	public String getISO_120_ExtendedData() {
		return ISO_120_ExtendedData;
	}

	public void setISO_120_ExtendedData(String iSO_120_ExtendedData) {
		ISO_120_ExtendedData = iSO_120_ExtendedData;
	}

	public String getISO_121_ExtendedData() {
		return ISO_121_ExtendedData;
	}

	public void setISO_121_ExtendedData(String iSO_121_ExtendedData) {
		ISO_121_ExtendedData = iSO_121_ExtendedData;
	}

	public String getISO_122_ExtendedData() {
		return ISO_122_ExtendedData;
	}

	public void setISO_122_ExtendedData(String iSO_122_ExtendedData) {
		ISO_122_ExtendedData = iSO_122_ExtendedData;
	}

	public String getISO_123_ExtendedData() {
		return ISO_123_ExtendedData;
	}

	public void setISO_123_ExtendedData(String iSO_123_ExtendedData) {
		ISO_123_ExtendedData = iSO_123_ExtendedData;
	}

	public String getISO_124_ExtendedData() {
		return ISO_124_ExtendedData;
	}

	public void setISO_124_ExtendedData(String iSO_124_ExtendedData) {
		ISO_124_ExtendedData = iSO_124_ExtendedData;
	}

	public Iso8583(){
		
		XStream xs = new XStream();
		xs.autodetectAnnotations(true);
		this.ISO_000_Message_Type = StringUtils.Empty();
		this.ISO_002_PAN = StringUtils.Empty();
		this.ISO_003_ProcessingCode = StringUtils.Empty();
		this.ISO_004_AmountTransaction = 0;
		this.ISO_006_BillAmount = 0;
		this.ISO_007_TransDatetime = new Date();
		this.ISO_008_BillFeeAmount = 0;
		this.ISO_011_SysAuditNumber = StringUtils.Empty();
		this.ISO_012_LocalDatetime = new Date();
		this.ISO_013_LocalDate = new Date();
		this.ISO_015_SettlementDatel = new Date();
		this.ISO_018_MerchantType = StringUtils.Empty();
		this.ISO_019_AcqCountryCode = StringUtils.Empty();
		this.ISO_022_PosEntryMode = StringUtils.Empty();
		this.ISO_023_CardSeq = StringUtils.Empty();
		this.ISO_024_NetworkId = StringUtils.Empty();
		this.ISO_028_TranFeeAmount = 0;
		this.ISO_029_SettlementFee = 0;
		this.ISO_030_ProcFee = 0;
		this.ISO_032_ACQInsID = StringUtils.Empty();
		this.ISO_033_FWDInsID = StringUtils.Empty();
		this.ISO_034_PANExt = StringUtils.Empty();
		this.ISO_035_Track2 = StringUtils.Empty();
		this.ISO_036_Track3 = StringUtils.Empty();
		this.ISO_037_RetrievalReferenceNumber = StringUtils.Empty();
		this.ISO_038_AutorizationNumber = StringUtils.Empty();
		this.ISO_039_ResponseCode = "909";
		this.ISO_039p_ResponseDetail = "TRANSACCION INICIALIZADA";
		this.ISO_041_CardAcceptorID = StringUtils.Empty();
		this.ISO_042_Card_Acc_ID_Code = StringUtils.Empty();
		this.ISO_043_CardAcceptorLoc = StringUtils.Empty();
		this.ISO_044_AddRespData = StringUtils.Empty();
		this.ISO_049_TranCurrCode = 0;
		this.ISO_051_CardCurrCode = 0;
		this.ISO_052_PinBlock = StringUtils.Empty();
		this.ISO_054_AditionalAmounts = StringUtils.Empty();
		this.ISO_055_EMV = StringUtils.Empty();
		this.ISO_090_OriginalData = StringUtils.Empty();
		this.ISO_102_AccountID_1 = StringUtils.Empty();
		this.ISO_103_AccountID_2 = StringUtils.Empty();
		this.ISO_104_TranDescription = StringUtils.Empty();
		this.ISO_114_ExtendedData = StringUtils.Empty();
		this.ISO_115_ExtendedData = StringUtils.Empty();
		this.ISO_120_ExtendedData = StringUtils.Empty();
		this.ISO_121_ExtendedData = StringUtils.Empty();
		this.ISO_122_ExtendedData = StringUtils.Empty();
		this.ISO_123_ExtendedData = StringUtils.Empty();
		this.ISO_124_ExtendedData = StringUtils.Empty();
		this.ISO_BitMap = "FFFFFFFFFFFFFFFF";
	}
	
	public Iso8583(Iso8583Binary isoBin){
		
		this();
		Logger log = null;
		try {
			
			String mti = isoBin.getMti();
			switch (mti) {
			case "0220":
			case "0200":
				this.ISO_000_Message_Type = "1200";
				break;
			case "0800":
				this.ISO_000_Message_Type = "1800";
				break;
			case "0420":
			case "0400":
				this.ISO_000_Message_Type = "1400";
				break;
			case "0300":
				this.ISO_000_Message_Type = "1300";
				break;
			default:
				break;
			}
			byte[] prBitmap = isoBin.getPrimaryBitmap();
			
			//int lowNibble = prBitmap[0] & 0x0f; // segundo nibble
			int hiNibble = (prBitmap[0] >> 4) & 0x0f; // primer nibble
			if(hiNibble >= 8){
				this.ISO_BitMap = DatatypeConverter.printHexBinary(isoBin.getPrimaryBitmap());
				this.ISO_BitMap = this.ISO_BitMap + DatatypeConverter.printHexBinary(isoBin.getDe1_SecondaryBitmap());
			}else
				this.ISO_BitMap = DatatypeConverter.printHexBinary(prBitmap);
			this.ISO_002_PAN = isoBin.getDe2_PAN();
			
			if(this.ISO_000_Message_Type.equals("1800")){
				this.ISO_003_ProcessingCode = StringUtils.IsNullOrEmpty(isoBin.getDe3_ProcCode().trim())? "000000"
	                  				    :isoBin.getDe3_ProcCode(); //Mensaje de Control
			}else{
				
				this.ISO_003_ProcessingCode = isoBin.getDe3_ProcCode();
			}
			
			
			this.ISO_004_AmountTransaction = (Double.parseDouble(String.valueOf(Integer
					                        .parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe4_AmtTxn())?"0"
					                        :isoBin.getDe4_AmtTxn())))/100);
			this.ISO_006_BillAmount = (Double.parseDouble(String.valueOf(Integer
                    					.parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe6_AmtCardhBill()) ? "0"
                    							: isoBin.getDe6_AmtCardhBill())))/100);
			this.ISO_007_TransDatetime = FormatUtils.StringToDateIso(FormatUtils.DateToString(new Date(), "yyyy") + 
					                   isoBin.getDe7_TransDttm(), "yyyyMMddHHmmss");
			this.ISO_008_BillFeeAmount = (Double.parseDouble(String.valueOf(Integer
                    .parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe8_AmtCardhBillFee())?"0"
                    :isoBin.getDe8_AmtCardhBillFee())))/100);
			this.ISO_011_SysAuditNumber = isoBin.getDe11_STAN();
			this.ISO_012_LocalDatetime =  FormatUtils.StringToDateIso(FormatUtils.DateToString(new Date(), "yyyy")  + 
	                   isoBin.getDe13_DateLocal() + isoBin.getDe12_TimeLocal(), "yyyyMMddHHmmss");
			this.ISO_013_LocalDate = FormatUtils.StringToDateIso(FormatUtils.DateToString(new Date(), "yyyy") + 
	                   			isoBin.getDe13_DateLocal(), "yyyyMMdd");
			this.ISO_015_SettlementDatel = FormatUtils.StringToDateIso(FormatUtils.DateToString(new Date(), "yyyy") + 
           			isoBin.getDe15_DateSetl(), "yyyyMMdd");
			
			if(this.ISO_000_Message_Type.equals("1800")){
				this.ISO_018_MerchantType = StringUtils.IsNullOrEmpty(isoBin.getDe18_MerchType()
						                .replace("0000", ""))? "0001"
	                  				    :isoBin.getDe18_MerchType(); //Mensaje de Control
			}else{
				
				this.ISO_018_MerchantType = isoBin.getDe18_MerchType();
			}
			
			this.ISO_019_AcqCountryCode = isoBin.getDe19_AcqInstCtryCode();
			this.ISO_022_PosEntryMode = isoBin.getDe20_PriAccNumExtCtryCode();
			this.ISO_023_CardSeq = isoBin.getDe23_CardSeqNo();
			
			if(this.ISO_000_Message_Type.equals("1800")){
				this.ISO_024_NetworkId = StringUtils.IsNullOrEmpty(isoBin.getDe24_NetIntlId()
						.replace("000", ""))? "551"
					    :isoBin.getDe24_NetIntlId(); //Mensaje de Control
			}else{
				
				this.ISO_024_NetworkId = isoBin.getDe24_NetIntlId();
			}
			this.ISO_028_TranFeeAmount = (Double.parseDouble(String.valueOf(Integer
                    .parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe28_AmtTxnFee())?"0"
                    :isoBin.getDe28_AmtTxnFee())))/100);
			this.ISO_029_SettlementFee = (Double.parseDouble(String.valueOf(Integer
                    .parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe29_AmtSettleFee())?"0"
                    :isoBin.getDe29_AmtSettleFee())))/100);
			this.ISO_030_ProcFee = (Double.parseDouble(String.valueOf(Integer
                    .parseInt(StringUtils.IsNullOrEmpty(isoBin.getDe30_AmtTxnProcFee())?"0"
                    :isoBin.getDe30_AmtTxnProcFee())))/100);
			this.ISO_032_ACQInsID = isoBin.getDe32_AcqInstIdCode();
			this.ISO_033_FWDInsID = isoBin.getDe33_FwdInstIdCode();
			this.ISO_034_PANExt = isoBin.getDe34_PanExt();
			this.ISO_035_Track2 = GeneralUtils.isEmptyByteArray(isoBin.getDe35_Track2()) 
         		   				 ? StringUtils.Empty() :new String(isoBin.getDe35_Track2());
			this.ISO_036_Track3 =  GeneralUtils.isEmptyByteArray(isoBin.getDe36_Track3()) 
	   				             ? StringUtils.Empty() :new String(isoBin.getDe36_Track3());
			this.ISO_037_RetrievalReferenceNumber = isoBin.getDe37_RetRefNo();
			this.ISO_038_AutorizationNumber = isoBin.getDe38_AuthIdentResp();
			this.ISO_039_ResponseCode = StringUtils.padRight(isoBin.getDe39_RespCode(),3,"0");
			this.ISO_041_CardAcceptorID = isoBin.getDe41_CardAcptTermId();
			this.ISO_042_Card_Acc_ID_Code = isoBin.getDe42_CardAcptIdCode();
			this.ISO_043_CardAcceptorLoc = isoBin.getDe43_CardAcptNameLoc();
			this.ISO_044_AddRespData = GeneralUtils.isEmptyByteArray(isoBin.getDe44_AddtRespData()) 
	   				                  ? StringUtils.Empty() : new String(isoBin.getDe44_AddtRespData());
			this.ISO_049_TranCurrCode = Double.parseDouble(isoBin.getDe49_CurCodeTxn());
			this.ISO_052_PinBlock = DatatypeConverter.printHexBinary(isoBin.getDe52_PinData());
			this.ISO_054_AditionalAmounts = isoBin.getDe54_AddtlAmts();
			this.ISO_055_EMV = GeneralUtils.isEmptyByteArray(isoBin.getDe55_ResIso()) 
                    		   ? StringUtils.Empty() : new String(isoBin.getDe55_ResIso());
			this.ISO_090_OriginalData = isoBin.getDe90_OrigDataElem();
			this.ISO_102_AccountID_1 = GeneralUtils.isEmptyByteArray(isoBin.getDe102_AcctId1()) 
         		                       ? StringUtils.Empty() : new String(isoBin.getDe102_AcctId1());
			this.ISO_103_AccountID_2 = GeneralUtils.isEmptyByteArray(isoBin.getDe103_AcctId2()) 
                                       ? StringUtils.Empty() : new String(isoBin.getDe103_AcctId2());
			this.ISO_104_TranDescription = GeneralUtils.isEmptyByteArray(isoBin.getDe104_TxnDesc()) 
					                      ? StringUtils.Empty() : new String(isoBin.getDe104_TxnDesc());
			this.ISO_114_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe114_ResvNat()) 
					                      ? StringUtils.Empty() : new String(isoBin.getDe114_ResvNat());
			this.ISO_115_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe115_ResvNat()) 
					                      ? StringUtils.Empty() : new String(isoBin.getDe115_ResvNat());
			this.ISO_120_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe120_ResvPriv()) 
					                      ? StringUtils.Empty(): new String(isoBin.getDe120_ResvPriv());
			this.ISO_121_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe121_ResvPriv()) 
					                      ? StringUtils.Empty(): new String(isoBin.getDe121_ResvPriv());
			this.ISO_122_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe122_ResvPriv()) 
					                      ? StringUtils.Empty(): new String(isoBin.getDe122_ResvPriv());
			this.ISO_123_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe123_ResvPriv()) 
					                      ? StringUtils.Empty(): new String(isoBin.getDe123_ResvPriv());
			this.ISO_124_ExtendedData = GeneralUtils.isEmptyByteArray(isoBin.getDe124_ResvPriv()) 
					                      ? StringUtils.Empty(): new String(isoBin.getDe124_ResvPriv());
	        if(this.ISO_000_Message_Type.equals("1800")){
	        	
	        	this.ISO_115_ExtendedData = isoBin.getDe70_NetMgtInfoCode();
	        }
	        	
			
			
		} catch (Exception e) {
			
			this.ISO_039_ResponseCode = "999";
			this.ISO_039p_ResponseDetail = GeneralUtils.ExceptionToString("EROR CONSTRUCTOR ISO ", e);
			log = new Logger();
			log.WriteLogMonitor("ERROR CONSTRUCTOR Iso8583(Iso8583Binary isoBin)", TypeMonitor.error, e);
			
		}
	}
	
	public Iso8583(wIso8583 iso){
		
		Logger log = null;
		try {
			
			this.ISO_000_Message_Type = iso.getISO_000_Message_Type();
			this.ISO_002_PAN = iso.getISO_002_PAN();
			this.ISO_003_ProcessingCode = iso.getISO_003_ProcessingCode();
			this.ISO_004_AmountTransaction = iso.getISO_004_AmountTransaction();
			this.ISO_006_BillAmount = iso.getISO_006_BillAmount();
			this.ISO_007_TransDatetime = iso.getISO_007_TransDatetime();
			this.ISO_008_BillFeeAmount = iso.getISO_008_BillFeeAmount();
			this.ISO_011_SysAuditNumber = iso.getISO_011_SysAuditNumber();
			this.ISO_012_LocalDatetime = iso.getISO_012_LocalDatetime();
			this.ISO_013_LocalDate = iso.getISO_013_LocalDate();
			this.ISO_015_SettlementDatel = iso.getISO_015_SettlementDatel();
			this.ISO_018_MerchantType = iso.getISO_018_MerchantType();
			this.ISO_019_AcqCountryCode = iso.getISO_019_AcqCountryCode();
			this.ISO_022_PosEntryMode = iso.getISO_022_PosEntryMode();
			this.ISO_023_CardSeq = iso.getISO_023_CardSeq();
			this.ISO_024_NetworkId = iso.getISO_024_NetworkId();
			this.ISO_028_TranFeeAmount = iso.getISO_028_TranFeeAmount();
			this.ISO_029_SettlementFee = iso.getISO_029_SettlementFee();
			this.ISO_030_ProcFee = iso.getISO_030_ProcFee();
			this.ISO_032_ACQInsID = iso.getISO_032_ACQInsID();
			this.ISO_033_FWDInsID = iso.getISO_033_FWDInsID();
			this.ISO_034_PANExt = iso.getISO_034_PANExt();
			this.ISO_035_Track2 = iso.getISO_035_Track2();
			this.ISO_036_Track3 = iso.getISO_036_Track3();
			this.ISO_037_RetrievalReferenceNumber = iso.getISO_037_RetrievalReferenceNumber();
			this.ISO_038_AutorizationNumber = iso.getISO_038_AutorizationNumber();
			this.ISO_039_ResponseCode = iso.getISO_039_ResponseCode();
			this.ISO_039p_ResponseDetail = iso.getISO_039p_ResponseDetail();
			this.ISO_041_CardAcceptorID = iso.getISO_041_CardAcceptorID();
			this.ISO_042_Card_Acc_ID_Code = iso.getISO_042_Card_Acc_ID_Code();
			this.ISO_043_CardAcceptorLoc = iso.getISO_043_CardAcceptorLoc();
			this.ISO_044_AddRespData = iso.getISO_044_AddRespData();
			this.ISO_049_TranCurrCode = iso.getISO_049_TranCurrCode();
			this.ISO_051_CardCurrCode = iso.getISO_051_CardCurrCode();
			this.ISO_052_PinBlock = iso.getISO_052_PinBlock();
			this.ISO_054_AditionalAmounts = iso.getISO_054_AditionalAmounts();
			this.ISO_055_EMV = iso.getISO_055_EMV();
			this.ISO_090_OriginalData = iso.getISO_090_OriginalData();
			this.ISO_102_AccountID_1 = iso.getISO_102_AccountID_1();
			this.ISO_103_AccountID_2 = iso.getISO_103_AccountID_2();
			this.ISO_104_TranDescription = iso.getISO_104_TranDescription();
			this.ISO_114_ExtendedData = iso.getISO_114_ExtendedData();
			this.ISO_115_ExtendedData = iso.getISO_115_ExtendedData();
			this.ISO_120_ExtendedData = iso.getISO_120_ExtendedData();
			this.ISO_121_ExtendedData = iso.getISO_121_ExtendedData();
			this.ISO_122_ExtendedData = iso.getISO_122_ExtendedData();
			this.ISO_123_ExtendedData = iso.getISO_123_ExtendedData();
			this.ISO_124_ExtendedData = iso.getISO_124_ExtendedData();			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("ERROR CONSTRUCTOR Iso8583(wIso8583 iso)", TypeMonitor.error, e);
		}
	}
	public <T> Iso8583(T obj){
		
	
	}
	@Override
	public Object clone() throws CloneNotSupportedException {		
		return super.clone();
		
	}
	
}

