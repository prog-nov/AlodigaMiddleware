package com.alodiga.middleware.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;


public class FormatUtils {

	public static String DateToString(Date date, String formatText){
		
		Logger log = null;
		try {
			DateFormat df = new SimpleDateFormat(formatText);
			Date today = date;        
			String reportDate = df.format(today);
			return reportDate;
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo FormatUtils::DateToString ", TypeMonitor.error, e);
			return null;
		}
	}
	
	public static String DateToString(String dateString, String formatSource, String formatTarget){
			
			Logger log = null;
			Date dateAux = null;
			DateFormat format = null;
			try {
				format = new SimpleDateFormat(formatSource);
				dateAux = format.parse(dateString);
				String fecha = FormatUtils.DateToString(dateAux, formatTarget);
				return fecha;
			} catch (Exception e) {
				log = new Logger();
				log.WriteLogMonitor("Error modulo FormatUtils::DateToString II", TypeMonitor.error, e);
				return null;
			}
		}
	
	public static Date StringToDate(String date, String format){
		
		Logger log = null;
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.US);
			Date dater = formatter.parse(date);
			return dater;
			
		} catch (Exception e) {
			
			log = new Logger();
			log.WriteLogMonitor("Error modulo FormatUtils::StringToDate ", TypeMonitor.error, e);
			return null;
		}
	}
	public static Date StringToDateTrim(String date, String format){
		
		Logger log = null;
		try {
			
			String nuevo = "";
			for (char c : date.toCharArray()) {
				String f = String.valueOf(c);
				if(StringUtils.isNotBlank(f))
					nuevo += f;
			}
			SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.US);
			Date dater = formatter.parse(nuevo);
			return dater;
			
		} catch (Exception e) {
			
			log = new Logger();
			log.WriteLogMonitor("Error modulo FormatUtils::StringToDate ", TypeMonitor.error, e);
			return null;
		}
	}
	
	public static Date StringToDateIso(String date, String format){
		Logger log = null;
		try {
			
			Date _date;
		    SimpleDateFormat df = new SimpleDateFormat(format);
		    _date = df.parse(date);
			return _date;
			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo FormatUtils::StringToDate ", TypeMonitor.error, e);
			return new Date();
		}
	}
	
	public static double TruncateDouble(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }
}
