/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalData;
import PepperPackage.EmotionalModel.PepperEmotionalModel;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalEvent;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalModel;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jsleon
 */
public class BEstadoEmocionalRobot implements Believes {

private EmotionalModel emoModel;

public BEstadoEmocionalRobot(){
    emoModel=new PepperEmotionalModel();
}

    @Override
    public boolean update(InfoData si) {
        
        EmotionalData emoDat= (EmotionalData) si;
        EmotionalEvent emoEv= emoDat.getEmoEv();
        emoModel.processEmotionalEvent(emoEv);
        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

    public EmotionalModel getEmoModel() {
        return emoModel;
    }

    public void setEmoModel(EmotionalModel emoModel) {
        this.emoModel = emoModel;
    }

}
