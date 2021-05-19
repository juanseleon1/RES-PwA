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
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class ConversacionInicial extends ResPwaTask {

    private HashMap<String, Object> infoServicio;
    private long start;

    public ConversacionInicial() {
//        System.out.println("--- Task Preguntar Estado Animo Iniciada ---");
        infoServicio = new HashMap<>();
        start = System.currentTimeMillis();
    }

    @Override
    public void executeTask(Believes parameters) {

        long now = System.currentTimeMillis();
        TimeUnit unit = TimeUnit.SECONDS;
        if (unit.convert(now - start, TimeUnit.SECONDS) > 20) {
            System.out.println("--- Execute Task Preguntar Estado Animo ---");
            RobotAgentBelieves rab = (RobotAgentBelieves) parameters;
            if (rab.getbEstadoInteraccion().getRespuestasPorContexto() == 0) {
                if (!infoServicio.containsKey("SAY")) {
                    infoServicio.put("SAY", "Mucho gusto, soy Pepper. Que bueno verte");
                }
                ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                requestService(srb, rab);
                start = System.currentTimeMillis();
            }

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
        System.out.println("Esta hablando: " + blvs.getbEstadoInteraccion().isEstaHablando() + " " + "Recibir respuesta: " + blvs.getbEstadoInteraccion().isRecibirRespuestaPwA());
        if (!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoInteraccion().getRespuestasPorContexto() > 1) {
            System.out.println("--- Check Finish Conversacion Inicial ---");
            blvs.getbEstadoInteraccion().setLogged(true);
            blvs.getbEstadoInteraccion().setRecibirRespuestaPwA(false);
            if (blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.SALUDARTOPIC)){
                System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                deactivateTopic(PepperTopicsNames.SALUDARTOPIC, believes);
            }
            return true;
        }
        return false;
    }

}
