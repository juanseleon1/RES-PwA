/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import SensorHandlerAgent.SensorData;
import java.util.HashMap;
import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author juans
 */
public class EmotionalData extends InfoData{

    private Map<String, Object> info;
    private WHO who;
    private EmotionalEventType emoType;
    
    
    public static EmotionalData fromSensorData(SensorData infoRecibida) {
        EmotionalData em = new EmotionalData();
        em.who=WHO.PWA;
        em.info= infoRecibida.getDataP();
        em.emoType=em.getEmotionalEventMapping();
        return em;
    }
    
    
    public EmotionalData(){
        super("emodata");
        info=new HashMap<>();
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public WHO getWho() {
        return who;
    }

    public void setWho(WHO who) {
        this.who = who;
    }

    public EmotionalEventType getEmoType() {
        return emoType;
    }

    public void setEmoType(EmotionalEventType emoType) {
        this.emoType = emoType;
    }

    private EmotionalEventType getEmotionalEventMapping() {
        
        if(this.info.containsKey("")){
            
        }
        
        
        return null;        
    }

    
}
