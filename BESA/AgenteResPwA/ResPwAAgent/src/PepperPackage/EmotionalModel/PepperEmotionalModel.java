/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import BESA.ExceptionBESA;
import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalModel;
import EmotionalAnalyzerAgent.EmotionalState;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import Tareas.Cuenteria.LedsColor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperEmotionalModel extends EmotionalModel {

    private final double a;
    private LedsColor lc;
    private double qState;

    public PepperEmotionalModel() {
        this.qState = 0;
        this.a=0;
    }

    @Override
    public void updateModel(EmotionalData e) {

    }

    @Override
    public EmotionalState getState() {
        EmotionalState ret = new EmotionalState();
        EmotionPwA emo = null;
//        qState=qState+ (a*());
        ret.setInfluenceFactor(qState);
        ret.setDominantEmotion(emo);
        return ret;
    }
}
