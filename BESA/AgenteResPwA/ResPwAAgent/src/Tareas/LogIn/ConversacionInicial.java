/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class ConversacionInicial extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();
    private LocalTime start;

    public ConversacionInicial() {
//        System.out.println("--- Task Preguntar Estado Animo Iniciada ---");
        start = LocalTime.now();
    }

    @Override
    public void executeTask(Believes parameters) {
        LocalTime now = LocalTime.now();
        if (Duration.between(start, now).getSeconds()>30) {
            System.out.println("--- Execute Task Preguntar Estado Animo ---");
            RobotAgentBelieves rab = (RobotAgentBelieves) parameters;
            activateTopic(PepperTopicsNames.SALUDARTOPIC, parameters);
            if(!infoServicio.containsKey("SAY")){
                infoServicio.put("SAY", "Buen dia, como se encuentra");
            }
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            requestService(srb, rab);
            start= LocalTime.now();
        }

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Preguntar Estado Animo ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        deactivateTopic(PepperTopicsNames.SALUDARTOPIC, believes);
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb, blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Preguntar Estado Animo ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        deactivateTopic(PepperTopicsNames.SALUDARTOPIC, believes);
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb, blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
        super.checkFinish(believes);

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoInteraccion().isRecibirRespuestaPwA()) {
            deactivateTopic(PepperTopicsNames.SALUDARTOPIC, believes);
            blvs.getbEstadoInteraccion().setLogged(true);
            blvs.getbEstadoInteraccion().setRecibirRespuestaPwA(false);
            return true;
        }
        return false;
    }

}
