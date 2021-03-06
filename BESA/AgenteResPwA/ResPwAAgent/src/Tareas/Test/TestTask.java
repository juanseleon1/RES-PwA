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
//        infoServicio.put("name", "alegreTopic"); //sadTopic
//        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);

//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "alegreTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);

//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "emoTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
//
//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "alegreTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
//
//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "sadTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
//
//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "iraTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
//
//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "normTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
//        infoServicio = new HashMap<>();
//        infoServicio.put("name", "musicTopic");
//        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.LOADCONVTOPIC, infoServicio);
//        requestService(srb, blvs);
        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "OYE CHUZCA, CONOCES A BRAYAN?");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
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
