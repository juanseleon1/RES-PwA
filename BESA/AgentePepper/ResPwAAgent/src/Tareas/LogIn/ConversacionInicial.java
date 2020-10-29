/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class ConversacionInicial extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public ConversacionInicial() {
        System.out.println("--- Task Preguntar Estado Animo Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Preguntar Estado Animo ---");
        ServiceDataRequest srb = null;
        infoServicio.put("GETEMOTIONSTATE", null);
        srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
        infoServicio.clear();
        
        //buscar texto "¿como estas pepito?"
        infoServicio.put("SAY", "PreguntaSentimientos");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Preguntar Estado Animo ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Preguntar Estado Animo ---");
    }
    
}

