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
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/**
 *
 * @author juans
 */
public class Subscriber implements MessageListener{
		
    private TopicSession session;
    private TopicSubscriber subscriber;
		
    public Subscriber(TopicConnection connection, javax.jms.Topic topic) throws Exception {
            this.session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            this.subscriber = this.session.createSubscriber(topic, null, false);
            this.subscriber.setMessageListener(this);
    }

    public void close() throws Exception  {
            subscriber.close();
            session.close();
    }

    @Override
    public void onMessage(Message message) {
            try {
            SensorData infoRecibida = castMessageToData(message);
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
