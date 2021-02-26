/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Test;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class TestTask extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public TestTask() {
//        System.out.println("--- Task Revisar Perfil Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Revisar Perfil ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("name", "basicoConv");

        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "A mafe le gustan los toxicos");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Quiero perreo intenso");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Mami chula");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Oye chuzca");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Tu papa es carpintero?");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Y esas tablas?");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("TAGSDANCE", "HAPPINESS");
        infoServicio.put("FACTOR", 0);
        srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
        requestService(srb, blvs);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Revisar Perfil ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Revisar Perfil ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        return false;
    }

}
