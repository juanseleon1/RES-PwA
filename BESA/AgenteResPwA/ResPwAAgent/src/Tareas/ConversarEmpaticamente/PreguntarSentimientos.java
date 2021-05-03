/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class PreguntarSentimientos extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public PreguntarSentimientos() {
//        System.out.println("--- Task Preguntar Sentimientos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Preguntar Sentimientos ---");
        
        activateTopic(PepperTopicsNames.ALEGRETOPIC, parameters);
        activateTopic(PepperTopicsNames.IRATOPIC, parameters);
        activateTopic(PepperTopicsNames.SADTOPIC, parameters);
        //buscar texto
        infoServicio.put("SAY", "PreguntaSentimientos");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, (RobotAgentBelieves) parameters);
    
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Preguntar Sentimientos ---");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Preguntar Sentimientos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //debe revisar que el estado animo haya cambiado y cierto tiempo
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && !blvs.getbEstadoInteraccion().isRecibirRespuestaPwA()) {
            deactivateTopic(PepperTopicsNames.ALEGRETOPIC, believes);
            deactivateTopic(PepperTopicsNames.IRATOPIC, believes);
            deactivateTopic(PepperTopicsNames.SADTOPIC, believes);
            return true;
        }
        return false;
    }
    
}
