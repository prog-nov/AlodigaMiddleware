package com.alodiga.middleware.cscoreswitch;

public interface IProcessTransaction {

	abstract Iso8583 processtransaction(Iso8583 iso);
}
