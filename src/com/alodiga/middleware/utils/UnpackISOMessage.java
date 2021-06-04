package com.alodiga.middleware.utils;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;

import java.io.InputStream;

public class UnpackISOMessage {
    public static void main(String[] args) {
//    	String message = "020002220100020000000319174039639530031900196";
    	String message ="02007A3A4601AFE09208195412781234567890389002000000000100000000000100000042715163963953015163904270427020000167611000000012341090001234563700000000000000012121212000000121212121234567891231234569614512345678000000000034567av rivadavia5643 caracas VEN            928148943162709F260831221BC08E163B789F2701809F1008010103A0280600009F3704291315229F36020550950540000080009A030903139C01009F02060009090909005F2A020392820258009F1A0203569F03060100010001009F34035E03009F3303E0E0C89F3501229F1E0830333531313634359F5301528407A00000000410109F090200029F4102000112510006   716";
    	UnpackISOMessage packageIso = new UnpackISOMessage();
    	////////////////////////////////////////////////////////////
    	/////////////////////Descompone El ISO - 8583///////////////
    	////////////////////////////////////////////////////////////
    	packageIso.descomposeISOMessage(message);
    	////////////////////////////////////////////////////////////
    	/////////////////////Armado del ISO-8583///////////////////////
    	////////////////////////////////////////////////////////////
    	packageIso.constructISOMessage();
}
    
    private void descomposeISOMessage (String msg) {
        UnpackISOMessage iso = new UnpackISOMessage();
        try {
            ISOMsg isoMsg = iso.parseISOMessage(msg);
            
            
            iso.printISOMessage(isoMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    private void constructISOMessage() {
    	 UnpackISOMessage iso = new UnpackISOMessage();
    	 try {
			iso.getIsoObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private ISOMsg parseISOMessage(String msg) throws Exception {
    	
       
        System.out.println(msg);
        try {
            // Load package from resources directory.
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            System.out.println("paso");
            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.unpack(msg.getBytes());
           
            return isoMsg;
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }
    
    
    private void getIsoObject() throws Exception {
    	InputStream is = getClass().getResourceAsStream("/fields.xml");
    	ISOPackager packager = new GenericPackager(is);
    	ISOMsg m = new ISOMsg();
    	m.setMTI("0200");
    	m.set(0, "0200");
//    	m.set(1, "0111101000111010010001100000000110101111111000001001001000001000");
    	m.set(2, "5412781234567890389");//pan
    	m.set(3, "002000");//Código de procesamiento
    	m.set(4, "000000100000");//Monto de la transacción
    	m.set(5, "000000100000");//Monto de conciliación
    	m.set(7, "0427151639");//Fecha y hora de la	transmisión GMT
    	m.set(11, "639530");//Número de trace
    	m.set(12, "151639");//Hora local de la transacción
    	m.set(13, "0427"); //Fecha local de la transacción
    	m.set(15, "0427"); //Fecha de la conciliación
    	m.set(18, "0200"); //Categoría de comercio (MCC)
    	m.set(22, "001");//Modo de entrada de los datos de la tarjeta en el terminal
    	m.set(23, "676");//Número de secuencia de la tarjeta (CVV)Preguntar
    	m.set(32, "00000001234");//Código de identificación de la institución adquirente
    	m.set(33, "9000123456");//Código de identificación del procesador
    	m.set(35, "0000000000000001212121200000012121212");//Datos del track 2
    	m.set(37, "123456789123");//Número de referencia de la transacción
    	m.set(38, "123456");//Identificación de la respuesta de autorización
    	m.set(39, "96");//Código de respuesta
    	m.set(40, "145");
    	m.set(41, "12345678");//Identificación del terminal adquirente de la transacción
    	m.set(42, "000000000034567");//Código identificador del comercio afiliado
    	m.set(43, "av rivadavia5643 caracas VEN");//Nombre y ubicación del adquirente de la transacción
    	//m.set(48, "96");//Datos adicionales
    	m.set(49, "928");//Código de moneda de la transacción
    	m.set(52, "14894316");//Datos del número de identificación personal (PIN)
    	//m.set(54, "");//Montos adicionales
    	m.set(55, "9F260831221BC08E163B789F2701809F1008010103A0280600009F3704291315229F36020550950540000080009A030903139C01009F02060009090909005F2A020392820258009F1A0203569F03060100010001009F34035E03009F3303E0E0C89F3501229F1E0830333531313634359F5301528407A00000000410109F090200029F41020001");//Datos de la tarjeta chip
//    	m.set(61, "");//Datos del punto de venta (POS)
    	m.set("61.1", "1");       //Unattended terminal
        m.set("61.3", "2");       //Off premises of card acceptor facility
        m.set("61.4", "5");       //Electronic order (home PC, Internet, mobile phone, PDA)
        m.set("61.5", "1");       //Card not present
        m.set("61.6", "0");       //Terminal/operator has no card capture capability
        m.set("61.7", "0");       //Normal request (original presentment)
        m.set("61.8", "0");       //No security concern
        m.set("61.10", "6");      //Authorized Level 6 CAT: Electronic commerce
        m.set("61.13", "716");    //country code Zimbabwe
    	m.setPackager(packager);
    	byte b[] = m.pack();
    	String s = new String(b);
    	System.out.println(s);
    }

    private void printISOMessage(ISOMsg isoMsg) {
        try {
            System.out.println("MTI = " + isoMsg.getMTI());
            System.out.println("61.1 : " + isoMsg.getString("61.1"));
            System.out.println("61.3 : " + isoMsg.getString("61.3"));
            System.out.println("61.4 : " + isoMsg.getString("61.4"));
            System.out.println("61.5 : " + isoMsg.getString("61.5"));
            System.out.println("61.6 : " + isoMsg.getString("61.6"));
            System.out.println("61.7 : " + isoMsg.getString("61.7"));
            System.out.println("61.8 : " + isoMsg.getString("61.8"));
            System.out.println("61.10 : " + isoMsg.getString("61.10"));
            System.out.println("61.13 : " + isoMsg.getString("61.13"));
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.println("Field ("+ i +")"+ isoMsg.getString(i));
                } 
            }
            System.out.println(isoMsg.toString());
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
