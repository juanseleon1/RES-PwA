/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CancelarActividad;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import Tareas.AnimarElogiarPwA.AnimarStrategy;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class PreguntarActividad extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public PreguntarActividad() {
//        System.out.println("--- Task Preguntar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Preguntar Actividad ---");
        
        //buscar texto
        infoServicio.put("SAY", "AskActivity");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Preguntar Actividad ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Preguntar Actividad ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando()) {
            return true;
        }
        return false;
    }
    
}
