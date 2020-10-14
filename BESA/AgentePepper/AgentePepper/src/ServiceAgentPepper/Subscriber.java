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
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
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
public class Subscriber implements MessageListener{
		
    private TopicSession session;
    private TopicSubscriber subscriber;
    private javax.jms.Topic topic;
    private TopicConnection connection;
    private static String usuario = "guest";
    private static String contrasena = "guest";
		
    public Subscriber(String nConnection, String nTopic) throws Exception {
           
        Properties env = new Properties();
	env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	env.put(Context.SECURITY_PRINCIPAL, usuario);
	env.put(Context.SECURITY_CREDENTIALS, contrasena);

	InitialContext ic = new InitialContext(env);

	TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ic.lookup("jms/"+nConnection);
	connection = connectionFactory.createTopicConnection(usuario, contrasena);
	
	topic = (javax.jms.Topic) ic.lookup("jms/topic/"+nTopic);
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        subscriber = session.createSubscriber(topic, null, false);
        subscriber.setMessageListener(this);
    }

    public void close() throws Exception  {
            subscriber.close();
            session.close();
    }

    @Override
    public void onMessage(Message message) {
            try {
            SensorData infoRecibida = castMessageToData(message);
            System.out.println("Pepperoni");
            System.out.println("Message Received: "+infoRecibida);
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasSHAAgent);
            EventBESA sensorEvtA= new EventBESA(GetInfoGuard.class.getName(),infoRecibida);
            handler.sendEvent(sensorEvtA);  
            } catch (ExceptionBESA e) {
                    e.printStackTrace();
            }
    }

    private SensorData castMessageToData(Message message) {
        return null;
    }
	
	
}
