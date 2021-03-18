/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalModel;
import java.time.LocalTime;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jsleon
 */
public class BEstadoEmocionalRobot implements Believes {

    private EmotionalModel em;
    private LocalTime lastUpdate;
    private EmotionalModulationStrategy ems;
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

    public EmotionalModulationStrategy getEms() {
        return ems;
    }

    public void setEms(EmotionalModulationStrategy ems) {
        this.ems = ems;
    }
    
    

}
