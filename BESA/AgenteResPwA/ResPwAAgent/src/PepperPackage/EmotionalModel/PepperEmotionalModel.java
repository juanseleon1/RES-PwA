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
import Tareas.Cuenteria.LedsColor;

/**
 *
 * @author juans
 */
public class PepperEmotionalModel extends EmotionalModel {

    private final double m;
    private double currState;
    private final double predetValue;
    private LedsColor lc;
    private long lastUpdt;
    private double emoLifeTime;
    private static final double CHANGE_FACT = 0.3;

    public PepperEmotionalModel(double predetValue) {
        this.predetValue = predetValue;
        this.m = 1;
    }

    @Override
    public void updateModel(EmotionalData e) {
        updateModelValue();
        lastUpdt = System.currentTimeMillis();
    }

    @Override
    public EmotionalState getState() {
        double currTime = System.currentTimeMillis()-lastUpdt;
        EmotionalState ret = new EmotionalState();
        EmotionPwA emo = null;
        updateModelValue();
        lastUpdt = System.currentTimeMillis();
        ret.setDominantEmotion(emo);
        return ret;
    }

    public LedsColor getLc() {
        return lc;
    }

    public void setLc(LedsColor lc) {
        this.lc = lc;
    }

    private void updateModelValue() {
        currState = (m) + (predetValue);
    }
    
    
    private double calculateDecayFactor(double currTime){
       double decay=0;
       //TODO
       return 0;
    }
}
