package com.alodiga.middleware.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceCodeMappingComparer {
	Map<String, ErrorEnum> map = null;


	public ServiceCodeMappingComparer() {
		map = new HashMap<String, ErrorEnum>();
		map.put(MapeoCodigosAuthorizerEnum.SUCCESS.getCode(), ErrorEnum.TRANSACCION_APROBADA);
		map.put(MapeoCodigosAuthorizerEnum.CARD_NOT_VALIDATE.getCode(), ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.CARD_NOT_EXISTS.getCode(), ErrorEnum.TARJETA_INVALIDA);
		map.put( MapeoCodigosAuthorizerEnum.MINIMUM_AMOUNT_NOT_ALLOWED.getCode(), ErrorEnum.MONTO_INVALIDO);
		map.put(MapeoCodigosAuthorizerEnum.MAXIMUM_AMOUNT_IS_NOT_ALLOWED.getCode(), ErrorEnum.MONTO_INVALIDO);
		map.put(MapeoCodigosAuthorizerEnum.BALANCE_NOT_AVAILABLE.getCode(),ErrorEnum.FONDOS_INSUFICIENTES);
		map.put(MapeoCodigosAuthorizerEnum.INTERNAL_ERROR.getCode(),ErrorEnum.ERROR_EN_SISTEMA);
		map.put(MapeoCodigosAuthorizerEnum.CHECK_DIGIT_CARD_INCORRECT.getCode(),ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.CARD_NOT_VALIDATE.getCode(),ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.TRANSACTION_QUANTITY_LIMIT_DAILY.getCode(),ErrorEnum.EXCEDE_EL_LIMITE_DE_RETIRO);
		map.put(MapeoCodigosAuthorizerEnum.TRANSACTION_AMOUNT_LIMIT_DAILY.getCode(),ErrorEnum.EXCEDE_EL_LIMITE_DE_RETIRO);
		map.put(MapeoCodigosAuthorizerEnum.TRANSACTION_QUANTITY_LIMIT_MONTHLY.getCode(),ErrorEnum.EXCEDE_EL_LIMITE_DE_RETIRO);
		map.put(MapeoCodigosAuthorizerEnum.TRANSACTION_AMOUNT_LIMIT_MONTHLY.getCode(),ErrorEnum.EXCEDE_EL_LIMITE_DE_RETIRO);
		map.put(MapeoCodigosAuthorizerEnum.CARD_NOT_EXISTS.getCode(),ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.THE_CARDHOLDER_NOT_MATCH.getCode(),ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.CARD_NOT_FOUND.getCode(),ErrorEnum.TARJETA_INVALIDA);
		map.put(MapeoCodigosAuthorizerEnum.PIN_OFFSET_DIFFERENT.getCode(),ErrorEnum.PIN_INVALIDO);
		map.put(MapeoCodigosAuthorizerEnum.CARD_PURCHAGE_SUCCESS.getCode(),ErrorEnum.TRANSACCION_APROBADA);
		
	}
	
	public ErrorEnum getEnumByCode(String code) {
		ErrorEnum myEnum = map.get((String) code);
		if (myEnum == null) {
			return ErrorEnum.ERROR_EN_SISTEMA;
		} else {
			return myEnum;
		}
	}

	
}
