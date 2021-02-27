/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.InteraccionSocial;

import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalAnalyzerState;
import PepperPackage.EmotionalModel.PepperEModel;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class Interacciones extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public Interacciones() {
        //System.out.println("--- Task Interacciones Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Interacciones ---");
        
        //mensajitos de acuerdo a sus emociones
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        double state = ((PepperEModel)blvs.getEm()).getState();
        
        if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.ANGER))
        {
            if(state > 0)
            {
                infoServicio.put("SAY", "");
                ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                requestService(srb,blvs);
            }
        }
        
        if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.HAPPINESS))
        {
            if(state > 0)
            {
                infoServicio.put("SAY", "YUPIII");
                ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                requestService(srb,blvs);
            }
        }
        
        if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.SADNESS))
        {
            if(state > 0)
            {
                infoServicio.put("SAY", "");
                ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                requestService(srb,blvs);
            }
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Interacciones ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Interacciones ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        System.out.println("--- Check Finish Task Interacciones ---");
        return false;
    }
    
}
