/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage;

import BESA.ExceptionBESA;
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalModel;
import SensorHandlerAgent.SensorData;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperEModel extends EmotionalModel{
    private float state;
    private float refreshRate;
    private float normalState;

    public PepperEModel(float state, float refreshRate, float normalState) {
        this.state = state;
        this.refreshRate = refreshRate;
        this.normalState = normalState;
    }
    
    @Override
    public void updateModel() {
        float act=refreshRate, dif=normalState-state,fact=1;
        if(dif<0)
            fact*=-1;
        if(dif<act)
            act=dif;
        act*=fact;
        
        if(dif!=0)
        {
            state+=act;
        }
    }

    @Override
    public void updtModelFromEvt(SensorData sd) {
        try {
            EmotionalData e= new EmotionalData();
            Map<String,Object> map= e.getInfo();
            calcNewEmotionalParams(map,sd);
            e.setInfo(map);
            sendAct(e);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PepperEModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    private void calcNewEmotionalParams(Map<String, Object> map, SensorData sd) {
        
    }
    
}
