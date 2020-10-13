/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import SensorHandlerAgent.SensorData;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

/**
 *
 * @author mafegarces
 */
public class Publisher {
    
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
		
    public void sendMessage(SensorData data) {
	try {
            Message info = castDataToMessage(data);
            publisher.publish(info);
            System.out.println("Message Send" );
            Thread.sleep(1000);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    

    public Message castDataToMessage(SensorData data) {
        return null;
    }
}
