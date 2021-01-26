package com.alodiga.middleware.cscoreswitch;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.time.StopWatch;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.utils.GeneralUtils;
import com.alodiga.middleware.utils.RowsValidationUtils;
import com.alodiga.middleware.utils.StringUtils;
import com.alodiga.temporal.cache.TransactionConfig;
import com.alodiga.temporal.cache.ValidTransaction;


public class wIso8583 extends Iso8583 implements Serializable, Cloneable {

	private static final long serialVersionUID = 6420368067177083887L;
	private int wsIso_LogStatus;
	private Date wsISO_TranDatetime;
	private Date wsISO_TranDatetimeResponse;
	private int wsISO_SFRetryCounts;
	private int wsISO_SF_Count;
	private int wsIsoSF_CountVisualizer;
	private boolean wsISO_FlagStoreForward;
	private Date wsIniBDD;
	private Date wsFinBDD;
	private double wsTempTrx;
	private double wsTempBDD;
	private double wsTempAut;
	private double wISO_012_LocalDatetime_decimal;
	private String wISO_IsReverso;
	private String ws_IP;
	private String ws_flagCupos;
	private String ws_valoresCupos;
	private transient StopWatch tickAut;
	private transient StopWatch tickBdd;
	private transient  StopWatch tickMidd;
	private TransactionConfiguration wsTransactionConfig;
	
	private transient  Logger log;
	
	public int getWsIso_LogStatus() {
		return wsIso_LogStatus;
	}
	public void setWsIso_LogStatus(int wsIso_LogStatus) {
		this.wsIso_LogStatus = wsIso_LogStatus;
	}
	public Date getWsISO_TranDatetime() {
		return wsISO_TranDatetime;
	}
	public void setWsISO_TranDatetime(Date wsISO_TranDatetime) {
		this.wsISO_TranDatetime = wsISO_TranDatetime;
	}
	public Date getWsISO_TranDatetimeResponse() {
		return wsISO_TranDatetimeResponse;
	}
	public void setWsISO_TranDatetimeResponse(Date wsISO_TranDatetimeResponse) {
		this.wsISO_TranDatetimeResponse = wsISO_TranDatetimeResponse;
	}
	public int getWsISO_SFRetryCounts() {
		return wsISO_SFRetryCounts;
	}
	public void setWsISO_SFRetryCounts(int wsISO_SFRetryCounts) {
		this.wsISO_SFRetryCounts = wsISO_SFRetryCounts;
	}
	public int getWsISO_SF_Count() {
		return wsISO_SF_Count;
	}
	public void setWsISO_SF_Count(int wsISO_SF_Count) {
		this.wsISO_SF_Count = wsISO_SF_Count;
	}
	public boolean getWsISO_FlagStoreForward() {
		return wsISO_FlagStoreForward;
	}
	public void setWsISO_FlagStoreForward(boolean wsISO_FlagStoreForward) {
		this.wsISO_FlagStoreForward = wsISO_FlagStoreForward;
	}
	public Date getWsIniBDD() {
		return wsIniBDD;
	}
	public void setWsIniBDD(Date wsIniBDD) {
		this.wsIniBDD = wsIniBDD;
	}
	public Date getWsFinBDD() {
		return wsFinBDD;
	}
	public void setWsFinBDD(Date wsFinBDD) {
		this.wsFinBDD = wsFinBDD;
	}
	public double getWsTempTrx() {
		return wsTempTrx;
	}
	public void setWsTempTrx(double wsTempTrx) {
		this.wsTempTrx = wsTempTrx;
	}
	public double getWsTempBDD() {
		return wsTempBDD;
	}
	public void setWsTempBDD(double wsTempBDD) {
		this.wsTempBDD = wsTempBDD;
	}
	public double getWsTempAut() {
		return wsTempAut;
	}
	public void setWsTempAut(double wsTempAut) {
		this.wsTempAut = wsTempAut;
	}
	public double getwISO_012_LocalDatetime_decimal() {
		return wISO_012_LocalDatetime_decimal;
	}
	public void setwISO_012_LocalDatetime_decimal(
			double wISO_012_LocalDatetime_decimal) {
		this.wISO_012_LocalDatetime_decimal = wISO_012_LocalDatetime_decimal;
	}
	public String getwISO_IsReverso() {
		return wISO_IsReverso;
	}
	public void setwISO_IsReverso(String wISO_IsReverso) {
		this.wISO_IsReverso = wISO_IsReverso;
	}
	public String getWs_IP() {
		return ws_IP;
	}
	public void setWs_IP(String ws_IP) {
		this.ws_IP = ws_IP;
	}
	public String getWs_flagCupos() {
		return ws_flagCupos;
	}
	public void setWs_flagCupos(String ws_flagCupos) {
		this.ws_flagCupos = ws_flagCupos;
	}
	public String getWs_valoresCupos() {
		return ws_valoresCupos;
	}
	public void setWs_valoresCupos(String ws_valoresCupos) {
		this.ws_valoresCupos = ws_valoresCupos;
	}
	@Transient
	public StopWatch getTickAut() {
		return tickAut;
	}
	public void setTickAut(StopWatch tickAut) {
		this.tickAut = tickAut;
	}
	@Transient
	public StopWatch getTickBdd() {
		return tickBdd;
	}
	public void setTickBdd(StopWatch tickBdd) {
		this.tickBdd = tickBdd;
	}
	@Transient
	public StopWatch getTickMidd() {
		return tickMidd;
	}
	public void setTickMidd(StopWatch tickMidd) {
		this.tickMidd = tickMidd;
	}
	public TransactionConfiguration getWsTransactionConfig() {
		return wsTransactionConfig;
	}
	public void setWsTransactionConfig(TransactionConfiguration wsTransactionConfig) {
		this.wsTransactionConfig = wsTransactionConfig;
	}
	public int getWsIsoSF_CountVisualizer() {
		return wsIsoSF_CountVisualizer;
	}
	public void setWsIsoSF_CountVisualizer(int wsIsoSF_CountVisualizer) {
		this.wsIsoSF_CountVisualizer = wsIsoSF_CountVisualizer;
	}
	public wIso8583(){
		super();	
		this.wsISO_TranDatetime = new Date();
		this.wsIso_LogStatus = 1;
		this.wsISO_TranDatetimeResponse = new Date();
		this.wsISO_FlagStoreForward = false;
		this.tickAut = new StopWatch();
		this.tickBdd = new StopWatch();
		this.wsIsoSF_CountVisualizer = 1;
		this.log = new Logger();
	}	
	@SuppressWarnings("unused")
	public wIso8583(Iso8583 iso, String IP) {		
		this();
		iso = validateTramaIso(iso);
		if(iso.getISO_039_ResponseCode().equals("000")){			
			try{
				String[] values = iso.getISO_039p_ResponseDetail().split("_");
				TransactionConfiguration trxConf = 
						  new TransactionConfiguration
						 (new TransactionConfig(values[0], Integer.parseInt(values[1]),
						 values[2], Double.parseDouble(values[3])));
				
				 if(trxConf != null){
				
					if(trxConf.getStore_Forward_Num() >= 0 )
						this.setWsISO_SFRetryCounts(trxConf.getStore_Forward_Num());
					if(iso.getISO_023_CardSeq() != null){
						if(iso.getISO_000_Message_Type().equals("1400") && iso.getISO_023_CardSeq().equals("SF"))
							this.setWsISO_SF_Count(1);
						else
							this.setWsISO_SF_Count(0);
					}
					 
					int a = trxConf.getCanal_status(); 
					trxConf.setIp(IP);
					this.setWsTransactionConfig(trxConf);												
					this.setISO_000_Message_Type(iso.getISO_000_Message_Type());
					this.setISO_002_PAN(iso.getISO_002_PAN());
					this.setISO_003_ProcessingCode(iso.getISO_003_ProcessingCode());
					this.setISO_004_AmountTransaction(iso.getISO_004_AmountTransaction());
					this.setISO_006_BillAmount(iso.getISO_006_BillAmount());
					this.setISO_007_TransDatetime(iso.getISO_007_TransDatetime());
					this.setISO_008_BillFeeAmount(iso.getISO_008_BillFeeAmount());
					this.setISO_011_SysAuditNumber(iso.getISO_011_SysAuditNumber());
					this.setISO_012_LocalDatetime(iso.getISO_012_LocalDatetime());
					this.setISO_013_LocalDate(iso.getISO_013_LocalDate());
					this.setISO_015_SettlementDatel(iso.getISO_015_SettlementDatel());
					this.setISO_018_MerchantType(iso.getISO_018_MerchantType());
					this.setISO_019_AcqCountryCode(iso.getISO_019_AcqCountryCode());
					this.setISO_022_PosEntryMode(iso.getISO_022_PosEntryMode());
					this.setISO_023_CardSeq(iso.getISO_023_CardSeq());
					this.setISO_024_NetworkId(iso.getISO_024_NetworkId());
					this.setISO_028_TranFeeAmount(iso.getISO_028_TranFeeAmount());
					this.setISO_029_SettlementFee(iso.getISO_029_SettlementFee());
					this.setISO_030_ProcFee(iso.getISO_030_ProcFee());
					this.setISO_032_ACQInsID(iso.getISO_032_ACQInsID());
					this.setISO_033_FWDInsID(iso.getISO_033_FWDInsID());
					this.setISO_034_PANExt(iso.getISO_034_PANExt());
					this.setISO_035_Track2(iso.getISO_035_Track2());
					this.setISO_036_Track3(iso.getISO_036_Track3());
					this.setISO_037_RetrievalReferenceNumber(iso.getISO_037_RetrievalReferenceNumber());
					this.setISO_038_AutorizationNumber(iso.getISO_038_AutorizationNumber());
					this.setISO_039_ResponseCode(iso.getISO_039_ResponseCode());
					this.setISO_041_CardAcceptorID(iso.getISO_041_CardAcceptorID());
					this.setISO_042_Card_Acc_ID_Code(iso.getISO_042_Card_Acc_ID_Code());
					this.setISO_043_CardAcceptorLoc(iso.getISO_043_CardAcceptorLoc());
					this.setISO_044_AddRespData(iso.getISO_044_AddRespData());
					this.setISO_049_TranCurrCode(iso.getISO_049_TranCurrCode());
					this.setISO_051_CardCurrCode(iso.getISO_051_CardCurrCode());
					this.setISO_052_PinBlock(iso.getISO_052_PinBlock());
					this.setISO_054_AditionalAmounts(iso.getISO_054_AditionalAmounts());
					this.setISO_055_EMV(iso.getISO_055_EMV());
					this.setISO_090_OriginalData(iso.getISO_090_OriginalData());
					this.setISO_102_AccountID_1(iso.getISO_102_AccountID_1());
					this.setISO_103_AccountID_2(iso.getISO_103_AccountID_2());
					this.setISO_104_TranDescription(iso.getISO_104_TranDescription());
					this.setISO_114_ExtendedData(iso.getISO_114_ExtendedData());
					this.setISO_115_ExtendedData(iso.getISO_115_ExtendedData());
					this.setISO_120_ExtendedData(iso.getISO_120_ExtendedData());
					this.setISO_121_ExtendedData(iso.getISO_121_ExtendedData());
					this.setISO_122_ExtendedData(iso.getISO_122_ExtendedData());
					this.setISO_123_ExtendedData(iso.getISO_123_ExtendedData());
					this.setISO_124_ExtendedData(iso.getISO_124_ExtendedData());
					this.setISO_BitMap(iso.getISO_BitMap());		
					this.setWs_IP(IP);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(iso.getISO_012_LocalDatetime());
					double dateDecimal = Double.parseDouble(new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime()));
					this.setwISO_012_LocalDatetime_decimal(dateDecimal);
					
				 }
				 else{
					 this.setISO_039_ResponseCode("909");
					 this.setISO_039p_ResponseDetail("ERROR AL CARGAR INFO TRANSACTION CONFIGURATION");
				 }
					 				
			}catch(Exception ex){
				
				log.WriteLogMonitor("Error modulo wIso8583::wIso8583(Iso8583 iso, String IP) [Constructor] ", TypeMonitor.error, ex);
				this.setISO_039_ResponseCode("909");
				this.setISO_039p_ResponseDetail(GeneralUtils.ExceptionToString("ERROR EN PROCESOS ", ex));
			}				
		}
		else{
			this.setWsIso_LogStatus(1);
			this.setISO_039_ResponseCode(iso.getISO_039_ResponseCode());
			this.setISO_039p_ResponseDetail(iso.getISO_039p_ResponseDetail());	
			if(!StringUtils.IsNullOrEmpty(IP))
				iso.setISO_124_ExtendedData(IP);	
		}			
	}
	
	private Iso8583 validateTramaIso(Iso8583 iso){
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		try {
			
			String queryResult = iso.getISO_003_ProcessingCode().trim() + "_" +
				                 iso.getISO_024_NetworkId() + "_" + 
				                 iso.getISO_018_MerchantType() + "_";
			List<ValidTransaction> ListValidTransaction = null;
			
			@SuppressWarnings("unchecked")
			Callable<ValidTransaction> callableV = new 
					 ValidTransaction(iso.getISO_003_ProcessingCode().trim(), 
							 		  Integer.parseInt(iso.getISO_024_NetworkId()), 
							 		  iso.getISO_018_MerchantType(), 
							 		  iso.getISO_004_AmountTransaction());
			
			@SuppressWarnings("unchecked")
			Callable<ValidTransaction> callableN = new 
					 ValidTransaction(iso.getISO_003_ProcessingCode().trim(), 
							 		  Integer.parseInt(iso.getISO_024_NetworkId()), 
							 		  iso.getISO_018_MerchantType(), 
							 		  -1);		
			Future<ValidTransaction> futureV = pool.submit(callableV);
			Future<ValidTransaction> futureN = pool.submit(callableN);	
			
					
			@SuppressWarnings("unchecked")
			List<ValidTransaction> ListValidTrxValor = (List<ValidTransaction>) futureV.get();		
			@SuppressWarnings("unchecked")
			List<ValidTransaction> ListValidTrxCommon = (List<ValidTransaction>) futureN.get();
					
		
			
		log.WriteLogMonitor("LLego el mensaje Via SOAP", TypeMonitor.error, new Exception());
		
			
		
			
			
			if(ListValidTrxValor.size() > 0){		
				ListValidTransaction = ListValidTrxValor;
				queryResult +=  String.valueOf(iso.getISO_004_AmountTransaction());
			}
			else{
				ListValidTransaction = ListValidTrxCommon;
				queryResult += "-1";
			}
			
			if(ListValidTransaction.size() > 0 && ListValidTransaction != null){				
				Map<String,String> arrIso = ObtenerElementosISO(iso);
				if(arrIso != null && arrIso.size() > 0){		
					String retornoValidacion = null;
					for (ValidTransaction validTransaction : ListValidTrxCommon) {
						
						String campoIso = validTransaction.getV_campoiso();
						String factor = validTransaction.getV_factorvalidate();
						String msg = StringUtils.IsNullOrEmpty(validTransaction.getV_message()) ? 
								     "" : validTransaction.getV_message();
						String campoValidar = arrIso.get(campoIso);
						if(!(StringUtils.isNullOrEmpty(campoValidar))){							
							retornoValidacion = procesaValidacion(factor, campoValidar);
							if(!retornoValidacion.startsWith("OK")){
								
								iso.setISO_039_ResponseCode("905");
								iso.setISO_039p_ResponseDetail(retornoValidacion + "CAMPO ISO_" + campoIso + " (" + msg + ")");								
								return iso;
							}
							else 
								iso.setISO_039_ResponseCode("000");
						}
						else{
							iso.setISO_039_ResponseCode("905");
							iso.setISO_039p_ResponseDetail("CAMPO: ISO_" + campoIso + " ES MANDATORIO" + " (" + msg + ")");
							return iso;
						}														
					}
					iso.setISO_039p_ResponseDetail(queryResult);					
					return iso;
				}
				else{
					iso.setISO_039_ResponseCode("905");
					iso.setISO_039p_ResponseDetail("ERROR EN PROCESOS INTERNOS, ERROR MODULO EN VALIDACION TRAMA ISO (MAP ISO)");
					return iso;
				}
			}
			else{
				iso.setISO_039_ResponseCode("909");
				iso.setISO_039p_ResponseDetail("ERROR EN PROCESOS NO EXISTE LA CONFIGURACION VALID_TRANSACTION PARA EL PROCESSING_CODE: " + 
											iso.getISO_003_ProcessingCode() + " CANAL: " + iso.getISO_018_MerchantType() +
											" RED: " + iso.getISO_024_NetworkId() + " VALOR: " + iso.getISO_004_AmountTransaction());
				return iso;
			}																					
		} catch (InterruptedException | ExecutionException e) {	
			
			log.WriteLogMonitor("Error modulo wIso8583::validateTramaIso [Interrupted, Execution] ", TypeMonitor.error, e);
			iso.setISO_039_ResponseCode("905");
			iso.setISO_039p_ResponseDetail(GeneralUtils.ExceptionToString("ERROR VALIDACION ISO (FUTURE EXCEPTION) ", e));
			return iso;
		} catch (Exception ex) {
			log.WriteLogMonitor("Error modulo wIso8583::validateTramaIso [General] ", TypeMonitor.error, ex);
			iso.setISO_039_ResponseCode("905");
			iso.setISO_039p_ResponseDetail(GeneralUtils.ExceptionToString("ERROR VALIDACION ISO (GENERAL) ", ex));
			return iso;
		}finally {
			pool.shutdown();
		}
		
	}
	private Map<String,String> ObtenerElementosISO(Iso8583 iso){
		String nombredelCampo = null;
		Map<String,String> map = new HashMap<String, String>();
		try {
			Field[] fields = iso.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String nombreCampo = field.getName(); 
				if(!nombreCampo.equalsIgnoreCase("serialVersionUID") && 
						!nombreCampo.equalsIgnoreCase("aditionalIso")){
					nombredelCampo = nombreCampo + " == " + field.getClass();
					String[] a =  field.getName().split("_");				
					map.put(a[1], String.valueOf(field.get(iso)));
				}
			}						
		} catch (Exception e) {
			log.WriteLogMonitor("Error modulo wIso8583::ObtenerElementosISO " + nombredelCampo, TypeMonitor.error, e);
			map = null;
		}
		return map;
	}
	private String procesaValidacion(String criterios, String campo)
    {
        String data = "OK";        
        try
        {
            String[] val = criterios.split("%");
            String[] longiRango = val[0].split("-");
            String[] valoresDef = val[1].split(",");
            String[] valoresRango = val[1].split("-");

            if (!val[0].equals("-1"))
            {
                if (longiRango.length > 1) 
                {                	
                    int A = Integer.parseInt(longiRango[0]);
                    int B = Integer.parseInt(longiRango[1]);
                    int C = campo.length();
                    if (C == 0)                    
                        return "CAMPO MANDATORIO: ";                    
                    else
                    {
                        if (!(C >= A && C <= B))
                            return "ERROR EN RANGO ESTABLECIDO ";
                    }
                }
                else 
                {
                    if (campo.length() == 0)
                        return "CAMPO OBLIGATORIO: ";
                    else if (campo.length() != Integer.parseInt(val[0]))
                        return "ERROR LONGITUD ";
                }
            }
            if (!val[2].equals("-1")) 
            {
                switch (val[2])
                {
                    case "D":
                        boolean rD = RowsValidationUtils.validateDecimal(campo);
                        if (!rD)
                            return "ERROR TIPO DE DATO ";
                        break;
                    case "D0":
                    	boolean rD0 = RowsValidationUtils.validateDecimal(campo);
                        if (!rD0)
                            return "ERROR TIPO DE DATO ";
                        else
                        { 
                            if(Double.parseDouble(campo) <= 0)
                                return "ERROR EL VALOR DEBE SER MAYOR QUE CERO ";
                        }
                        break;
                    case "F":
                    	boolean rF = RowsValidationUtils.validateDate(campo, false);
                        if (!rF)
                            return "CAMPO INVALIDO ";
                        break;
                    case "Fh":
                    	boolean rFh = RowsValidationUtils.validateDate(campo, true);
                        if (!rFh)
                            return "CAMPO INVALIDO ";
                        break;
                    case "N":
                    	boolean rN = RowsValidationUtils.validateInt(campo);
                        if (!rN)
                            return "ERROR TIPO DE DATO ";
                        break;
                    case "N0":
                    	boolean rN0 = RowsValidationUtils.validateInt(campo);
                        if (!rN0)
                            return "ERROR TIPO DE DATO ";
                        else
                        {                        	
                            if (Integer.parseInt(campo) <= 0)
                                return "ERROR EL VALOR DEBE SER MAYOR QUE CERO ";
                        }
                        break;
                    default:
                        break;
                }
            }
            if (!val[1].equals("-1"))
            {
                if (valoresRango.length > 1)
                {                	
                    double A = Double.parseDouble(valoresRango[0]);
                    double B = Double.parseDouble(valoresRango[1]);
                    double C = Double.parseDouble(campo);
                    if (!(C >= A && C <= B))
                        return "ERROR EN RANGO DE VALORES ESTABLECIDOS ";
                }
                else
                {
                    int permitidos = 0;
                    for (int k = 0; k < valoresDef.length; k++)
                    {
                        if (campo.equals(valoresDef[k]))
                        {
                            permitidos++;
                            break;
                        }
                    }
                    if (permitidos == 0)
                        return "CAMPO INVALIDO ";
                }
            }
        }
        catch (Exception ex)
        {
        	log.WriteLogMonitor("Error modulo wIso8583::procesaValidacion ", TypeMonitor.error, ex);
            data = "ERROR EN VALIDACION ";
        }
        return data;
    }
	@Override
	public Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
	
	public List<wIso8583> Retrieve() throws Exception {
		
		List<wIso8583> isoList = null;
		String query = "SELECT * FROM Iso8583";		
//		DataSetMemoryLoader<wIso8583> loader = 
//	    new DataSetMemoryLoader<wIso8583>
//		(MemoryGlobal.conn, wIso8583.class, query);
//		isoList = loader.LoadDataClass();
		return isoList;
		
	}
	 	
	
}

