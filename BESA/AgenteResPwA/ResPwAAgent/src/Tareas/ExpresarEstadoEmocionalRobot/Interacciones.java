/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ExpresarEstadoEmocionalRobot;

import EmotionalAnalyzerAgent.Utils.EmotionPwA;
import EmotionalAnalyzerAgent.Agent.EmotionalAnalyzerState;
import PepperPackage.EmotionalModel.ResPwaEmotionalModel;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import PepperPackage.EmotionalModel.PepperEmotionRanges;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
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
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        double state= 0;
        
        if(state >= PepperEmotionRanges.SAD.getMin() && state < PepperEmotionRanges.SAD.getMax())
        {
            infoServicio.put("SAY", "juuum");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.VHAPPY.getMin() && state < PepperEmotionRanges.VHAPPY.getMax())
        {
            infoServicio.put("EMOTION", "VHAPPY");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.PLAYANIMATION, infoServicio);

            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.VSAD.getMin() && state < PepperEmotionRanges.VSAD.getMax())
        {
            infoServicio.put("EMOTION", "VSAD");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.PLAYANIMATION, infoServicio);

            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(state >= PepperEmotionRanges.HAPPY.getMin() && state < PepperEmotionRanges.HAPPY.getMax())
        {
            infoServicio.put("SAY", "jijijiji");
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
