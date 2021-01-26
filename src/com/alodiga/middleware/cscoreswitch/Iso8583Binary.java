package com.alodiga.middleware.cscoreswitch;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.Ref;
import com.alodiga.middleware.utils.StringUtils;

public class Iso8583Binary implements Serializable{

	
	private static final long serialVersionUID = -8207289862022741664L;
	
	private String mti;
    private byte[] primaryBitmap = { 0, 0, 0, 0, 0, 0, 0, 0 };
    private byte[] de1_SecondaryBitmap = { 0, 0, 0, 0, 0, 0, 0, 0 };        // DE1: Bitmap, extended (b64)
    private String de2_PAN;        // DE2: PAN (n19)
    private String de3_ProcCode;        // DE3: Processing Code (n6)
    private String de4_AmtTxn;        // DE4: Amount, Txn (n12)
    private String de5_AmtSettle;        // DE5: Amount, Settlement (n12)
    private String de6_AmtCardhBill;        // DE6: Amount,Cardholder billing (n12)
    private String de7_TransDttm;        // DE7: Transmission date and time (n10)
    private String de8_AmtCardhBillFee;        // DE8: Amount, Cardholder billing fee (n8 )
    private String de9_ConvRateSettle;        // DE9: Conversion rate, settlement (n8 )
    private String de10_ConvRateCardhBill;        // DE10: Conversion rate, cardh bill (n8 )
    private String de11_STAN;        // DE11: STAN (n6)
    private String de12_TimeLocal;        // DE12: Time, Local txn (n6)
    private String de13_DateLocal;        // DE13: Date, Local txn (n4)
    private String de14_DateExpiry;        // DE14: Date, expiry (n4)
    private String de15_DateSetl;        // DE15: Date, Settlement (n4)
    private String de16_DateConv;        // DE16: Date, Conversion (n4)
    private String de17_DateCapt;        // DE17: Date, Capture (n4)
    private String de18_MerchType;        // DE18: Merchants type (n4)
    private String de19_AcqInstCtryCode;        // DE19: Acquiring Inst Count code (n3)
    private String de20_PriAccNumExtCtryCode;        // DE20: Prim Acc Num Ext, Count Code (n3)
    private String de21_FwdInstCtryCode;        // DE21: Forwarding inst count code (n3)
    private String de22_PosEntryMode;        // DE22: POS entry mode (n3)
    private String de23_CardSeqNo;        // DE23: Card Seq No (n3)
    private String de24_NetIntlId;        // DE24: Net Intl Id (n3)
    private String de25_PosCondCode;        // DE25: POS  Cond Code (n2)
    private String de26_PosPinCaptCode;        // DE26: POS PIN Capture code (n2)
    private String de27_AuthIdRespLen;        // DE27: Auth Ident Resp Len (n1)
    private String de28_AmtTxnFee;        // DE28: Amount, Txn fee (xn8 )
    private String de29_AmtSettleFee;        // DE29: Amount, Settlment fee (xn8 )
    private String de30_AmtTxnProcFee;        // DE30: Amount, Txn Proc Fee (xn8 )
    private String de31_AmtSettleProcFee;        // DE31: Amount, Setl Proc Fee (xn8 )
    private String de32_AcqInstIdCode;        // DE32: Acq Inst Id Code (n11)
    private String de33_FwdInstIdCode;        // DE33: Fwd Inst Id code (n11)
    private String de34_PanExt;        // DE34: PAN, Extended (ns28 )
    private byte[] de35_Track2;        // DE35: Track 2 (z37)
    private byte[] de36_Track3;        // DE36: Track 3 (z104)
    private String de37_RetRefNo;        // DE37: RRN (an12)
    private String de38_AuthIdentResp;        // DE38: Auth identification response (an6)
    private String de39_RespCode;        // DE39: Response code (an2)
    private String de40_ServRestrCode;        // DE40: Service Restr Code (an3)
    private String de41_CardAcptTermId;        // DE41: TID (orig ans) (an8 )
    private String de42_CardAcptIdCode;        // DE42: Card Acceptor Identification Code (orig ans) (an15)
    private String de43_CardAcptNameLoc;        // DE43: Card acpt name/loc (orig ans) (an40)
    private byte[] de44_AddtRespData;        // DE44: Addtl resp data (ans25)
    private byte[] de45_Track1;        // DE45: Track 1 (ans76)
    private byte[] de46_AddtlDataIso;        // DE46: Addtl data - ISO (ans999)
    private byte[] de47_AddtlDataNat;        // DE47: Addtl data - National (ans999)
    private byte[] de48_AddtlDataPriv;        // DE48: Addtl data - Private (ans999)
    private String de49_CurCodeTxn;        // DE49: Cur code, txn (a or n3)
    private String de50_CurCodeSettle;        // DE50: Cur code, setl (a or n3)
    private String de51_CurCodeCardhBill;        // DE51: Cur code, cardh bill (a or n3)
    private byte[] de52_PinData;        // DE52: Pin data (b64)
    private String de53_SecControlInfo;        // DE53: Security related control info (n16)
    private String de54_AddtlAmts;        // DE54: Additional amounts (an120)
    private byte[] de55_ResIso;        // DE55: ICC Data (Reserved ISO) (ans999)
    private byte[] de56_ResIso;        // DE56: Reserved ISO (ans999)
    private String de57_AmtCash;        // DE57: Amount, Cash oz only (n12)
    private String de58_BalanceLedger;        // DE58: Ledger balance oz only (n12)
    private String de59_BalanceCleared;        // DE59: Acct balance, cleared funds oz only (n12)
    private String de60_PreswipeStatus;        // DE60: Preswipe status (Reserved Private, normally ans 999) (an1)
    private byte[] de61_ResPriv;        // DE61: Reserved Private (ans999)
    private byte[] de62_ResPriv;        // DE62: Reserved Private (ans999)
    private byte[] de63_ResPriv;        // DE63: Reserved Private (ans999)
    private byte[] de64_MAC;        // DE64: MAC (b64)
    private byte[] de65_Bitmap;        // DE65: Bit map, extended 2 (b64)
    private String de66_SettleCode;        // DE66: Settlement code (n1)
    private String de67_ExtPayCode;        // DE67: Ext payment code (n2)
    private String de68_RecvInstCtryCode;        // DE68: Recv inst count code (n3)
    private String de69_SettleInstCtryCode;        // DE69: Setl Inst Count code (n3)
    private String de70_NetMgtInfoCode;        // DE70: Net mgt info code (n3)
    private String de71_MessageNo;        // DE71: Message No (n4)
    private String de72_MessageNoLast;        // DE72: Message No Last (n4)
    private String de73_DateAction;        // DE73: Date, Action (n6)
    private String de74_CreditsNo;        // DE74: Credits, Num (n10)
    private String de75_CreditRevsNo;        // DE75: Credit revs, num (n10)
    private String de76_DebitsNo;        // DE76: Debits, num (n10)
    private String de77_DebitRevsNo;        // DE77: Debit revs, num (n10)
    private String de78_TransfersNo;        // DE78: Transfers, num (n10)
    private String de79_TransferRevsNo;        // DE79: Transfer revs, num (n10)
    private String de80_InquiriesNo;        // DE80: Inquiries, num (n10)
    private String de81_AuthsNo;        // DE81: Auths, num (n10)
    private String de82_CreditsProcFeeAmt;        // DE82: Credits, proc fee amt (n12)
    private String de83_CreditsTxnFeeAmt;        // DE83: Credits, transaction fee amt (n12)
    private String de84_DebitsProcFeeAmt;        // DE84: Debits, proc fee amt (n12)
    private String de85_DebitsTxnFeeAmt;        // DE85: Debits, transaction fee amt (n12)
    private String de86_CreditsAmt;        // DE86: Credits, amt (n16)
    private String de87_CreditRevsAmt;        // DE87: Credit revs, amt (n16)
    private String de88_DebitsAmt;        // DE88: Debits, amt (n16)
    private String de89_DebitRevsAmt;        // DE89: Debit revs, amount (n16)
    private String de90_OrigDataElem;        // DE90: Original data elements (n42)
    private String de91_FileUpdateCode;        // DE91: File update code (an1)
    private String de92_FileSecCode;        // DE92: File security code (an2)
    private String de93_RespInd;        // DE93: Response indicator (an5)
    private String de94_ServInd;        // DE94: Service indicator (an7)
    private String de95_ReplAmts;        // DE95: Replacement amounts (an42)
    private byte[] de96_MsgSecCode;        // DE96: Message Security code (b64)
    private String de97_AmtNetSetl;        // DE97: Amount, net settlement (xn16)
    private byte[] de98_Payee;        // DE98: Payee (ans25)
    private String de99_SettleInstIdCode;        // DE99: Setl inst id code (n11)
    private String de100_RecvInstIdCode;        // DE100: Recv inst id code (n11)
    private String de101_FileName;        // DE101: File name (normally ans) (an17)
    private byte[] de102_AcctId1;        // DE102: Account id 1 (ans28 )
    private byte[] de103_AcctId2;        // DE103: Account id 2 (ans28 )
    private byte[] de104_TxnDesc;        // DE104: Txn description (ans100)
    private byte[] de105_ResvIso;        // DE105: Reserved for iso use (ans999)
    private byte[] de106_ResvIso;        // DE106: Reserved for iso use (ans999)
    private byte[] de107_ResvIso;        // DE107: Reserved for iso use (ans999)
    private byte[] de108_ResvIso;        // DE108: Reserved for iso use (ans999)
    private byte[] de109_ResvIso;        // DE109: Reserved for iso use (ans999)
    private byte[] de110_ResvIso;        // DE110: Reserved for iso use (ans999)
    private byte[] de111_ResvIso;        // DE111: Reserved for iso use (ans999)
    private byte[] de112_ResvNat;        // DE112: Reserved for national use (ans999)
    private byte[] de113_ResvNat;        // DE113: Reserved for national use (ans999)
    private byte[] de114_ResvNat;        // DE114: Reserved for national use (ans999)
    private byte[] de115_ResvNat;        // DE115: Reserved for national use (ans999)
    private byte[] de116_ResvNat;        // DE116: Reserved for national use (ans999)
    private byte[] de117_CardStatUpdCode;        // DE117: Card status update code (oz only) (an2)
    private byte[] de118_TotalCashNo;        // DE118: Cash, total number oz only (n10)
    private byte[] de119_TotalCashAmt;        // DE119: Cash, total amount oz only (n16)
    private byte[] de120_ResvPriv;        // DE120: Reserved for private use (ans999)
    private byte[] de121_ResvPriv;        // DE121: Reserved for private use (ans999)
    private byte[] de122_ResvPriv;        // DE122: Reserved for private use (ans999)
    private byte[] de123_ResvPriv;        // DE123: Reserved for private use (ans999)
    private byte[] de124_ResvPriv;        // DE124: Reserved for private use (ans999)
    private byte[] de125_ResvPriv;        // DE125: Reserved for private use (ans999)
    private byte[] de126_ResvPriv;        // DE126: Reserved for private use (ans999)
    private byte[] de127_ResvPriv;        // DE127: Reserved for private use (ans999)
    private byte[] de128_MAC;        // DE128: MAC (b64)
    
	
    
    public String getMti() {
		return mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public byte[] getPrimaryBitmap() {
		return primaryBitmap;
	}

	public void setPrimaryBitmap(byte[] primaryBitmap) {
		this.primaryBitmap = primaryBitmap;
	}

	public byte[] getDe1_SecondaryBitmap() {
		return de1_SecondaryBitmap;
	}

	public void setDe1_SecondaryBitmap(byte[] de1_SecondaryBitmap) {
		this.de1_SecondaryBitmap = de1_SecondaryBitmap;
	}

	public String getDe2_PAN() {
		return de2_PAN;
	}

	public void setDe2_PAN(String de2_PAN) {
		this.de2_PAN = de2_PAN;
	}

	public String getDe3_ProcCode() {
		return de3_ProcCode;
	}

	public void setDe3_ProcCode(String de3_ProcCode) {
		this.de3_ProcCode = de3_ProcCode;
	}

	public String getDe4_AmtTxn() {
		return de4_AmtTxn;
	}

	public void setDe4_AmtTxn(String de4_AmtTxn) {
		this.de4_AmtTxn = de4_AmtTxn;
	}

	public String getDe5_AmtSettle() {
		return de5_AmtSettle;
	}

	public void setDe5_AmtSettle(String de5_AmtSettle) {
		this.de5_AmtSettle = de5_AmtSettle;
	}

	public String getDe6_AmtCardhBill() {
		return de6_AmtCardhBill;
	}

	public void setDe6_AmtCardhBill(String de6_AmtCardhBill) {
		this.de6_AmtCardhBill = de6_AmtCardhBill;
	}

	public String getDe7_TransDttm() {
		return de7_TransDttm;
	}

	public void setDe7_TransDttm(String de7_TransDttm) {
		this.de7_TransDttm = de7_TransDttm;
	}

	public String getDe8_AmtCardhBillFee() {
		return de8_AmtCardhBillFee;
	}

	public void setDe8_AmtCardhBillFee(String de8_AmtCardhBillFee) {
		this.de8_AmtCardhBillFee = de8_AmtCardhBillFee;
	}

	public String getDe9_ConvRateSettle() {
		return de9_ConvRateSettle;
	}

	public void setDe9_ConvRateSettle(String de9_ConvRateSettle) {
		this.de9_ConvRateSettle = de9_ConvRateSettle;
	}

	public String getDe10_ConvRateCardhBill() {
		return de10_ConvRateCardhBill;
	}

	public void setDe10_ConvRateCardhBill(String de10_ConvRateCardhBill) {
		this.de10_ConvRateCardhBill = de10_ConvRateCardhBill;
	}

	public String getDe11_STAN() {
		return de11_STAN;
	}

	public void setDe11_STAN(String de11_STAN) {
		this.de11_STAN = de11_STAN;
	}

	public String getDe12_TimeLocal() {
		return de12_TimeLocal;
	}

	public void setDe12_TimeLocal(String de12_TimeLocal) {
		this.de12_TimeLocal = de12_TimeLocal;
	}

	public String getDe13_DateLocal() {
		return de13_DateLocal;
	}

	public void setDe13_DateLocal(String de13_DateLocal) {
		this.de13_DateLocal = de13_DateLocal;
	}

	public String getDe14_DateExpiry() {
		return de14_DateExpiry;
	}

	public void setDe14_DateExpiry(String de14_DateExpiry) {
		this.de14_DateExpiry = de14_DateExpiry;
	}

	public String getDe15_DateSetl() {
		return de15_DateSetl;
	}

	public void setDe15_DateSetl(String de15_DateSetl) {
		this.de15_DateSetl = de15_DateSetl;
	}

	public String getDe16_DateConv() {
		return de16_DateConv;
	}

	public void setDe16_DateConv(String de16_DateConv) {
		this.de16_DateConv = de16_DateConv;
	}

	public String getDe17_DateCapt() {
		return de17_DateCapt;
	}

	public void setDe17_DateCapt(String de17_DateCapt) {
		this.de17_DateCapt = de17_DateCapt;
	}

	public String getDe18_MerchType() {
		return de18_MerchType;
	}

	public void setDe18_MerchType(String de18_MerchType) {
		this.de18_MerchType = de18_MerchType;
	}

	public String getDe19_AcqInstCtryCode() {
		return de19_AcqInstCtryCode;
	}

	public void setDe19_AcqInstCtryCode(String de19_AcqInstCtryCode) {
		this.de19_AcqInstCtryCode = de19_AcqInstCtryCode;
	}

	public String getDe20_PriAccNumExtCtryCode() {
		return de20_PriAccNumExtCtryCode;
	}

	public void setDe20_PriAccNumExtCtryCode(String de20_PriAccNumExtCtryCode) {
		this.de20_PriAccNumExtCtryCode = de20_PriAccNumExtCtryCode;
	}

	public String getDe21_FwdInstCtryCode() {
		return de21_FwdInstCtryCode;
	}

	public void setDe21_FwdInstCtryCode(String de21_FwdInstCtryCode) {
		this.de21_FwdInstCtryCode = de21_FwdInstCtryCode;
	}

	public String getDe22_PosEntryMode() {
		return de22_PosEntryMode;
	}

	public void setDe22_PosEntryMode(String de22_PosEntryMode) {
		this.de22_PosEntryMode = de22_PosEntryMode;
	}

	public String getDe23_CardSeqNo() {
		return de23_CardSeqNo;
	}

	public void setDe23_CardSeqNo(String de23_CardSeqNo) {
		this.de23_CardSeqNo = de23_CardSeqNo;
	}

	public String getDe24_NetIntlId() {
		return de24_NetIntlId;
	}

	public void setDe24_NetIntlId(String de24_NetIntlId) {
		this.de24_NetIntlId = de24_NetIntlId;
	}

	public String getDe25_PosCondCode() {
		return de25_PosCondCode;
	}

	public void setDe25_PosCondCode(String de25_PosCondCode) {
		this.de25_PosCondCode = de25_PosCondCode;
	}

	public String getDe26_PosPinCaptCode() {
		return de26_PosPinCaptCode;
	}

	public void setDe26_PosPinCaptCode(String de26_PosPinCaptCode) {
		this.de26_PosPinCaptCode = de26_PosPinCaptCode;
	}

	public String getDe27_AuthIdRespLen() {
		return de27_AuthIdRespLen;
	}

	public void setDe27_AuthIdRespLen(String de27_AuthIdRespLen) {
		this.de27_AuthIdRespLen = de27_AuthIdRespLen;
	}

	public String getDe28_AmtTxnFee() {
		return de28_AmtTxnFee;
	}

	public void setDe28_AmtTxnFee(String de28_AmtTxnFee) {
		this.de28_AmtTxnFee = de28_AmtTxnFee;
	}

	public String getDe29_AmtSettleFee() {
		return de29_AmtSettleFee;
	}

	public void setDe29_AmtSettleFee(String de29_AmtSettleFee) {
		this.de29_AmtSettleFee = de29_AmtSettleFee;
	}

	public String getDe30_AmtTxnProcFee() {
		return de30_AmtTxnProcFee;
	}

	public void setDe30_AmtTxnProcFee(String de30_AmtTxnProcFee) {
		this.de30_AmtTxnProcFee = de30_AmtTxnProcFee;
	}

	public String getDe31_AmtSettleProcFee() {
		return de31_AmtSettleProcFee;
	}

	public void setDe31_AmtSettleProcFee(String de31_AmtSettleProcFee) {
		this.de31_AmtSettleProcFee = de31_AmtSettleProcFee;
	}

	public String getDe32_AcqInstIdCode() {
		return de32_AcqInstIdCode;
	}

	public void setDe32_AcqInstIdCode(String de32_AcqInstIdCode) {
		this.de32_AcqInstIdCode = de32_AcqInstIdCode;
	}

	public String getDe33_FwdInstIdCode() {
		return de33_FwdInstIdCode;
	}

	public void setDe33_FwdInstIdCode(String de33_FwdInstIdCode) {
		this.de33_FwdInstIdCode = de33_FwdInstIdCode;
	}

	public String getDe34_PanExt() {
		return de34_PanExt;
	}

	public void setDe34_PanExt(String de34_PanExt) {
		this.de34_PanExt = de34_PanExt;
	}

	public byte[] getDe35_Track2() {
		return de35_Track2;
	}

	public void setDe35_Track2(byte[] de35_Track2) {
		this.de35_Track2 = de35_Track2;
	}

	public byte[] getDe36_Track3() {
		return de36_Track3;
	}

	public void setDe36_Track3(byte[] de36_Track3) {
		this.de36_Track3 = de36_Track3;
	}

	public String getDe37_RetRefNo() {
		return de37_RetRefNo;
	}

	public void setDe37_RetRefNo(String de37_RetRefNo) {
		this.de37_RetRefNo = de37_RetRefNo;
	}

	public String getDe38_AuthIdentResp() {
		return de38_AuthIdentResp;
	}

	public void setDe38_AuthIdentResp(String de38_AuthIdentResp) {
		this.de38_AuthIdentResp = de38_AuthIdentResp;
	}

	public String getDe39_RespCode() {
		return de39_RespCode;
	}

	public void setDe39_RespCode(String de39_RespCode) {
		this.de39_RespCode = de39_RespCode;
	}

	public String getDe40_ServRestrCode() {
		return de40_ServRestrCode;
	}

	public void setDe40_ServRestrCode(String de40_ServRestrCode) {
		this.de40_ServRestrCode = de40_ServRestrCode;
	}

	public String getDe41_CardAcptTermId() {
		return de41_CardAcptTermId;
	}

	public void setDe41_CardAcptTermId(String de41_CardAcptTermId) {
		this.de41_CardAcptTermId = de41_CardAcptTermId;
	}

	public String getDe42_CardAcptIdCode() {
		return de42_CardAcptIdCode;
	}

	public void setDe42_CardAcptIdCode(String de42_CardAcptIdCode) {
		this.de42_CardAcptIdCode = de42_CardAcptIdCode;
	}

	public String getDe43_CardAcptNameLoc() {
		return de43_CardAcptNameLoc;
	}

	public void setDe43_CardAcptNameLoc(String de43_CardAcptNameLoc) {
		this.de43_CardAcptNameLoc = de43_CardAcptNameLoc;
	}

	public byte[] getDe44_AddtRespData() {
		return de44_AddtRespData;
	}

	public void setDe44_AddtRespData(byte[] de44_AddtRespData) {
		this.de44_AddtRespData = de44_AddtRespData;
	}

	public byte[] getDe45_Track1() {
		return de45_Track1;
	}

	public void setDe45_Track1(byte[] de45_Track1) {
		this.de45_Track1 = de45_Track1;
	}

	public byte[] getDe46_AddtlDataIso() {
		return de46_AddtlDataIso;
	}

	public void setDe46_AddtlDataIso(byte[] de46_AddtlDataIso) {
		this.de46_AddtlDataIso = de46_AddtlDataIso;
	}

	public byte[] getDe47_AddtlDataNat() {
		return de47_AddtlDataNat;
	}

	public void setDe47_AddtlDataNat(byte[] de47_AddtlDataNat) {
		this.de47_AddtlDataNat = de47_AddtlDataNat;
	}

	public byte[] getDe48_AddtlDataPriv() {
		return de48_AddtlDataPriv;
	}

	public void setDe48_AddtlDataPriv(byte[] de48_AddtlDataPriv) {
		this.de48_AddtlDataPriv = de48_AddtlDataPriv;
	}

	public String getDe49_CurCodeTxn() {
		return de49_CurCodeTxn;
	}

	public void setDe49_CurCodeTxn(String de49_CurCodeTxn) {
		this.de49_CurCodeTxn = de49_CurCodeTxn;
	}

	public String getDe50_CurCodeSettle() {
		return de50_CurCodeSettle;
	}

	public void setDe50_CurCodeSettle(String de50_CurCodeSettle) {
		this.de50_CurCodeSettle = de50_CurCodeSettle;
	}

	public String getDe51_CurCodeCardhBill() {
		return de51_CurCodeCardhBill;
	}

	public void setDe51_CurCodeCardhBill(String de51_CurCodeCardhBill) {
		this.de51_CurCodeCardhBill = de51_CurCodeCardhBill;
	}

	public byte[] getDe52_PinData() {
		return de52_PinData;
	}

	public void setDe52_PinData(byte[] de52_PinData) {
		this.de52_PinData = de52_PinData;
	}

	public String getDe53_SecControlInfo() {
		return de53_SecControlInfo;
	}

	public void setDe53_SecControlInfo(String de53_SecControlInfo) {
		this.de53_SecControlInfo = de53_SecControlInfo;
	}

	public String getDe54_AddtlAmts() {
		return de54_AddtlAmts;
	}

	public void setDe54_AddtlAmts(String de54_AddtlAmts) {
		this.de54_AddtlAmts = de54_AddtlAmts;
	}

	public byte[] getDe55_ResIso() {
		return de55_ResIso;
	}

	public void setDe55_ResIso(byte[] de55_ResIso) {
		this.de55_ResIso = de55_ResIso;
	}

	public byte[] getDe56_ResIso() {
		return de56_ResIso;
	}

	public void setDe56_ResIso(byte[] de56_ResIso) {
		this.de56_ResIso = de56_ResIso;
	}

	public String getDe57_AmtCash() {
		return de57_AmtCash;
	}

	public void setDe57_AmtCash(String de57_AmtCash) {
		this.de57_AmtCash = de57_AmtCash;
	}

	public String getDe58_BalanceLedger() {
		return de58_BalanceLedger;
	}

	public void setDe58_BalanceLedger(String de58_BalanceLedger) {
		this.de58_BalanceLedger = de58_BalanceLedger;
	}

	public String getDe59_BalanceCleared() {
		return de59_BalanceCleared;
	}

	public void setDe59_BalanceCleared(String de59_BalanceCleared) {
		this.de59_BalanceCleared = de59_BalanceCleared;
	}

	public String getDe60_PreswipeStatus() {
		return de60_PreswipeStatus;
	}

	public void setDe60_PreswipeStatus(String de60_PreswipeStatus) {
		this.de60_PreswipeStatus = de60_PreswipeStatus;
	}

	public byte[] getDe61_ResPriv() {
		return de61_ResPriv;
	}

	public void setDe61_ResPriv(byte[] de61_ResPriv) {
		this.de61_ResPriv = de61_ResPriv;
	}

	public byte[] getDe62_ResPriv() {
		return de62_ResPriv;
	}

	public void setDe62_ResPriv(byte[] de62_ResPriv) {
		this.de62_ResPriv = de62_ResPriv;
	}

	public byte[] getDe63_ResPriv() {
		return de63_ResPriv;
	}

	public void setDe63_ResPriv(byte[] de63_ResPriv) {
		this.de63_ResPriv = de63_ResPriv;
	}

	public byte[] getDe64_MAC() {
		return de64_MAC;
	}

	public void setDe64_MAC(byte[] de64_MAC) {
		this.de64_MAC = de64_MAC;
	}

	public byte[] getDe65_Bitmap() {
		return de65_Bitmap;
	}

	public void setDe65_Bitmap(byte[] de65_Bitmap) {
		this.de65_Bitmap = de65_Bitmap;
	}

	public String getDe66_SettleCode() {
		return de66_SettleCode;
	}

	public void setDe66_SettleCode(String de66_SettleCode) {
		this.de66_SettleCode = de66_SettleCode;
	}

	public String getDe67_ExtPayCode() {
		return de67_ExtPayCode;
	}

	public void setDe67_ExtPayCode(String de67_ExtPayCode) {
		this.de67_ExtPayCode = de67_ExtPayCode;
	}

	public String getDe68_RecvInstCtryCode() {
		return de68_RecvInstCtryCode;
	}

	public void setDe68_RecvInstCtryCode(String de68_RecvInstCtryCode) {
		this.de68_RecvInstCtryCode = de68_RecvInstCtryCode;
	}

	public String getDe69_SettleInstCtryCode() {
		return de69_SettleInstCtryCode;
	}

	public void setDe69_SettleInstCtryCode(String de69_SettleInstCtryCode) {
		this.de69_SettleInstCtryCode = de69_SettleInstCtryCode;
	}

	public String getDe70_NetMgtInfoCode() {
		return de70_NetMgtInfoCode;
	}

	public void setDe70_NetMgtInfoCode(String de70_NetMgtInfoCode) {
		this.de70_NetMgtInfoCode = de70_NetMgtInfoCode;
	}

	public String getDe71_MessageNo() {
		return de71_MessageNo;
	}

	public void setDe71_MessageNo(String de71_MessageNo) {
		this.de71_MessageNo = de71_MessageNo;
	}

	public String getDe72_MessageNoLast() {
		return de72_MessageNoLast;
	}

	public void setDe72_MessageNoLast(String de72_MessageNoLast) {
		this.de72_MessageNoLast = de72_MessageNoLast;
	}

	public String getDe73_DateAction() {
		return de73_DateAction;
	}

	public void setDe73_DateAction(String de73_DateAction) {
		this.de73_DateAction = de73_DateAction;
	}

	public String getDe74_CreditsNo() {
		return de74_CreditsNo;
	}

	public void setDe74_CreditsNo(String de74_CreditsNo) {
		this.de74_CreditsNo = de74_CreditsNo;
	}

	public String getDe75_CreditRevsNo() {
		return de75_CreditRevsNo;
	}

	public void setDe75_CreditRevsNo(String de75_CreditRevsNo) {
		this.de75_CreditRevsNo = de75_CreditRevsNo;
	}

	public String getDe76_DebitsNo() {
		return de76_DebitsNo;
	}

	public void setDe76_DebitsNo(String de76_DebitsNo) {
		this.de76_DebitsNo = de76_DebitsNo;
	}

	public String getDe77_DebitRevsNo() {
		return de77_DebitRevsNo;
	}

	public void setDe77_DebitRevsNo(String de77_DebitRevsNo) {
		this.de77_DebitRevsNo = de77_DebitRevsNo;
	}

	public String getDe78_TransfersNo() {
		return de78_TransfersNo;
	}

	public void setDe78_TransfersNo(String de78_TransfersNo) {
		this.de78_TransfersNo = de78_TransfersNo;
	}

	public String getDe79_TransferRevsNo() {
		return de79_TransferRevsNo;
	}

	public void setDe79_TransferRevsNo(String de79_TransferRevsNo) {
		this.de79_TransferRevsNo = de79_TransferRevsNo;
	}

	public String getDe80_InquiriesNo() {
		return de80_InquiriesNo;
	}

	public void setDe80_InquiriesNo(String de80_InquiriesNo) {
		this.de80_InquiriesNo = de80_InquiriesNo;
	}

	public String getDe81_AuthsNo() {
		return de81_AuthsNo;
	}

	public void setDe81_AuthsNo(String de81_AuthsNo) {
		this.de81_AuthsNo = de81_AuthsNo;
	}

	public String getDe82_CreditsProcFeeAmt() {
		return de82_CreditsProcFeeAmt;
	}

	public void setDe82_CreditsProcFeeAmt(String de82_CreditsProcFeeAmt) {
		this.de82_CreditsProcFeeAmt = de82_CreditsProcFeeAmt;
	}

	public String getDe83_CreditsTxnFeeAmt() {
		return de83_CreditsTxnFeeAmt;
	}

	public void setDe83_CreditsTxnFeeAmt(String de83_CreditsTxnFeeAmt) {
		this.de83_CreditsTxnFeeAmt = de83_CreditsTxnFeeAmt;
	}

	public String getDe84_DebitsProcFeeAmt() {
		return de84_DebitsProcFeeAmt;
	}

	public void setDe84_DebitsProcFeeAmt(String de84_DebitsProcFeeAmt) {
		this.de84_DebitsProcFeeAmt = de84_DebitsProcFeeAmt;
	}

	public String getDe85_DebitsTxnFeeAmt() {
		return de85_DebitsTxnFeeAmt;
	}

	public void setDe85_DebitsTxnFeeAmt(String de85_DebitsTxnFeeAmt) {
		this.de85_DebitsTxnFeeAmt = de85_DebitsTxnFeeAmt;
	}

	public String getDe86_CreditsAmt() {
		return de86_CreditsAmt;
	}

	public void setDe86_CreditsAmt(String de86_CreditsAmt) {
		this.de86_CreditsAmt = de86_CreditsAmt;
	}

	public String getDe87_CreditRevsAmt() {
		return de87_CreditRevsAmt;
	}

	public void setDe87_CreditRevsAmt(String de87_CreditRevsAmt) {
		this.de87_CreditRevsAmt = de87_CreditRevsAmt;
	}

	public String getDe88_DebitsAmt() {
		return de88_DebitsAmt;
	}

	public void setDe88_DebitsAmt(String de88_DebitsAmt) {
		this.de88_DebitsAmt = de88_DebitsAmt;
	}

	public String getDe89_DebitRevsAmt() {
		return de89_DebitRevsAmt;
	}

	public void setDe89_DebitRevsAmt(String de89_DebitRevsAmt) {
		this.de89_DebitRevsAmt = de89_DebitRevsAmt;
	}

	public String getDe90_OrigDataElem() {
		return de90_OrigDataElem;
	}

	public void setDe90_OrigDataElem(String de90_OrigDataElem) {
		this.de90_OrigDataElem = de90_OrigDataElem;
	}

	public String getDe91_FileUpdateCode() {
		return de91_FileUpdateCode;
	}

	public void setDe91_FileUpdateCode(String de91_FileUpdateCode) {
		this.de91_FileUpdateCode = de91_FileUpdateCode;
	}

	public String getDe92_FileSecCode() {
		return de92_FileSecCode;
	}

	public void setDe92_FileSecCode(String de92_FileSecCode) {
		this.de92_FileSecCode = de92_FileSecCode;
	}

	public String getDe93_RespInd() {
		return de93_RespInd;
	}

	public void setDe93_RespInd(String de93_RespInd) {
		this.de93_RespInd = de93_RespInd;
	}

	public String getDe94_ServInd() {
		return de94_ServInd;
	}

	public void setDe94_ServInd(String de94_ServInd) {
		this.de94_ServInd = de94_ServInd;
	}

	public String getDe95_ReplAmts() {
		return de95_ReplAmts;
	}

	public void setDe95_ReplAmts(String de95_ReplAmts) {
		this.de95_ReplAmts = de95_ReplAmts;
	}

	public byte[] getDe96_MsgSecCode() {
		return de96_MsgSecCode;
	}

	public void setDe96_MsgSecCode(byte[] de96_MsgSecCode) {
		this.de96_MsgSecCode = de96_MsgSecCode;
	}

	public String getDe97_AmtNetSetl() {
		return de97_AmtNetSetl;
	}

	public void setDe97_AmtNetSetl(String de97_AmtNetSetl) {
		this.de97_AmtNetSetl = de97_AmtNetSetl;
	}

	public byte[] getDe98_Payee() {
		return de98_Payee;
	}

	public void setDe98_Payee(byte[] de98_Payee) {
		this.de98_Payee = de98_Payee;
	}

	public String getDe99_SettleInstIdCode() {
		return de99_SettleInstIdCode;
	}

	public void setDe99_SettleInstIdCode(String de99_SettleInstIdCode) {
		this.de99_SettleInstIdCode = de99_SettleInstIdCode;
	}

	public String getDe100_RecvInstIdCode() {
		return de100_RecvInstIdCode;
	}

	public void setDe100_RecvInstIdCode(String de100_RecvInstIdCode) {
		this.de100_RecvInstIdCode = de100_RecvInstIdCode;
	}

	public String getDe101_FileName() {
		return de101_FileName;
	}

	public void setDe101_FileName(String de101_FileName) {
		this.de101_FileName = de101_FileName;
	}

	public byte[] getDe102_AcctId1() {
		return de102_AcctId1;
	}

	public void setDe102_AcctId1(byte[] de102_AcctId1) {
		this.de102_AcctId1 = de102_AcctId1;
	}

	public byte[] getDe103_AcctId2() {
		return de103_AcctId2;
	}

	public void setDe103_AcctId2(byte[] de103_AcctId2) {
		this.de103_AcctId2 = de103_AcctId2;
	}

	public byte[] getDe104_TxnDesc() {
		return de104_TxnDesc;
	}

	public void setDe104_TxnDesc(byte[] de104_TxnDesc) {
		this.de104_TxnDesc = de104_TxnDesc;
	}

	public byte[] getDe105_ResvIso() {
		return de105_ResvIso;
	}

	public void setDe105_ResvIso(byte[] de105_ResvIso) {
		this.de105_ResvIso = de105_ResvIso;
	}

	public byte[] getDe106_ResvIso() {
		return de106_ResvIso;
	}

	public void setDe106_ResvIso(byte[] de106_ResvIso) {
		this.de106_ResvIso = de106_ResvIso;
	}

	public byte[] getDe107_ResvIso() {
		return de107_ResvIso;
	}

	public void setDe107_ResvIso(byte[] de107_ResvIso) {
		this.de107_ResvIso = de107_ResvIso;
	}

	public byte[] getDe108_ResvIso() {
		return de108_ResvIso;
	}

	public void setDe108_ResvIso(byte[] de108_ResvIso) {
		this.de108_ResvIso = de108_ResvIso;
	}

	public byte[] getDe109_ResvIso() {
		return de109_ResvIso;
	}

	public void setDe109_ResvIso(byte[] de109_ResvIso) {
		this.de109_ResvIso = de109_ResvIso;
	}

	public byte[] getDe110_ResvIso() {
		return de110_ResvIso;
	}

	public void setDe110_ResvIso(byte[] de110_ResvIso) {
		this.de110_ResvIso = de110_ResvIso;
	}

	public byte[] getDe111_ResvIso() {
		return de111_ResvIso;
	}

	public void setDe111_ResvIso(byte[] de111_ResvIso) {
		this.de111_ResvIso = de111_ResvIso;
	}

	public byte[] getDe112_ResvNat() {
		return de112_ResvNat;
	}

	public void setDe112_ResvNat(byte[] de112_ResvNat) {
		this.de112_ResvNat = de112_ResvNat;
	}

	public byte[] getDe113_ResvNat() {
		return de113_ResvNat;
	}

	public void setDe113_ResvNat(byte[] de113_ResvNat) {
		this.de113_ResvNat = de113_ResvNat;
	}

	public byte[] getDe114_ResvNat() {
		return de114_ResvNat;
	}

	public void setDe114_ResvNat(byte[] de114_ResvNat) {
		this.de114_ResvNat = de114_ResvNat;
	}

	public byte[] getDe115_ResvNat() {
		return de115_ResvNat;
	}

	public void setDe115_ResvNat(byte[] de115_ResvNat) {
		this.de115_ResvNat = de115_ResvNat;
	}

	public byte[] getDe116_ResvNat() {
		return de116_ResvNat;
	}

	public void setDe116_ResvNat(byte[] de116_ResvNat) {
		this.de116_ResvNat = de116_ResvNat;
	}

	public byte[] getDe117_CardStatUpdCode() {
		return de117_CardStatUpdCode;
	}

	public void setDe117_CardStatUpdCode(byte[] de117_CardStatUpdCode) {
		this.de117_CardStatUpdCode = de117_CardStatUpdCode;
	}

	public byte[] getDe118_TotalCashNo() {
		return de118_TotalCashNo;
	}

	public void setDe118_TotalCashNo(byte[] de118_TotalCashNo) {
		this.de118_TotalCashNo = de118_TotalCashNo;
	}

	public byte[] getDe119_TotalCashAmt() {
		return de119_TotalCashAmt;
	}

	public void setDe119_TotalCashAmt(byte[] de119_TotalCashAmt) {
		this.de119_TotalCashAmt = de119_TotalCashAmt;
	}

	public byte[] getDe120_ResvPriv() {
		return de120_ResvPriv;
	}

	public void setDe120_ResvPriv(byte[] de120_ResvPriv) {
		this.de120_ResvPriv = de120_ResvPriv;
	}

	public byte[] getDe121_ResvPriv() {
		return de121_ResvPriv;
	}

	public void setDe121_ResvPriv(byte[] de121_ResvPriv) {
		this.de121_ResvPriv = de121_ResvPriv;
	}

	public byte[] getDe122_ResvPriv() {
		return de122_ResvPriv;
	}

	public void setDe122_ResvPriv(byte[] de122_ResvPriv) {
		this.de122_ResvPriv = de122_ResvPriv;
	}

	public byte[] getDe123_ResvPriv() {
		return de123_ResvPriv;
	}

	public void setDe123_ResvPriv(byte[] de123_ResvPriv) {
		this.de123_ResvPriv = de123_ResvPriv;
	}

	public byte[] getDe124_ResvPriv() {
		return de124_ResvPriv;
	}

	public void setDe124_ResvPriv(byte[] de124_ResvPriv) {
		this.de124_ResvPriv = de124_ResvPriv;
	}

	public byte[] getDe125_ResvPriv() {
		return de125_ResvPriv;
	}

	public void setDe125_ResvPriv(byte[] de125_ResvPriv) {
		this.de125_ResvPriv = de125_ResvPriv;
	}

	public byte[] getDe126_ResvPriv() {
		return de126_ResvPriv;
	}

	public void setDe126_ResvPriv(byte[] de126_ResvPriv) {
		this.de126_ResvPriv = de126_ResvPriv;
	}

	public byte[] getDe127_ResvPriv() {
		return de127_ResvPriv;
	}

	public void setDe127_ResvPriv(byte[] de127_ResvPriv) {
		this.de127_ResvPriv = de127_ResvPriv;
	}

	public byte[] getDe128_MAC() {
		return de128_MAC;
	}

	public void setDe128_MAC(byte[] de128_MAC) {
		this.de128_MAC = de128_MAC;
	}

	public Iso8583Binary() {
		
    	super();
    	this.mti = "0210";
    	this.de2_PAN = StringUtils.padLeft("0", 19, "0");
    	this.de3_ProcCode = StringUtils.padLeft("0", 6, "0");
    	this.de4_AmtTxn = StringUtils.padLeft("0", 12, "0");
    	this.de5_AmtSettle = StringUtils.padLeft("0", 12, "0");
    	this.de6_AmtCardhBill = StringUtils.padLeft("0", 12, "0");
    	this.de7_TransDttm =  new SimpleDateFormat("MMddHHmmss").format(new Date()); //"1129235959"; //MMddHHmmss
    	this.de8_AmtCardhBillFee = StringUtils.padLeft("0", 8, "0");
    	this.de9_ConvRateSettle = StringUtils.padLeft("0", 8, "0");
    	this.de10_ConvRateCardhBill = StringUtils.padLeft("0", 8, "0");
    	this.de11_STAN = StringUtils.padLeft("0", 6, "0");
    	this.de12_TimeLocal = new SimpleDateFormat("HHmmss").format(new Date()); //HHmmss
    	this.de13_DateLocal = new SimpleDateFormat("MMdd").format(new Date());
    	this.de14_DateExpiry = "8011"; //yyMM
    	this.de15_DateSetl = new SimpleDateFormat("MMdd").format(new Date()); //MMdd
    	this.de16_DateConv = new SimpleDateFormat("MMdd").format(new Date()); //MMdd
    	this.de17_DateCapt = new SimpleDateFormat("MMdd").format(new Date()); //MMdd
    	this.de18_MerchType = StringUtils.padLeft("0", 4, "0");
    	this.de19_AcqInstCtryCode = StringUtils.padLeft("0", 3, "	0");
    	this.de20_PriAccNumExtCtryCode = StringUtils.padLeft("0", 3, "0");
    	this.de21_FwdInstCtryCode = StringUtils.padLeft("0", 3, "0");
    	this.de22_PosEntryMode = StringUtils.padLeft("0", 3, "0");
    	this.de23_CardSeqNo = StringUtils.padLeft("0", 3, "0");
    	this.de24_NetIntlId = StringUtils.padLeft("0", 3, "0");
    	this.de25_PosCondCode = StringUtils.padLeft("0", 2, "0");
    	this.de26_PosPinCaptCode = StringUtils.padLeft("0", 2, "0");
    	this.de27_AuthIdRespLen = "0";
    	this.de28_AmtTxnFee = StringUtils.padLeft("0", 8, "0");
    	this.de29_AmtSettleFee = StringUtils.padLeft("0", 8, "0");
    	this.de30_AmtTxnProcFee = StringUtils.padLeft("0", 8, "0");
    	this.de31_AmtSettleProcFee = StringUtils.padLeft("0", 8, "0");
    	this.de32_AcqInstIdCode = StringUtils.padLeft("0", 11, "0");
    	this.de33_FwdInstIdCode = StringUtils.padLeft("0", 11, "0");
    	this.de34_PanExt = StringUtils.padLeft("0", 28, "0");
    	this.de35_Track2  = new byte[37];
    	this.de36_Track3 = new byte[104];   	
    	this.de37_RetRefNo = StringUtils.padLeft(" ", 11, " ");
    	this.de38_AuthIdentResp = StringUtils.padLeft(" ", 6, " ");
    	this.de39_RespCode = "96";
    	this.de40_ServRestrCode = StringUtils.padLeft(" ", 3, " ");
    	this.de41_CardAcptTermId = StringUtils.padLeft(" ", 8, " ");
    	this.de42_CardAcptIdCode = StringUtils.padLeft(" ", 15, " ");
    	this.de43_CardAcptNameLoc = StringUtils.padLeft(" ", 40, " ");
    	this.de44_AddtRespData = new byte[25];
    	this.de45_Track1 = new byte[76];
    	this.de46_AddtlDataIso = new byte[999];
    	this.de47_AddtlDataNat = new byte[999];
    	this.de48_AddtlDataPriv = new byte[999];
    	this.de49_CurCodeTxn = "840";
    	this.de50_CurCodeSettle = StringUtils.padLeft(" ", 3, " ");
    	this.de51_CurCodeCardhBill = StringUtils.padLeft(" ", 3, " ");
    	this.de52_PinData = new byte[8];
    	this.de53_SecControlInfo = StringUtils.padLeft("1", 16, "1");//info binaria
    	this.de54_AddtlAmts = StringUtils.padLeft(" ", 120, " ");
    	this.de55_ResIso = new byte[999];
    	this.de56_ResIso = new byte[999];
    	this.de57_AmtCash = StringUtils.padLeft(" ", 999, " ");
    	this.de58_BalanceLedger = StringUtils.padLeft(" ", 999, " ");
    	this.de59_BalanceCleared = StringUtils.padLeft(" ", 999, " ");
    	this.de60_PreswipeStatus = " ";
    	this.de61_ResPriv = new byte[999];
    	this.de62_ResPriv = new byte[999];
    	this.de63_ResPriv = new byte[999];
    	this.de64_MAC = new byte[8];
    	this.de65_Bitmap = new byte[8];
    	this.de66_SettleCode = "0";
    	this.de67_ExtPayCode = "00";
    	this.de68_RecvInstCtryCode = StringUtils.padLeft("0", 3, "0");
    	this.de69_SettleInstCtryCode = StringUtils.padLeft("0", 3, "0");
    	this.de70_NetMgtInfoCode = StringUtils.padLeft("0", 3, "0");
    	this.de71_MessageNo = StringUtils.padLeft("0", 4, "0");
    	this.de72_MessageNoLast = StringUtils.padLeft("0", 4, "0");
    	this.de73_DateAction = StringUtils.padLeft("0", 6, "0");
    	this.de74_CreditsNo = StringUtils.padLeft("0", 10, "0");
    	this.de75_CreditRevsNo = StringUtils.padLeft("0", 10, "0");
    	this.de76_DebitsNo = StringUtils.padLeft("0", 10, "0");
    	this.de77_DebitRevsNo = StringUtils.padLeft("0", 10, "0");
    	this.de78_TransfersNo = StringUtils.padLeft("0", 10, "0");
    	this.de79_TransferRevsNo = StringUtils.padLeft("0", 10, "0");
    	this.de80_InquiriesNo = StringUtils.padLeft("0", 10, "0");
    	this.de81_AuthsNo = StringUtils.padLeft("0", 10, "0");
    	this.de82_CreditsProcFeeAmt = StringUtils.padLeft("0", 12, "0");
    	this.de83_CreditsTxnFeeAmt = StringUtils.padLeft("0", 12, "0");
    	this.de84_DebitsProcFeeAmt = StringUtils.padLeft("0", 12, "0");
    	this.de85_DebitsTxnFeeAmt = StringUtils.padLeft("0", 12, "0");
    	this.de86_CreditsAmt = StringUtils.padLeft("0", 16, "0");
    	this.de87_CreditRevsAmt = StringUtils.padLeft("0", 16, "0");
    	this.de88_DebitsAmt = StringUtils.padLeft("0", 16, "0");
    	this.de89_DebitRevsAmt = StringUtils.padLeft("0", 16, "0");
    	this.de89_DebitRevsAmt = StringUtils.padLeft("0", 16, "0");
    	this.de90_OrigDataElem = StringUtils.padLeft("0", 42, "0");
    	this.de91_FileUpdateCode = StringUtils.padLeft(" ", 1, " ");
    	this.de92_FileSecCode = StringUtils.padLeft("0", 2, "0");
    	this.de93_RespInd =  StringUtils.padLeft(" ", 5, " ");
    	this.de94_ServInd = StringUtils.padLeft(" ", 7, " ");
    	this.de95_ReplAmts = StringUtils.padLeft(" ", 42, " ");
    	this.de96_MsgSecCode = new byte[8];
    	this.de97_AmtNetSetl = StringUtils.padLeft(" ", 16, " ");
    	this.de98_Payee = new byte[25];
    	this.de99_SettleInstIdCode = StringUtils.padLeft("0", 11, "0");
    	this.de100_RecvInstIdCode = StringUtils.padLeft("0", 11, "0");
    	this.de101_FileName = StringUtils.padLeft("0", 17, "0");
    	this.de102_AcctId1 = new byte[28];
    	this.de103_AcctId2 = new byte[28];
    	this.de104_TxnDesc = new byte[100];
    	this.de105_ResvIso = new byte[999];
    	this.de106_ResvIso = new byte[999];
    	this.de107_ResvIso = new byte[999];
    	this.de109_ResvIso = new byte[999];
    	this.de110_ResvIso = new byte[999];
    	this.de111_ResvIso = new byte[999];
    	this.de112_ResvNat = new byte[999];
    	this.de113_ResvNat = new byte[999];
    	this.de114_ResvNat = new byte[999];
    	this.de115_ResvNat = new byte[999];
    	this.de116_ResvNat = new byte[999];
    	this.de117_CardStatUpdCode = new byte[999];
    	this.de118_TotalCashNo = new byte[999];
    	this.de119_TotalCashAmt = new byte[999];
    	this.de120_ResvPriv = new byte[999];
    	this.de121_ResvPriv = new byte[999];
    	this.de122_ResvPriv = new byte[999];
    	this.de123_ResvPriv = new byte[999];
    	this.de124_ResvPriv = new byte[999];
    	this.de125_ResvPriv = new byte[999];
    	this.de126_ResvPriv = new byte[999];
    	this.de127_ResvPriv = new byte[999];
    	this.de128_MAC = new byte[8];
	}
    
	public static Iso8583Binary GenericError(){
		
		Iso8583Binary iso = new Iso8583Binary();
		iso.setDe39_RespCode("96");
		iso.setDe114_ResvNat("SYSTEM MALFUNCTION".getBytes());
		iso.setPrimaryBitmap(GeneralUtils.getHexBitMapToString("8218000002000000"));
		iso.setDe1_SecondaryBitmap(GeneralUtils.getHexBitMapToString("0000000000004000"));
		return iso;
	}
	
    public byte[] getIso8583BinaryResponse(Iso8583Binary isoBinRes){
    	Logger log = null;
		try {
			
			byte[] buffer = new byte[40960];
			int offset = 0;
			ResponseBynary bin = new ResponseBynary();
			System.arraycopy(isoBinRes.getMti().getBytes(), 0, buffer, 0, 
					    isoBinRes.getMti().getBytes().length); offset += 4;
			byte[] prBitmap = isoBinRes.getPrimaryBitmap();
			System.arraycopy(isoBinRes.getPrimaryBitmap(), 0, buffer, offset, 8); offset += 8;
			int hiNibble = (prBitmap[0] >> 4) & 0x0f; 
			if(hiNibble >= 8)
				System.arraycopy(isoBinRes.getDe1_SecondaryBitmap(), 0, buffer, offset, 8); offset += 8;
			bin.setBuffer(buffer);
			bin.setOffset(offset);
			Ref<ResponseBynary> ref = new Ref<ResponseBynary>(bin);
			
        	if ((isoBinRes.getPrimaryBitmap()[0] & 64) > 0)  GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe2_PAN(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe3_ProcCode(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe4_AmtTxn(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe5_AmtSettle(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe6_AmtCardhBill(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe7_TransDttm(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[0] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe8_AmtCardhBillFee(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[1] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe9_ConvRateSettle(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[1] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe10_ConvRateCardhBill(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[1] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe11_STAN(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[1] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe12_TimeLocal(), ref);
        	if ((isoBinRes.getPrimaryBitmap()[1] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe13_DateLocal(), ref);
            if ((isoBinRes.getPrimaryBitmap()[1] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe14_DateExpiry(), ref);
            if ((isoBinRes.getPrimaryBitmap()[1] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe15_DateSetl(), ref);
            if ((isoBinRes.getPrimaryBitmap()[1] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe16_DateConv(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe17_DateCapt(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe18_MerchType(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe19_AcqInstCtryCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe20_PriAccNumExtCtryCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe21_FwdInstCtryCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe22_PosEntryMode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe23_CardSeqNo(), ref);
            if ((isoBinRes.getPrimaryBitmap()[2] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe24_NetIntlId(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe25_PosCondCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe26_PosPinCaptCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe27_AuthIdRespLen(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe28_AmtTxnFee(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe29_AmtSettleFee(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe30_AmtTxnProcFee(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe31_AmtSettleProcFee(), ref);
            if ((isoBinRes.getPrimaryBitmap()[3] & 1) > 0)   GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe32_AcqInstIdCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 128) > 0) GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe33_FwdInstIdCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 64) > 0)  GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe34_PanExt(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 32) > 0)  GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe35_Track2(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 16) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe36_Track3(), ref);
             if ((isoBinRes.getPrimaryBitmap()[4] & 8) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe37_RetRefNo(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe38_AuthIdentResp(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe39_RespCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[4] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe40_ServRestrCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe41_CardAcptTermId(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe42_CardAcptIdCode(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe43_CardAcptNameLoc(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 16) > 0)  GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe44_AddtRespData(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 8) > 0)   GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe45_Track1(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 4) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe46_AddtlDataIso(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe47_AddtlDataNat(), ref);
            if ((isoBinRes.getPrimaryBitmap()[5] & 1) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe48_AddtlDataPriv(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe49_CurCodeTxn(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe50_CurCodeSettle(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe51_CurCodeCardhBill(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 16) > 0)  GeneralUtils.ReadFixByteReverse(isoBinRes.getDe52_PinData(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe53_SecControlInfo(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 4) > 0)   GeneralUtils.ReadVar3RawReverse(isoBinRes.getDe54_AddtlAmts(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe55_ResIso(), ref);
            if ((isoBinRes.getPrimaryBitmap()[6] & 1) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe56_ResIso(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 128) > 0) GeneralUtils.ReadVar3RawReverse(isoBinRes.getDe57_AmtCash(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe58_BalanceLedger(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe59_BalanceCleared(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 16) > 0)  GeneralUtils.ReadVar3RawReverse(isoBinRes.getDe60_PreswipeStatus(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 8) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe61_ResPriv(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 4) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe62_ResPriv(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe63_ResPriv(), ref);
            if ((isoBinRes.getPrimaryBitmap()[7] & 1) > 0)   GeneralUtils.ReadFixByteReverse(isoBinRes.getDe64_MAC(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 128) > 0) GeneralUtils.ReadFixByteReverse(isoBinRes.getDe65_Bitmap(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe66_SettleCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe67_ExtPayCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe68_RecvInstCtryCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe69_SettleInstCtryCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe70_NetMgtInfoCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe71_MessageNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[0] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe72_MessageNoLast(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe73_DateAction(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe74_CreditsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe75_CreditRevsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe76_DebitsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe77_DebitRevsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe78_TransfersNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe79_TransferRevsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[1] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe80_InquiriesNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe81_AuthsNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe82_CreditsProcFeeAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe83_CreditsTxnFeeAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe84_DebitsProcFeeAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe85_DebitsTxnFeeAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe86_CreditsAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe87_CreditRevsAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[2] & 1) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe88_DebitsAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe89_DebitRevsAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 64) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe90_OrigDataElem(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 32) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe91_FileUpdateCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 16) > 0)  GeneralUtils.ReadFixStringReverse(isoBinRes.getDe92_FileSecCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 8) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe93_RespInd(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 4) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe94_ServInd(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 2) > 0)   GeneralUtils.ReadFixStringReverse(isoBinRes.getDe95_ReplAmts(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[3] & 1) > 0)   GeneralUtils.ReadFixByteReverse(isoBinRes.getDe96_MsgSecCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 128) > 0) GeneralUtils.ReadFixStringReverse(isoBinRes.getDe97_AmtNetSetl(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 64) > 0)  GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe98_Payee(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 32) > 0)  GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe99_SettleInstIdCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 16) > 0)  GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe100_RecvInstIdCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 8) > 0)   GeneralUtils.ReadVar2RawReverse(isoBinRes.getDe101_FileName(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 4) > 0)   GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe102_AcctId1(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 2) > 0)   GeneralUtils.ReadVar2RawByteReverse(isoBinRes.getDe103_AcctId2(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[4] & 1) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe104_TxnDesc(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 128) > 0) GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe105_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 64) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe106_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 32) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe107_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 16) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe108_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 8) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe109_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 4) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe110_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe111_ResvIso(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[5] & 1) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe112_ResvNat(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 128) > 0) GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe113_ResvNat(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 64) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe114_ResvNat(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 32) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe115_ResvNat(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 16) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe116_ResvNat(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 8) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe117_CardStatUpdCode(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 4) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe118_TotalCashNo(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe119_TotalCashAmt(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[6] & 1) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe120_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 128) > 0) GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe121_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 64) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe122_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 32) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe123_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 16) > 0)  GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe124_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 8) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe125_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 4) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe126_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 2) > 0)   GeneralUtils.ReadVar3RawByteReverse(isoBinRes.getDe127_ResvPriv(), ref);
            if ((isoBinRes.getDe1_SecondaryBitmap()[7] & 1) > 0)   GeneralUtils.ReadFixByteReverse(isoBinRes.getDe128_MAC(), ref);
            int totalOffset = ref.get().getOffset();
            System.out.println(totalOffset);
            byte[] bufferTotal = new byte[totalOffset];
            System.arraycopy(ref.get().getBuffer(), 0, bufferTotal, 0, totalOffset);
            System.out.println(new String(bufferTotal));
        	return bufferTotal;
        	
		} catch (Exception e) {
			
			log = new Logger();
			log.WriteLogMonitor("ERROR CONSTRUCTOR IsoBynary(byte[] buffer)", TypeMonitor.error, e);
			return null;
		}
	}
	
    public Iso8583Binary(byte[] buffer) {
		
    	this();
    	Logger log = null;
    	try {
    		
    		this.mti = new String(buffer, 0, 4);
    		System.out.println("MTI="+ mti);
    		
    		
        	int offset = 4;
        	Ref<Integer> ref = new Ref<Integer>(offset);
        	
        	System.out.println("antes");
        	this.primaryBitmap = GeneralUtils.ReadFixByte(buffer, ref, 8); //Viene empaquetado
        	
//        	//////////////////////////////////////////////////////////////////////7
//        	System.out.println("despues");
//        	System.out.println("primaryBitmap[0] & 128 = "+(primaryBitmap[0] & 128));
//        	System.out.println("primaryBitmap[0] & 64 ="+(primaryBitmap[0] & 64));
//        	System.out.println("primaryBitmap[0] & 32 ="+(primaryBitmap[0] & 32));
//        	System.out.println("primaryBitmap[0] & 16 ="+(primaryBitmap[0] & 64));
//        	System.out.println("primaryBitmap[0] & 8  ="+(primaryBitmap[0] & 8));
//        	System.out.println("primaryBitmap[0] & 4 ="+(primaryBitmap[0] & 4));
//        	System.out.println("primaryBitmap[0] & 2 ="+(primaryBitmap[0] & 2));
//        	System.out.println("primaryBitmap[0] & 1"+(primaryBitmap[0] & 1));
//        	///////////////////////////////////////////////////////////////////////////////
//        	System.out.println("despues");
//        	System.out.println("primaryBitmap[1] & 128 = "+(primaryBitmap[1] & 128));
//        	System.out.println("primaryBitmap[1] & 64 ="+(primaryBitmap[1] & 64));
//        	
//        	System.out.println("primaryBitmap[1] & 32 ="+(primaryBitmap[1] & 32));
//        	System.out.println("GeneralUtils.ReadFixString(buffer, ref, 6); ="+GeneralUtils.ReadFixString(buffer, ref, 6));
//        	
//        	
//        	System.out.println("primaryBitmap[1] & 16 ="+(primaryBitmap[1] & 64));
//        	System.out.println("primaryBitmap[1] & 8  ="+(primaryBitmap[1] & 8));
//        	System.out.println("primaryBitmap[1] & 4 ="+(primaryBitmap[1] & 4));
//        	System.out.println("primaryBitmap[1] & 2 ="+(primaryBitmap[1] & 2));
//        	System.out.println("primaryBitmap[1] & 1"+(primaryBitmap[1] & 1));
//        	/////////////////////////////////////////////////////////////////////////////////
//        	System.out.println("primaryBitmap[4] & 2"+(primaryBitmap[4] & 2));
        	
        	System.out.println(buffer);
        	System.out.println("buffer = "+ Arrays.toString(buffer));
        	System.out.println("int offsetI = offset.get();"+ (ref.get()));
        	
        	
        	System.out.println("setea el campo 39 "+GeneralUtils.ReadFixString(buffer, ref, 2));
//        	
//        	
//        	
        	
        	if ((primaryBitmap[0] & 128) > 0) this.de1_SecondaryBitmap = GeneralUtils.ReadFixByte(buffer, ref, 8); //Viene empaquetado
        	if ((primaryBitmap[0] & 64) > 0)  this.de2_PAN = GeneralUtils.ReadVar2Raw(buffer, ref, 19);
        	if ((primaryBitmap[0] & 32) > 0)  this.de3_ProcCode = GeneralUtils.ReadFixString(buffer, ref, 6);
        	if ((primaryBitmap[0] & 16) > 0)  this.de4_AmtTxn = GeneralUtils.ReadFixString(buffer, ref, 12);
        	if ((primaryBitmap[0] & 8) > 0)   this.de5_AmtSettle = GeneralUtils.ReadFixString(buffer, ref, 12);
        	if ((primaryBitmap[0] & 4) > 0)   this.de6_AmtCardhBill = GeneralUtils.ReadFixString(buffer, ref, 12);
        	if ((primaryBitmap[0] & 2) > 0)   this.de7_TransDttm = GeneralUtils.ReadFixString(buffer, ref, 10);
        	if ((primaryBitmap[0] & 1) > 0)   this.de8_AmtCardhBillFee = GeneralUtils.ReadFixString(buffer, ref, 8);
        	
        	/////////////////////////////////////////////////////////////////////////////////
        	
        	if ((primaryBitmap[1] & 128) > 0) this.de9_ConvRateSettle = GeneralUtils.ReadFixString(buffer, ref, 8);
        	if ((primaryBitmap[1] & 64) > 0)  this.de10_ConvRateCardhBill = GeneralUtils.ReadFixString(buffer, ref, 8);
        	if ((primaryBitmap[1] & 32) > 0)  this.de11_STAN = GeneralUtils.ReadFixString(buffer, ref, 6);
        	if ((primaryBitmap[1] & 16) > 0)  this.de12_TimeLocal = GeneralUtils.ReadFixString(buffer, ref, 6);
        	if ((primaryBitmap[1] & 8) > 0)   this.de13_DateLocal = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[1] & 4) > 0)   this.de14_DateExpiry = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[1] & 2) > 0)   this.de15_DateSetl = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[1] & 1) > 0)   this.de16_DateConv = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[2] & 128) > 0) this.de17_DateCapt = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[2] & 64) > 0)  this.de18_MerchType = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((primaryBitmap[2] & 32) > 0)  this.de19_AcqInstCtryCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[2] & 16) > 0)  this.de20_PriAccNumExtCtryCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[2] & 8) > 0)   this.de21_FwdInstCtryCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[2] & 4) > 0)   this.de22_PosEntryMode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[2] & 2) > 0)   this.de23_CardSeqNo = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[2] & 1) > 0)   this.de24_NetIntlId = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[3] & 128) > 0) this.de25_PosCondCode = GeneralUtils.ReadFixString(buffer, ref, 2);
            if ((primaryBitmap[3] & 64) > 0)  this.de26_PosPinCaptCode = GeneralUtils.ReadFixString(buffer, ref, 2);
            if ((primaryBitmap[3] & 32) > 0)  this.de27_AuthIdRespLen = GeneralUtils.ReadFixString(buffer, ref, 1);
            if ((primaryBitmap[3] & 16) > 0)  this.de28_AmtTxnFee = GeneralUtils.ReadFixString(buffer, ref, 8);
            if ((primaryBitmap[3] & 8) > 0)   this.de29_AmtSettleFee = GeneralUtils.ReadFixString(buffer, ref, 8);
            if ((primaryBitmap[3] & 4) > 0)   this.de30_AmtTxnProcFee = GeneralUtils.ReadFixString(buffer, ref, 8);
            if ((primaryBitmap[3] & 2) > 0)   this.de31_AmtSettleProcFee = GeneralUtils.ReadFixString(buffer, ref, 8);
            if ((primaryBitmap[3] & 1) > 0)   this.de32_AcqInstIdCode = GeneralUtils.ReadVar2Raw(buffer, ref, 11);
            if ((primaryBitmap[4] & 128) > 0) this.de33_FwdInstIdCode = GeneralUtils.ReadVar2Raw(buffer, ref, 11);
            if ((primaryBitmap[4] & 64) > 0)  this.de34_PanExt = GeneralUtils.ReadVar2Raw(buffer, ref, 28);
            if ((primaryBitmap[4] & 32) > 0)  this.de35_Track2 = GeneralUtils.ReadVar2RawByte(buffer, ref, 28);
            if ((primaryBitmap[4] & 16) > 0)  this.de36_Track3 = GeneralUtils.ReadVar3RawByte(buffer, ref, 104);
            if ((primaryBitmap[4] & 8) > 0)   this.de37_RetRefNo = GeneralUtils.ReadFixString(buffer, ref, 12);
            if ((primaryBitmap[4] & 4) > 0)   this.de38_AuthIdentResp = GeneralUtils.ReadFixString(buffer, ref, 6);
            
            if ((primaryBitmap[4] & 2) > 0)   this.de39_RespCode = GeneralUtils.ReadFixString(buffer, ref, 2);
            
            if ((primaryBitmap[4] & 1) > 0)   this.de40_ServRestrCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[5] & 128) > 0) this.de41_CardAcptTermId = GeneralUtils.ReadFixString(buffer, ref, 8);
            if ((primaryBitmap[5] & 64) > 0)  this.de42_CardAcptIdCode = GeneralUtils.ReadFixString(buffer, ref, 15);
            if ((primaryBitmap[5] & 32) > 0)  this.de43_CardAcptNameLoc = GeneralUtils.ReadFixString(buffer, ref, 40);
            if ((primaryBitmap[5] & 16) > 0)  this.de44_AddtRespData = GeneralUtils.ReadVar2RawByte(buffer, ref, 25);
            if ((primaryBitmap[5] & 8) > 0)   this.de45_Track1 = GeneralUtils.ReadVar2RawByte(buffer, ref, 76);
            if ((primaryBitmap[5] & 4) > 0)   this.de46_AddtlDataIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[5] & 2) > 0)   this.de47_AddtlDataNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[5] & 1) > 0)   this.de48_AddtlDataPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[6] & 128) > 0) this.de49_CurCodeTxn = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[6] & 64) > 0)  this.de50_CurCodeSettle = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[6] & 32) > 0)  this.de51_CurCodeCardhBill = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((primaryBitmap[6] & 16) > 0)  this.de52_PinData =  GeneralUtils.ReadFixByte(buffer, ref, 8); //Viene empaquetado
            if ((primaryBitmap[6] & 8) > 0)   this.de53_SecControlInfo = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((primaryBitmap[6] & 4) > 0)   this.de54_AddtlAmts =  GeneralUtils.ReadVar3Raw(buffer, ref, 120);
            if ((primaryBitmap[6] & 2) > 0)   this.de55_ResIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[6] & 1) > 0)   this.de56_ResIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[7] & 128) > 0) this.de57_AmtCash = GeneralUtils.ReadVar3Raw(buffer, ref, 999);
            if ((primaryBitmap[7] & 64) > 0)  this.de58_BalanceLedger = GeneralUtils.ReadFixString(buffer, ref, 999);
            if ((primaryBitmap[7] & 32) > 0)  this.de59_BalanceCleared = GeneralUtils.ReadFixString(buffer, ref, 999);
            if ((primaryBitmap[7] & 16) > 0)  this.de60_PreswipeStatus = GeneralUtils.ReadVar3Raw(buffer, ref, 1);
            if ((primaryBitmap[7] & 8) > 0)   this.de61_ResPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[7] & 4) > 0)   this.de62_ResPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[7] & 2) > 0)   this.de63_ResPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((primaryBitmap[7] & 1) > 0)   this.de64_MAC = GeneralUtils.ReadFixByte(buffer, ref, 8);//Viene empaquetado
            if ((de1_SecondaryBitmap[0] & 128) > 0) this.de65_Bitmap = GeneralUtils.ReadFixByte(buffer, ref, 8);//Viene empaquetado
            if ((de1_SecondaryBitmap[0] & 64) > 0)  this.de66_SettleCode = GeneralUtils.ReadFixString(buffer, ref, 1);
            if ((de1_SecondaryBitmap[0] & 32) > 0)  this.de67_ExtPayCode = GeneralUtils.ReadFixString(buffer, ref, 2);
            if ((de1_SecondaryBitmap[0] & 16) > 0)  this.de68_RecvInstCtryCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((de1_SecondaryBitmap[0] & 8) > 0)   this.de69_SettleInstCtryCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((de1_SecondaryBitmap[0] & 4) > 0)   this.de70_NetMgtInfoCode = GeneralUtils.ReadFixString(buffer, ref, 3);
            if ((de1_SecondaryBitmap[0] & 2) > 0)   this.de71_MessageNo = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((de1_SecondaryBitmap[0] & 1) > 0)   this.de72_MessageNoLast = GeneralUtils.ReadFixString(buffer, ref, 4);
            if ((de1_SecondaryBitmap[1] & 128) > 0) this.de73_DateAction = GeneralUtils.ReadFixString(buffer, ref, 6);
            if ((de1_SecondaryBitmap[1] & 64) > 0)  this.de74_CreditsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 32) > 0)  this.de75_CreditRevsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 16) > 0)  this.de76_DebitsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 8) > 0)   this.de77_DebitRevsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 4) > 0)   this.de78_TransfersNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 2) > 0)   this.de79_TransferRevsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[1] & 1) > 0)   this.de80_InquiriesNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[2] & 128) > 0) this.de81_AuthsNo = GeneralUtils.ReadFixString(buffer, ref, 10);
            if ((de1_SecondaryBitmap[2] & 64) > 0)  this.de82_CreditsProcFeeAmt = GeneralUtils.ReadFixString(buffer, ref, 12);
            if ((de1_SecondaryBitmap[2] & 32) > 0)  this.de83_CreditsTxnFeeAmt = GeneralUtils.ReadFixString(buffer, ref, 12);
            if ((de1_SecondaryBitmap[2] & 16) > 0)  this.de84_DebitsProcFeeAmt = GeneralUtils.ReadFixString(buffer, ref, 12);
            if ((de1_SecondaryBitmap[2] & 8) > 0)   this.de85_DebitsTxnFeeAmt = GeneralUtils.ReadFixString(buffer, ref, 12);
            if ((de1_SecondaryBitmap[2] & 4) > 0)   this.de86_CreditsAmt = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((de1_SecondaryBitmap[2] & 2) > 0)   this.de87_CreditRevsAmt = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((de1_SecondaryBitmap[2] & 1) > 0)   this.de88_DebitsAmt = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((de1_SecondaryBitmap[3] & 128) > 0) this.de89_DebitRevsAmt = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((de1_SecondaryBitmap[3] & 64) > 0)  this.de90_OrigDataElem = GeneralUtils.ReadFixString(buffer, ref, 42);
            if ((de1_SecondaryBitmap[3] & 32) > 0)  this.de91_FileUpdateCode = GeneralUtils.ReadFixString(buffer, ref, 1);
            if ((de1_SecondaryBitmap[3] & 16) > 0)  this.de92_FileSecCode = GeneralUtils.ReadFixString(buffer, ref, 2);
            if ((de1_SecondaryBitmap[3] & 8) > 0)   this.de93_RespInd = GeneralUtils.ReadFixString(buffer, ref, 5);
            if ((de1_SecondaryBitmap[3] & 4) > 0)   this.de94_ServInd = GeneralUtils.ReadFixString(buffer, ref, 7);
            if ((de1_SecondaryBitmap[3] & 2) > 0)   this.de95_ReplAmts = GeneralUtils.ReadFixString(buffer, ref, 42);
            if ((de1_SecondaryBitmap[3] & 1) > 0)   this.de96_MsgSecCode = GeneralUtils.ReadFixByte(buffer, ref, 8);
            if ((de1_SecondaryBitmap[4] & 128) > 0) this.de97_AmtNetSetl = GeneralUtils.ReadFixString(buffer, ref, 16);
            if ((de1_SecondaryBitmap[4] & 64) > 0)  this.de98_Payee = GeneralUtils.ReadVar2RawByte(buffer, ref, 25);
            if ((de1_SecondaryBitmap[4] & 32) > 0)  this.de99_SettleInstIdCode = GeneralUtils.ReadVar2Raw(buffer, ref, 11);
            if ((de1_SecondaryBitmap[4] & 16) > 0)  this.de100_RecvInstIdCode = GeneralUtils.ReadVar2Raw(buffer, ref, 11);
            if ((de1_SecondaryBitmap[4] & 8) > 0)   this.de101_FileName = GeneralUtils.ReadVar2Raw(buffer, ref, 17);
            if ((de1_SecondaryBitmap[4] & 4) > 0)   this.de102_AcctId1 = GeneralUtils.ReadVar2RawByte(buffer, ref, 28);
            if ((de1_SecondaryBitmap[4] & 2) > 0)   this.de103_AcctId2 = GeneralUtils.ReadVar2RawByte(buffer, ref, 28);
            if ((de1_SecondaryBitmap[4] & 1) > 0)   this.de104_TxnDesc = GeneralUtils.ReadVar3RawByte(buffer, ref, 100);
            if ((de1_SecondaryBitmap[5] & 128) > 0) this.de105_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 64) > 0)  this.de106_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 32) > 0)  this.de107_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 16) > 0)  this.de108_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 8) > 0)   this.de109_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 4) > 0)   this.de110_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 2) > 0)   this.de111_ResvIso = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[5] & 1) > 0)   this.de112_ResvNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 128) > 0) this.de113_ResvNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 64) > 0)  this.de114_ResvNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 32) > 0)  this.de115_ResvNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 16) > 0)  this.de116_ResvNat = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 8) > 0)   this.de117_CardStatUpdCode = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 4) > 0)   this.de118_TotalCashNo = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 2) > 0)   this.de119_TotalCashAmt = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[6] & 1) > 0)   this.de120_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 128) > 0) this.de121_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 64) > 0)  this.de122_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 32) > 0)  this.de123_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 16) > 0)  this.de124_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 8) > 0)   this.de125_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 4) > 0)   this.de126_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 2) > 0)   this.de127_ResvPriv = GeneralUtils.ReadVar3RawByte(buffer, ref, 999);
            if ((de1_SecondaryBitmap[7] & 1) > 0)   this.de128_MAC = GeneralUtils.ReadFixByte(buffer, ref, 8); //Viene empaquetado
            
		} catch (Exception e) {
			
			log = new Logger();
			log.WriteLogMonitor("ERROR CONSTRUCTOR IsoBynary(byte[] buffer)", TypeMonitor.error, e);
			this.de39_RespCode = "99";
			this.setDe120_ResvPriv(GeneralUtils.ExceptionToString("ERROR ", e)
					.getBytes());
			
		}
        
	}
}

