/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author juans
 */
public class Subscriber{
		
    private TopicSession session;
    private TopicSubscriber subscriber;
    private javax.jms.Topic topic;
    private TopicConnection connection;
    private static String usuario = "guest";
    private static String contrasena = "guest";
		
    public Subscriber(String nConnection, String nTopic) throws Exception {
           
        Properties env = new Properties();
	//env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
	//env.put(Context.PROVIDER_URL, "localhost:3700/");
//	env.put(Context.SECURITY_PRINCIPAL, usuario);
//	env.put(Context.SECURITY_CREDENTIALS, contrasena);

	InitialContext ic = new InitialContext();//env);
	TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ic.lookup("jms/"+nConnection);
	connection = connectionFactory.createTopicConnection(usuario, contrasena);
	topic = (javax.jms.Topic) ic.lookup("jms/"+nTopic);
	connection = connectionFactory.createTopicConnection(usuario, contrasena);
        connection.start();
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(new ExListener());
        System.out.println("Sub. "+nTopic+ " Created");
    }

    public void close() throws Exception  {
        connection.close();
        subscriber.close();
        session.close();
    }

    private static SensorData castMessageToData(Message message) {
        return new SensorData(usuario);
    }
	
    
    public static class ExListener implements MessageListener
    {
        @Override
        public void onMessage(Message message)
        {
            try {
                SensorData infoRecibida = castMessageToData(message);
                System.out.println("Message Received: from topic "+(TextMessage)message);
//            System.out.println("Message Received: "+infoRecibida);
                AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasSHAAgent);
                EventBESA sensorEvtA= new EventBESA(GetInfoGuard.class.getName(),infoRecibida);
                handler.sendEvent(sensorEvtA);  
            } catch (ExceptionBESA ex) {
                Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
}
