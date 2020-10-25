/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Conversacion;

import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class PreguntarEstAnimo extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public PreguntarEstAnimo() {
        System.out.println("--- Task Preguntar Estado Animo Iniciada ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        //buscar texto "¿como estas pepito?"
        infoServicio.clear();
        infoServicio.put("SAY", "Texto");
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Preguntar Estado Animo ---");
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
