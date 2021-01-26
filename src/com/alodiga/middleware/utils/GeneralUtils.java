package com.alodiga.middleware.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.xml.bind.DatatypeConverter;

import com.alodiga.middleware.cscoreswitch.ResponseBynary;
import com.alodiga.middleware.cscoreswitch.wIso8583;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeLog;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;
import com.alodiga.middleware.logger.LoggerConfig.TypeWriteLog;


public class GeneralUtils{
	
	public static Object getValue(Object value, wIso8583 iso){
		Logger log = null;
		Object obj = null;
		try {
			
			if(String.valueOf(value).startsWith("com.")){
				if(String.valueOf(value).contains("[")){
					
					String IsoRow =  String.valueOf(value).split("\\[")[0];
					String valorIso = (String) getIsoValueOf(String.valueOf(IsoRow), iso);
					String partIso = valorIso.split("\\" +String.valueOf(value).split("\\[")[1].substring(0, 1))
							         [Integer.parseInt(String.valueOf(value).split("\\[")[1].substring(1, 2))];
					obj = partIso;
				}
				else
					obj = getIsoValueOf(String.valueOf(value), iso);
			}
			else
				obj = value;
				
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::getValue ", TypeMonitor.error, e);
			e.printStackTrace();
		}
		return obj;
	}
	
	public static String GetSecuencial(int length){
		String data = "0";
		Logger log = null;
		try {
			UUID uuid = UUID.randomUUID();
			data = uuid.toString().replace("-", "").substring(0, length);			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::GetSecuencial ", TypeMonitor.error, e);
			data = String.format("%0" + length +"d", Integer.parseInt(data));
		}
		return data;
	}
	public static String ExceptionToString(String label, Exception ex){
		
		StringWriter errors = new StringWriter();
		String error = null;
		Logger log = null;
		try {
			
			if(label == null)
				label = "";
			if(ex.getMessage() == null)
				error = label + ": ";
			else
				error = label + ": " + ex.getMessage() + "  ";
			errors.append("[ERROR:] " + error + " ---> [STACKTRACE:] ");
			ex.printStackTrace(new PrintWriter(errors));
			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::ExceptionToString ", TypeMonitor.error, e);
		}
		return errors.toString();
	}
	public static Object getIsoValueOf(String IsoFieldClass, wIso8583 objIso){
		
		Logger log = null;
		
		try {
								
			Object obj = null;
			List<String> clase = Arrays.asList(IsoFieldClass.split("\\."));
			String nomClass = IsoFieldClass.replace("." + clase.get(clase.size() - 1),"");
			Class<?> isoClass = Class.forName(nomClass);
			if(clase.get(clase.size() - 2).equalsIgnoreCase("TransactionConfiguration"))
				obj = getField(isoClass,  clase.get(clase.size() - 1), objIso.getWsTransactionConfig());
			else
				obj = getField(isoClass,  clase.get(clase.size() - 1), objIso);
			
			if(obj instanceof Date){
				String fechaFormat = null;
				Date fec = (Date)obj;
				if(clase.get(clase.size() - 1).equalsIgnoreCase("ISO_015_SettlementDatel"))
					fechaFormat = FormatUtils.DateToString(fec, "yyyy-MM-dd");
				else	
					fechaFormat = FormatUtils.DateToString(fec, "yyyy-MM-dd HH:mm:ss");
				obj = fechaFormat;
			}
			return obj;
			
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::getIsoValueOf ", TypeMonitor.error, e);
			return null;
		}
	}
	public static Object getField(Class<?> clazz, String fieldName, Object obj) {
	    Class<?> tmpClass = clazz;
	    do {
	        try {
	            Field f = tmpClass.getDeclaredField(fieldName);
	            f.setAccessible(true);
	            return f.get(obj);
	        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
	            tmpClass = tmpClass.getSuperclass();
	        }
	    } while (tmpClass != null);
	    throw new RuntimeException("Campo '" + fieldName + "' no se encuentra en la clase: " + clazz);
	}
	public static Timestamp DateToTimestamp(Date date){
		
		Logger log = null;
		try {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Timestamp time = new java.sql.Timestamp(date.getTime());
			return time;
		} catch (Exception e) {
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::DateToTimestamp ", TypeMonitor.error, e);
			return null;
		}
	}
	public static String GetConnectionService(String url){
		
		String data = "";
		HttpURLConnection connection = null;
	    try {
	        @SuppressWarnings("unused")
			long inicio = System.currentTimeMillis();
	        URL u = new URL(url);
	        connection = (HttpURLConnection) u.openConnection();
	        connection.setRequestMethod("HEAD");
	
	        @SuppressWarnings("unused")
			int code = connection.getResponseCode();
	        @SuppressWarnings("unused")
			long fin = System.currentTimeMillis();
	        //System.out.println("Codigo: " + code + " t:" +(fin-inicio));
	        data = "OK";
	    } catch (MalformedURLException e) {
	            data = "Error de URL: " + e;
	    } catch (IOException e) {
	            data =  "Error de conexion: " + e;
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	    return data;
	}
	public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        return asStream(sourceIterator, false);
    }

	public static <T> Stream<T> asStream(Iterator<T> sourceIterator, boolean parallel) {
        Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
	
	public static byte[] copyAux(byte[] header, int lenHeader, int offset){
		
		Logger log = null;
		try {
			
			byte[] off = new byte[offset + lenHeader];
			byte[] offR = new byte[offset];
			System.arraycopy(header, 0, off, 0, header.length);
	    	System.arraycopy(offR, 0, off, lenHeader, offR.length);
	    	return off;
			
		} catch (Exception e) {
			
			log = new Logger();
			log.WriteLogMonitor("Error modulo GeneralUtils::copyAux ", TypeMonitor.error, e);
			return null;
		}
		
	}
	 public static Date addSeccondsDate(int seconds){
		 
		 	   Date date = new Date();
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(date); 
		       calendar.add(Calendar.SECOND, seconds); 
		       return calendar.getTime(); 
	 }
	  public static byte[] ReadFixByte(byte[] buf, Ref<Integer> offset, int len)
	  {
			
			int offsetI = offset.get();
	        byte[] val = B(buf, offsetI, len);
	        offsetI += len;
	        offset.set(offsetI);
	        return val;
	  }
	  public static void ReadFixByteReverse(byte[] campoIso, Ref<ResponseBynary> ref)
	  {
		    int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(campoIso, 0, buffer, offset, campoIso.length);
			offset += campoIso.length;
		    ref.set(new ResponseBynary(buffer, offset));
	   }
	   public static String ReadVar2Raw(byte[] buf, Ref<Integer> offset, int unused)
	   {
			int offsetI = offset.get();
	        int len = (buf[offsetI] - 0x30) * 10 + buf[offsetI + 1] - 0x30;
	        int oldoffset = offsetI;
	        offsetI += len + 2;
	        offset.set(offsetI);
	        return  new String(B(buf, oldoffset + 2, len));
	   }
	   public static void ReadVar2RawReverse(String campo, Ref<ResponseBynary> ref)
	   {
		    int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(StringUtils.padLeft(String.valueOf(campo.length()),2,"0").getBytes(), 0, buffer, offset, 2); 
		    offset+=2;
		    System.arraycopy(campo.getBytes(), 0, buffer, offset, campo.length()); offset+= campo.length();
		    ref.set(new ResponseBynary(buffer, offset));
	   }
	    public static String ReadVar3Raw(byte[] buf, Ref<Integer> offset, int unused)
	    {
			int offsetI = offset.get();
	        int len = (buf[offsetI] - 0x30) * 100 + (buf[offsetI + 1] - 0x30) * 10 + buf[offsetI + 2] - 0x30;
	        int oldoffset = offsetI;
	        offsetI += len + 3;
	        offset.set(offsetI);
	        return new String(B(buf, oldoffset + 3, len));
	    }
	    
	    public static void ReadVar3RawReverse(String campo, Ref<ResponseBynary> ref)
	    {
	    	int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(StringUtils.padLeft(String.valueOf(campo.length()),3,"0").getBytes(), 0, buffer, offset, 3); 
		    offset += 3;
		    System.arraycopy(campo.getBytes(), 0, buffer, offset, campo.length()); 
		    offset+= campo.length();
		    ref.set(new ResponseBynary(buffer, offset));
	    }
		
		public static String ReadFixString(byte[] buf, Ref<Integer> offset, int len)
	    {
			int offsetI = offset.get();
	        byte[] val = B(buf, offsetI, len);
	        offsetI += len;
	        offset.set(offsetI);
	        return new String(val);
	    }
		public static void ReadFixStringReverse(String campo, Ref<ResponseBynary> ref)
	    {
			int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(campo.getBytes(), 0, buffer, offset, campo.length()); 
		    offset += campo.length();
		    ref.set(new ResponseBynary(buffer, offset));
	    }
		
		public static byte[] ReadVar2RawByte(byte[] buf, Ref<Integer> offset, int unused)
	    {
			int offsetI = offset.get();
	        int len = (buf[offsetI] - 0x30) * 10 + buf[offsetI + 1] - 0x30;
	        int oldoffset = offsetI;
	        offsetI += len + 2;
	        offset.set(offsetI);
	        return B(buf, oldoffset + 2, len);
	    }
		
		public static void ReadVar2RawByteReverse(byte[] campo, Ref<ResponseBynary> ref)
	    {
			int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(StringUtils.padLeft(String.valueOf(campo.length),2,"0").getBytes(), 0, buffer, offset, 2); 
		    offset+=2;
		    System.arraycopy(campo, 0, buffer, offset, campo.length); 
		    offset += campo.length;
		    ref.set(new ResponseBynary(buffer, offset));
	    }
		
		public static byte[] ReadVar3RawByte(byte[] buf, Ref<Integer> offset, int unused)
	    {
			int offsetI = offset.get();
	        int len = (buf[offsetI] - 0x30) * 100 + (buf[offsetI + 1] - 0x30) * 10 + buf[offsetI + 2] - 0x30;
	        int oldoffset = offsetI;
	        offsetI += len + 3;
	        offset.set(offsetI);
	        return B(buf, oldoffset + 3, len);
	    }
		
		public static void ReadVar3RawByteReverse(byte[] campo, Ref<ResponseBynary> ref)
	    {
			int offset = ref.get().getOffset();
		    byte[] buffer = ref.get().getBuffer();
		    System.arraycopy(StringUtils.padLeft(String.valueOf(campo.length),3,"0").getBytes(), 0, buffer, offset, 3); 
		    offset += 3;
		    System.arraycopy(campo, 0, buffer, offset, campo.length); 
		    offset += campo.length;
		    ref.set(new ResponseBynary(buffer, offset));
	    }
		
		public static byte[] B(byte[] buf, int off, int len)
	    {
	        byte[] a = new byte[len];
	        System.arraycopy(buf, off, a, 0, len);
	        return a;
	    }
		
		public static byte[] getHexBitMapToString(String val){
			
			Logger log = null;
			try {
				
				byte[] bitmap = null;
				if(val.length() == 32)	
					bitmap = new byte[16];
				else
					bitmap = new byte[8];
				int j = 0;
				for (int i = 0; i < val.length(); i++) {
				
					String parte = (val.substring(i, i + 2));
					bitmap[j] = hexStringToByte(parte);
					j++;
					i++;
				}
				return bitmap;
			} catch (Exception e) {
				
				log = new Logger();
				log.WriteLogMonitor("Error modulo GeneralUtils::getHexBitMapToString ", TypeMonitor.error, e);
				return null;
			}
		}
		
		public static void setterLoggerBinary(byte[] iso){
			
			Logger log = null;
			String data = "\n";
			int offset = 0;
			try {
				
				byte [] mti = new byte[4];  
				System.arraycopy(iso, 0, mti, 0, 4); offset += 4;
				data += new String(mti);
				byte [] primaryBitmap = new byte[8];
				System.arraycopy(iso, offset, primaryBitmap, 0, 8);  offset += 8;
				int hiNibble = (primaryBitmap[0] >> 4) & 0x0f; 
				if(hiNibble >= 8){
					
					data += DatatypeConverter.printHexBinary(primaryBitmap);
					byte [] secundaryBitmap = new byte[8];
					System.arraycopy(iso, offset, secundaryBitmap, 0, 8);  offset += 8;
					data += DatatypeConverter.printHexBinary(secundaryBitmap);
				}else
					data += DatatypeConverter.printHexBinary(primaryBitmap);
				byte[] resto = new byte[iso.length - offset];
				System.arraycopy(iso, offset, resto, 0, resto.length);	
				data += new String(resto);
				log = new Logger();
				log.WriteLog(data, TypeLog.isoBin, TypeWriteLog.file);
				
			} catch (Exception e) {
				
				log = new Logger();
				log.WriteLogMonitor("Error modulo GeneralUtils::setterLoggerBinary ", TypeMonitor.error, e);
			}
		}
		
		private static byte hexStringToByte(String data) {
		    return (byte) ((Character.digit(data.charAt(0), 16) << 4)
		                  | Character.digit(data.charAt(1), 16));
		}
		
		public static boolean isEmptyByteArray(byte[] valor){
			
			int counter = (int) IntStream.range(0, valor.length)
					.map(i -> valor[i])
					.filter(i -> i != 0).count();
			
			if(counter > 0)
				return false;
			else
				return true;
		}
		
}
