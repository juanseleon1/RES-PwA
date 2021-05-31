/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class PreguntarSentimientos extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();
    private long start;

    public PreguntarSentimientos() {
//        System.out.println("--- Task Preguntar Sentimientos Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Preguntar Sentimientos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;

       

        infoServicio.put("SAY", "¿Como te sientes hoy?");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
         ResPwaUtils.activateTopic(PepperTopicsNames.ALEGRETOPIC, parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Preguntar Sentimientos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        ResPwaUtils.deactivateTopic(PepperTopicsNames.ALEGRETOPIC, believes);
        ResPwaUtils.deactivateTopic(PepperTopicsNames.IRATOPIC, believes);
        ResPwaUtils.deactivateTopic(PepperTopicsNames.SADTOPIC, believes);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Preguntar Sentimientos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        ResPwaUtils.deactivateTopic(PepperTopicsNames.ALEGRETOPIC, believes);
        ResPwaUtils.deactivateTopic(PepperTopicsNames.IRATOPIC, believes);
        ResPwaUtils.deactivateTopic(PepperTopicsNames.SADTOPIC, believes);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

        if (!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoInteraccion().isRecibirRespuestaPwA() && blvs.getbEstadoInteraccion().getRespuestasPorContexto() > 1
                && ((blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.ALEGRETOPIC) || !blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.SADTOPIC) || !blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.IRATOPIC)))) {
            System.out.println("KKKKKKKK: " + blvs.getbEstadoInteraccion().isRecibirRespuestaPwA());
            ResPwaUtils.deactivateTopic(PepperTopicsNames.ALEGRETOPIC, believes);
            blvs.getbEstadoInteraccion().setRecibirRespuestaPwA(false);
            blvs.getbEstadoInteraccion().setRespuestasPorContexto(0);
            return true;
        }
        return false;
    }

}
