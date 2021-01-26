package com.alodiga.temporal.cache;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;



@SuppressWarnings("rawtypes")
public class ValidTransaction extends Thread implements Callable {

	private String proccode;
	private String canal_cod;
	private int net_id;
	private double ammountdebit;
	private String v_campoiso;
	private String v_factorvalidate;
	private String v_control;
	private String v_message;

	public String getProccode() {
		return proccode;
	}

	public void setProccode(String proccode) {
		this.proccode = proccode;
	}

	public String getCanal_cod() {
		return canal_cod;
	}

	public void setCanal_cod(String canal_cod) {
		this.canal_cod = canal_cod;
	}

	public int getNet_id() {
		return net_id;
	}

	public void setNet_id(int net_id) {
		this.net_id = net_id;
	}

	public double getAmmountdebit() {
		return ammountdebit;
	}

	public void setAmmountdebit(double ammountdebit) {
		this.ammountdebit = ammountdebit;
	}

	public String getV_campoiso() {
		return v_campoiso;
	}

	public void setV_campoiso(String v_campoiso) {
		this.v_campoiso = v_campoiso;
	}

	public String getV_factorvalidate() {
		return v_factorvalidate;
	}

	public void setV_factorvalidate(String v_factorvalidate) {
		this.v_factorvalidate = v_factorvalidate;
	}

	public String getV_control() {
		return v_control;
	}

	public void setV_control(String v_control) {
		this.v_control = v_control;
	}

	public String getV_message() {
		return v_message;
	}

	public void setV_message(String v_message) {
		this.v_message = v_message;
	}

	@Override 
	public String toString(){
		
		return this.getProccode() + "  " + this.getCanal_cod() + "  " + 
			   this.getNet_id() + "  " + this.getAmmountdebit() + "  " +
			   this.getV_campoiso() + "  " + this.getV_control() + "  " +
			   this.getV_factorvalidate() + " "  + this.getV_message();
	}
	public ValidTransaction(String Proccode, int NetId, String CanalCod, double AmmountDebit){
		this.proccode = Proccode;
		this.net_id = NetId;
		this.canal_cod = CanalCod;
		this.ammountdebit = AmmountDebit;
	}
	
	public ValidTransaction() {
		
	}

	public List<ValidTransaction> getValidTrxfigListObject(String Proccode, int NetId, String CanalCod, double AmmountDebit){
		List<ValidTransaction> lista = null;
		Logger log = null;
		try {
			
//			lista =   MemoryGlobal.ListValidTransactionMem.stream().
//					  filter(p -> p.getProccode().equals(Proccode) && 
//							    p.getCanal_cod().equals(CanalCod) && 
//							    p.getAmmountdebit() == AmmountDebit && 
//							    p.getNet_id() == NetId)
//					.peek(Objects::requireNonNull)
//				    .collect(Collectors.toList());
			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo ValidTransaction::getValidTrxfigListObject ", TypeMonitor.error, e);
		}
		return lista;
	}
	
	@Override
	public void run(){
	
	}

	@Override
	public List<ValidTransaction> call() throws Exception {		
		List<ValidTransaction> t = getValidTrxfigListObject(this.proccode, this.net_id, 
															this.canal_cod, this.ammountdebit);
		return t;
		
	}
	
}