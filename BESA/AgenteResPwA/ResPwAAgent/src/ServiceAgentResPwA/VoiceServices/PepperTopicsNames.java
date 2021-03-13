/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.VoiceServices;

/**
 *
 * @author ASUS
 */
public enum PepperTopicsNames {
    ALEGRETOPIC("alegreTopic"), BASICTOPIC("basicTopic"), AYUDATOPIC("ayudaTopic"), IRATOPIC("iraTopic");
    
    private String topicName;
    private PepperTopicsNames(String topic)
    {
        topicName=topic;
    }
    
    public String getTopic(){
        return topicName;
    }
}
