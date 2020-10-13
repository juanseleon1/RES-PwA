package ServiceAgentPepper;


import java.util.Properties;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mafegarces
 */

public class Topic {
    
    private static String usuario = "guest";
    private static String contrasena = "guest";
    private TopicConnection connection;
    private javax.jms.Topic topic;
    
    public Topic(String nConnection, String nTopic) throws NamingException, JMSException {
        
        Properties env = new Properties();
	env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	env.put(Context.SECURITY_PRINCIPAL, usuario);
	env.put(Context.SECURITY_CREDENTIALS, contrasena);

	InitialContext ic = new InitialContext(env);

	TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ic.lookup("jms/"+nConnection);
	connection = connectionFactory.createTopicConnection(usuario, contrasena);
		
	topic = (javax.jms.Topic) ic.lookup("jms/topic/"+nTopic);
    }
    
    //enviar mensaje
    pasarMensaje()
    databesa a messageJMS
    
    public void mandarMensajeTopico() throws JMSException {
        TopicSession session;
	TopicPublisher publisher;
        
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	publisher = session.createPublisher(topic);
    }
            
    public void cerrarConexiones() {
        
    }
	
	private static class Publisher implements Runnable {
		
		private TopicSession session;
		private TopicPublisher publisher;
		
		public Publisher(TopicConnection connection, javax.jms.Topic topic) throws Exception {
			this.session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			this.publisher = this.session.createPublisher(topic);
		}
		
		public void close() throws Exception  {
			publisher.close();
			session.close();
		}
		
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; ++i) {
					Message mensaje = session.createTextMessage(String.format("¡Hola mundo! (%d)", i));
					publisher.publish(mensaje);
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
