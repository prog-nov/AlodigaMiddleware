package com.alodiga.middleware.queueadmin;

import java.io.Serializable;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import com.alodiga.middleware.utils.GeneralUtils;



public class AdminQueueMessage extends Thread {
	
	private int tipo;
	private String nameQueue;
	private Session session;
	private MessageProducer producer;
	private boolean error;
	private String detalleError;
	private Object object;
	private int prioridad;
	private long delay;
	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;	
	
		
	public AdminQueueMessage(String _nameQueue, Session _session, MessageProducer _producer,
			                 Object obj, int prioridad, int tipo, long delay){
		
		this.object = obj;
		this.prioridad = prioridad;
		this.nameQueue = _nameQueue;
		this.session = _session;
		this.producer = _producer;
		error = true;
		this.tipo = tipo;
		this.delay = delay * 1000;
		this.start();
	}

	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getDetalleError() {
		return detalleError;
	}
	public void setDetalleError(String detalleError) {
		this.detalleError = detalleError;
	}
	
	public void SendMessageQueue(){
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		connectionFactory.setTrustAllPackages(true);
		Connection connection = null;
		
		try {
			
			connection = connectionFactory.createConnection();	
			System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","domain");
			this.session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(this.nameQueue);	
			this.producer = session.createProducer(destination);
			if(this.tipo == 1){
				
				ObjectMessage message = session.createObjectMessage();	
				message.setObject((Serializable) this.object);
				if(this.delay > 0)
					message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, this.delay);
				producer.setPriority(this.prioridad);
				producer.send(message);
				
			}else {
				
				TextMessage message = session.createTextMessage();	
				message.setText((String) this.object);
				if(this.delay > 0)
					message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, this.delay);
				producer.setPriority(this.prioridad);
				producer.send(message);
			}	
			
		}catch (JMSException e) {
			
			//poner log
			e.printStackTrace();
			this.error = false;
			this.detalleError = GeneralUtils.ExceptionToString("ERROR [JMS] AL ENVIAR A LA COLA: ", e);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			this.error = false;
			this.detalleError = GeneralUtils.ExceptionToString("ERROR [GEN] AL ENVIAR A LA COLA: ", e);
			
		}finally {
			
			try {
					connection.close();
					
			} catch (javax.jms.JMSException e) {
				this.error = false;
				this.detalleError = GeneralUtils.ExceptionToString("ERROR AL CERRAR COLA: ", e);
			}
		}
		
	}

	@Override
	public void run() {
		SendMessageQueue();
	}
	
		
}
