package com.alodiga.middleware.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;


public class RowsValidationUtils {

	
	public static boolean validateDate(String dateToValidate, boolean actual){		
		
		Logger log = null;
		String fechString = formatDate(dateToValidate, "EEEE MMMM d HH:mm:ss z yyyy", 
							"yyyy/MM/dd HH:mm:ss", Locale.US);		
		if(dateToValidate == null){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			
			Date date = sdf.parse(fechString);
			if(actual){			
				SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
				Date fechaActual = new Date();
				int IfechaActual = Integer.parseInt(formateador.format(fechaActual));
				int IfechaParam  = Integer.parseInt(formateador.format(date));
				if(IfechaParam > IfechaActual)
					return false;
				else
					return true;					
			}
			else
				return true;
		} catch (ParseException e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo RowsValidationUtils::validateDate ", TypeMonitor.error, e);
			return false;
		}
		catch (Exception e) {			
			return false;
		}
    }
	public static boolean validateInt(String valor){
		
		Logger log = null;
		try {			
			@SuppressWarnings("unused")
			long val = Long.parseLong(valor);
			return true;
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo RowsValidationUtils::validateInt ", TypeMonitor.error, e);
			return false;
		}
	}
	public static boolean validateDecimal(String valor){
		
		Logger log = null;
		try {			
			@SuppressWarnings("unused")
			double val = Double.parseDouble(valor);
			return true;
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo RowsValidationUtils::validateDecimal ", TypeMonitor.error, e);
			return false;
		}
	}
	public static String formatDate(String fecha, String oldFormat, String newFormat, Locale l){
		
		Logger log = null;
		String formattedDate = "";
		String dateStr = fecha;
	    DateFormat readFormat = new SimpleDateFormat(oldFormat, l);
	    DateFormat writeFormat = new SimpleDateFormat(newFormat);
	    Date date = null;
	    try {
	       date = readFormat.parse(dateStr);
	    } catch ( ParseException e ) {
	    	log = new Logger();
			log.WriteLogMonitor("Error modulo RowsValidationUtils::formatDate ", TypeMonitor.error, e);
	    }	    
	    if( date != null ) {
	    formattedDate = writeFormat.format(date);
	    }
	    return formattedDate;
	}
}
