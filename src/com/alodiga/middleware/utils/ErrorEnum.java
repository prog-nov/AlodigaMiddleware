package com.alodiga.middleware.utils;

public enum ErrorEnum {
	TRANSACCION_APROBADA("00", "Transaccion aprobada.","Aprobada"),
	CONTACTE_AL_BANCO_EMISOR_DE_LA_TARJETA("01", "Contacte al banco emisor de la tarjeta.","Declinada"),
	LLAMAR_AL_BANCO("02", "Llamar al banco.","Declinada"),
	COMERCIO_INVALIDO("03", "Comercio invalido.","Declinada"),
	RETENER_TARJETA_04("04", "Retener tarjeta.","Retener"),
	TRANSACCION_NEGADA("05", "Transacción negada.","Negada"),
	BANCO_EMISOR_NO_RESPONDE("08", "Banco emisor no responde.","Declinada"),
	TRANSACCIÓN_INVALIDA("12", "Transacción invalida.","Declinada"),
	MONTO_INVALIDO("13", "Monto inválido.","Declinada"),
	TARJETA_INVALIDA("14", "Tarjeta inválida.","Declinada"),
	ERROR_DE_FORMATO_DE_LA_TRANSACCION("30", "Error de formato de la transaccion.","Declinada"),
	TRANSACCION_NO_PERMITIDA("39", "Transacción no permitida.","Declinada"),
	TARJETA_PERDIDA("41", "Tarjeta perdida.","Retener"),
	RETENER_TARJETA_42("42", "Retener tarjeta.","Retener"),
	TARJETA_ROBADA("43", "Tarjeta robada.","Retener"),
	RETENER_TARJETA_44("44", "Retener tarjeta.","Retener"),
	FONDOS_INSUFICIENTES("51", "Fondos insuficientes.","Declinada"),
	TARJETA_VENCIDA("54", "Tarjeta vencida.","Declinada"),
	PIN_INVALIDO("55", "PIN invalido.","Declinada"),
	TRANSACCION_NO_PERMITIDA_AL_EMISOR_O_AL_TITULAR_DE_LA_TARJETA("57", "Transacción no permitida al emisor o al titular de la tarjeta.","Declinada"),
	TRANSACCIO_NO_PERMITIDA_AL_ADQUIRENTE_O_AL_TERMINAL("58", "Transacción no permitida al adquirente o al terminal.","Declinada"),
	EXCEDE_EL_LIMITE_DE_RETIRO("61", "Excede el límite de retiro.","Declinada"),
	TARJETA_RESTRINGIDA("62", "Tarjeta restringida.","Declinada"),
	VIOLACION_DE_LA_SEGURIDAD("63", "Violación de la seguridad.","Declinada"),
	EXCEDIDO_LIMITE_DE_RETIROS_EN_CUENTA("65", "Excedido límite de retiros en cuenta.","Declinada"),
	RETENER_TARJETA_67("67", "Retener tarjeta.","Retener"),
	EXCEDIDO_NUMERO_DE_REINTENTOS_DE_PIN_INVALIDOS("75", "Excedido numero de reintentos de PIN invalidos.","Declinada"),
	CUENTA_INVALIDA_76("76", "Cuenta invalida.","Declinada"),
	CUENTA_INVALIDA_77("77", "Cuenta invalida.","Declinada"),
	EMISOR_NO_DISPONIBLE("80", "Emisor no disponible.","Declinada"),
	ERROR_EN_LLAVE("81", "Error en llave.","Declinada"),
	SISTEMA_NO_DISPONIBLE("91", "Sistema no disponible.","Declinada"),
	EMISOR_INVALIDO("92", "Emisor invalido.","Declinada"),
	ERROR_EN_SISTEMA("96", "Error en sistema.","Declinada");
	
	private String cod;
	private String desc;
	private String acc;
	
	
	ErrorEnum(String codigo, String descripcion, String accion){
		cod = codigo;
		desc = descripcion;
		acc = accion;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public static ErrorEnum find(String ws) {
		for (ErrorEnum t : values()) {
			if (t.getDesc().equals(ws)) {
				return t;
			}
		}
		return null;
	}

	public static ErrorEnum find(Integer cod) {
		for (ErrorEnum t : values()) {
			if (Integer.valueOf(t.getCod()).equals(cod)) {
				return t;
			}
		}
		return null;
	}

}
