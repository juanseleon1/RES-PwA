package ServiceAgentPepper;


import java.util.Hashtable;
import SensorHandlerAgent.SensorData;
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
    private TopicSession session;
    private TopicPublisher publisher;
    
    public Topic(String nConnection, String nTopic) throws NamingException, JMSException {
        
        Properties env = new Properties();
	//env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
	//env.put(Context.PROVIDER_URL, "localhost:3700/");
//	env.put(Context.SECURITY_PRINCIPAL, usuario);
//	env.put(Context.SECURITY_CREDENTIALS, contrasena);

	InitialContext ic = new InitialContext();//env);
        System.out.println("AAAAAAAAAAAAAAAAAA"+ic.getEnvironment().toString());
	TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ic.lookup("jms/"+nConnection);
	connection = connectionFactory.createTopicConnection(usuario, contrasena);
		
	topic = (javax.jms.Topic) ic.lookup("jms/"+nTopic);
     
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	publisher = session.createPublisher(topic);
        
    }
		
    public void close() throws Exception  {
	publisher.close();
	session.close();
    }
		
    public void sendMessage(SensorData data) {
	try {
            Message info = castDataToMessage(data);
            publisher.publish(info);
            System.out.println("Message Send");
            Thread.sleep(1000);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }

    public Message castDataToMessage(SensorData data) {
        return null;
    }
}

