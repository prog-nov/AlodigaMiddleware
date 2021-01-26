package com.alodiga.middleware.queueadmin;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.alodiga.middleware.cscoreswitch.CallersAsync;
import com.alodiga.middleware.logger.Logger;
import com.alodiga.middleware.logger.LoggerConfig.TypeMonitor;

public class ReceiveAsyncMessage extends Thread implements MessageListener{

	
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	
	private String nameQueue;
	private Session session;
	private Logger log;
	
	public ReceiveAsyncMessage(String queueName, Session session){
		System.out.println("recibe ReceiveAsyncMessage static-access en cola");	
		log = new Logger();
		this.nameQueue = queueName;
		this.session = session;
		//this.setDaemon(true);
		//this.start();
	}
	private void ReadMessage(){
		    System.out.println("devuelve desde el active MQ");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connectionFactory.setMaxThreadPoolSize(1500);
			connectionFactory.setTrustAllPackages(true);
			System.out.println("ReadMessage");
					
			try {	
				Connection connection = connectionFactory.createConnection();	
				connection.start();		
				this.session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);				
				Destination destination = this.session.createQueue(this.nameQueue);	
				MessageConsumer consumer = this.session.createConsumer(destination);
				consumer.setMessageListener(this);
				log.WriteLogMonitor("QueueReceiveAsync [" + this.nameQueue  +"] loaded successfull!!", TypeMonitor.monitor, null);
			
			}catch (JMSException e) {
				
				e.printStackTrace();
				
			} catch (Exception e) {	
				
				e.printStackTrace();
			}
     }

	@Override
	public void onMessage(Message message) {
		System.out.println("onMessage");	
		try {
			
			CallersAsync call = new CallersAsync(message);
			call.start();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		ReadMessage();
	}
	
}
