/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ExpresarEstadoEmocionalRobot;

import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalAnalyzerState;
import EmotionalAnalyzerAgent.EmotionalState;
import PepperPackage.EmotionalModel.PepperEmotionalModel;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import PepperPackage.EmotionalModel.PepperEmotionRanges;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class Interacciones extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public Interacciones() {
        //System.out.println("--- Task Interacciones Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Interacciones ---");
        
        //mensajitos de acuerdo a sus emociones
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
//        EmotionalState emoState = blvs.getbEstadoEmocionalRobot().getEm().getState();
        double state= 0;//emoState.getInfluenceFactor();
        //toca modificar la emocion y color, depende de la encuesta
        if(state >= PepperEmotionRanges.SAD.getMin() && state < PepperEmotionRanges.SAD.getMax())
        {
            infoServicio.put("SAY", "jeit u bish");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.VHAPPY.getMin() && state < PepperEmotionRanges.VHAPPY.getMax())
        {
            infoServicio.put("SAY", "yupiii");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.VSAD.getMin() && state < PepperEmotionRanges.VSAD.getMax())
        {
            infoServicio.put("SAY", "juum");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.NORMAL.getMin() && state < PepperEmotionRanges.NORMAL.getMax())
        {
            infoServicio.put("SAY", "iu");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.HAPPY.getMin() && state < PepperEmotionRanges.HAPPY.getMax())
        {
            infoServicio.put("SAY", "güii");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
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
