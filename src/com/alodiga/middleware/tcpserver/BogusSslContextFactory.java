package com.alodiga.middleware.tcpserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Security;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import com.alodiga.temporal.cache.MemoryGlobal;


public class BogusSslContextFactory {
	
	private static final String PROTOCOL = "TLS";
	private static final String KEY_MANAGER_FACTORY_ALGORITHM;
	
	static {
		String algorithm = Security
		.getProperty("ssl.KeyManagerFactory.algorithm");
		if (algorithm == null) {
			algorithm = KeyManagerFactory.getDefaultAlgorithm();
		}
			KEY_MANAGER_FACTORY_ALGORITHM = algorithm;
		}
	private static String path = MemoryGlobal.currentPath;
	
	private static final String BOGUS_KEYSTORE = path + MemoryGlobal.nameSSLTCPFile;//"D:\\FinanCoop\\valle.pkcs12";
	private static final char[] BOGUS_PW =  MemoryGlobal.passSSLTCPFile.toCharArray();//{ 'e', 'x', 't', 'r', 'e', 'm', 'e','L','V','a','l','l','e','2','0','1','7' };
	private static SSLContext serverInstance = null;
	private static SSLContext clientInstance = null;
	
	
	public BogusSslContextFactory(){
		super();
	}
	
	public static SSLContext getInstance(boolean server)
            throws GeneralSecurityException {
        SSLContext retInstance = null;
        if (server) {
            synchronized(BogusSslContextFactory.class) {
                if (serverInstance == null) {
                    try {
                        serverInstance = createBougusServerSslContext();
                    } catch (Exception ioe) {
                    	System.out.println("Error2222 " + ioe.getMessage());
                        throw new GeneralSecurityException(
                                "Can't create Server SSLContext:" + ioe);
                    }
                }
            }
            retInstance = serverInstance;
        } else {
            synchronized (BogusSslContextFactory.class) {
                if (clientInstance == null) {
                    clientInstance = createBougusClientSslContext();
                }
            }
            retInstance = clientInstance;
        }
        return retInstance;
    }
	
	private static  SSLContext createBougusServerSslContext()
	        throws GeneralSecurityException, IOException {
	    
	    KeyStore ks = KeyStore.getInstance("JKS");
	    InputStream in = null;
	    try {
	        
	    	in = new FileInputStream(BOGUS_KEYSTORE);
	        ks.load(in, BOGUS_PW);
	    } finally {
	        if (in != null) {
	            try {
	                in.close();
	            } catch (IOException ignored) {
	            	
	            	System.out.println("Error3 " + ignored.getMessage());
	            }
	        }
	    }

	    // Set up key manager factory to use our key store
	    KeyManagerFactory kmf = KeyManagerFactory
	            .getInstance(KEY_MANAGER_FACTORY_ALGORITHM);
	    kmf.init(ks, BOGUS_PW);

	    // Initialize the SSLContext to work with our key managers.
	    SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
	    sslContext.init(kmf.getKeyManagers(),
	            BogusTrustManagerFactory.X509_MANAGERS, null);

	    return sslContext;
	}

	private static SSLContext createBougusClientSslContext()
	        throws GeneralSecurityException {
	    SSLContext context = SSLContext.getInstance(PROTOCOL);
	    context.init(null, BogusTrustManagerFactory.X509_MANAGERS, null);
	    return context;
	}
	
}
