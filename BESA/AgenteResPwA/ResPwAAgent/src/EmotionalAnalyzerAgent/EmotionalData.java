/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import SensorHandlerAgent.SensorData;
import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author juans
 */
public class EmotionalData extends InfoData {

    private Map<String, Object> info;
    private WHO who;
    private EmotionalEventType emoType;
    private double objImpact;

    public static EmotionalData fromSensorData(SensorData infoRecibida) {
        EmotionalData em = new EmotionalData();
        em.who = WHO.PWA;
        em.info = infoRecibida.getDataP();
        em.emoType = em.getEmotionalEventMapping();
        return em;
    }

    public static EmotionalData getPeriodicData() {
        EmotionalData em = new EmotionalData();
        em.who = WHO.ROBOT;
        em.info = new HashMap<>();
        em.emoType = EmotionalEventType.INTERNALUPDT;
        return em;

    }

    public EmotionalData() {
        super("emodata");
        info = new HashMap<>();
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
        EmotionalEventType resp = EmotionalEventType.getFromId(this.info.keySet().iterator().next());
        return resp;
    }

    public double getObjImpact() {
        return objImpact;
    }

    public void setObjImpact(double objImpact) {
        this.objImpact = objImpact;
    }

    public int getEventValence() {
        int valence = 1;
        boolean c1 = emoType.getValue()<0 && who.getValue() < 0 && objImpact>0;
        boolean c2 = emoType.getValue()>0 && who.getValue() < 0 && objImpact<0;
        boolean c3 = emoType.getValue()>0 && who.getValue() < 0 && objImpact>0;
        boolean c4 = emoType.getValue()<0 && who.getValue() > 0 && objImpact>0;
        boolean c5 = emoType.getValue()>0 && who.getValue() > 0 && objImpact<0;

        if (c1 || c2 || c3 || c4 || c5) {
            valence = -1;
        }
        return valence;

    }
}
