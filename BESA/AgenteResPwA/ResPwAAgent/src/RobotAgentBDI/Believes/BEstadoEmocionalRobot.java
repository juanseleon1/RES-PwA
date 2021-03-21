/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalModel;
import EmotionalAnalyzerAgent.EmotionalState;
import ServiceAgentResPwA.ServiceDataRequest;
import java.time.LocalTime;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jsleon
 */
public class BEstadoEmocionalRobot implements Believes {

    private EmotionalModel em;
    private LocalTime lastUpdate;
    private ModulationStrategy ems;
    BEstadoEmocionalRobot(EmotionalModel em) {
        this.em = em;

    }

    @Override
    public boolean update(InfoData si) {

        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

    public EmotionalModel getEm() {
        return em;
    }

    public void setEm(EmotionalModel em) {
        this.em = em;
    }

    public LocalTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ModulationStrategy getEms() {
        return ems;
    }

    public void setEms(ModulationStrategy ems) {
        this.ems = ems;
    }
    
    public Map<String,Object>  modulateAction(ServiceDataRequest sdr){
        EmotionalState emoData= em.getState();
        return ems.modulateAction(sdr, emoData);
    }

}
