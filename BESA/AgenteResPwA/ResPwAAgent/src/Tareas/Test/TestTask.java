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
        infoServicio.put("SAY", "Tengo muchas ganas de bailar");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Buenas noches profe enrrique");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("TAGSDANCE", "HAPPINESS");
        infoServicio.put("FACTOR", 1);
        srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Vamos a bailar con los viejitos");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Mafe, quieres bailar conmigo?");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);

        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Me voy a mareaaaaaaaar");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Yo te lo dije no me iba a enamorar, te lo advertí a mi manera");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Y grabe la penca de un magu ei tu nombre, juntito al mido entrelazadooooo");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("SAY", "Me voy a marear");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
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
