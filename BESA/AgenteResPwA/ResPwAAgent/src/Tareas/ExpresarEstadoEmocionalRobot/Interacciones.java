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
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import Tareas.Cuenteria.LedsColor;
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
        EmotionalState emoState = blvs.getbEstadoEmocionalRobot().getEm().getState();
        double state= emoState.getInfluenceFactor();
        //toca modificar la emocion y color, depende de la encuesta
        if(state >= LedsColor.RED.getMin() && state < LedsColor.RED.getMax())
        {
            infoServicio.put("SAY", "jeit u bish");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb,blvs);
        }
        
        if(state >= LedsColor.YELLOW.getMin() && state < LedsColor.YELLOW.getMax())
        {
            infoServicio.put("SAY", "yupiii");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb,blvs);
        }
        
        if(state >= LedsColor.BLUE.getMin() && state < LedsColor.BLUE.getMax())
        {
            infoServicio.put("SAY", "juum");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb,blvs);
        }
        
        if(state >= LedsColor.PURPLE.getMin() && state < LedsColor.PURPLE.getMax())
        {
            infoServicio.put("SAY", "iu");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb,blvs);
        }
        
        if(state >= LedsColor.GREEN.getMin() && state < LedsColor.GREEN.getMax())
        {
            infoServicio.put("SAY", "güii");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb,blvs);
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
