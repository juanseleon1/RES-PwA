/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PausarInteraccion;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import Tareas.ConversarEmpaticamente.ConversarStrategy;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class PausarActividad extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public PausarActividad() {
//        System.out.println("--- Task Pausar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Pausar Actividad ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        if(blvs.getbEstadoInteraccion().isEstaBailando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, null);
            requestService(srb,blvs);
        }
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
        
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.PAUSEVIDEO, null);
            requestService(srb,blvs);
        }
        
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, null);
        requestService(srb,blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Pausar Actividad ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Pausar Actividad ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isPausarInt() && !blvs.getbEstadoInteraccion().isEstaBailando() && !blvs.getbEstadoInteraccion().isEstaHablando() && !blvs.getbEstadoInteraccion().isConfirmacionRepDisp() && blvs.getbEstadoInteraccion().isSistemaSuspendidoInt()) {
            return true;
        }
        return false;
    }
    
}
