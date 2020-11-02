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
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class PausarActividad extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public PausarActividad() {
        System.out.println("--- Task Pausar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Pausar Actividad ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        if(blvs.getbEstadoInteraccion().isEstaBailando()) {
            infoServicio.put("STOPANIMATION", null);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, infoServicio);
            requestService(srb);
            infoServicio = new HashMap<>();
        }
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            infoServicio.put("STOPALL", null);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, infoServicio);
            requestService(srb);
            infoServicio = new HashMap<>();
        }
        
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            infoServicio.put("PAUSEVIDEO", null);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.PAUSEVIDEO, infoServicio);
            requestService(srb);
            infoServicio = new HashMap<>();
        }
        
        infoServicio.put("SUSPEND", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Pausar Actividad ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Pausar Actividad ---");
    }
    
}
