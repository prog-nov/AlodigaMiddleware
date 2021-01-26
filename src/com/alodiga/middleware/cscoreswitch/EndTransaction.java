package com.alodiga.middleware.cscoreswitch;

import com.alodiga.middleware.logger.Logger;

public class EndTransaction extends Thread {

	private wIso8583 iso;
	private Iso8583 iso8583;
	private Logger log = null;
	private boolean flagContinueTrx = false;
	
	public EndTransaction(wIso8583 iso, Iso8583 iso8583, boolean flagContinue){
		this.iso = iso;
		this.iso8583 = iso8583;
		this.log = new Logger();
		this.flagContinueTrx = flagContinue;
	}
	
	public void EndTransactionSwitch(wIso8583 iso, Iso8583 iso8583){		
		
			
		
	}

	@Override
	public void run() {		
		EndTransactionSwitch(this.iso, this.iso8583);
	}
	
	
}
