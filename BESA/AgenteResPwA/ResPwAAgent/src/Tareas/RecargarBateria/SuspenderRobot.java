/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.RecargarBateria;

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
public class SuspenderRobot extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SuspenderRobot() {
//        System.out.println("--- Task Suspender Robot Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Suspender Robot ---");
        //buscar texto
        infoServicio.put("SAY", "textoDespedir");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        if(blvs.getbEstadoInteraccion().isEstaBailando() || blvs.getbEstadoInteraccion().isEstaMoviendo()) {
            srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, null);
            requestService(srb);
        }
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb);
        }
        
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.PAUSEVIDEO, null);
            requestService(srb);
        }
        
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SUSPENDTABLET, null);
        requestService(srb);
        
        srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, null);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Suspender Robot ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isSistemaSuspendido()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.WAKEUP, null);
            requestService(srb);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Suspender Robot ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isSistemaSuspendido()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.WAKEUP, null);
            requestService(srb);
        }        
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isSistemaSuspendido()) {
            return true;
        }
        return false;
    }
    
}
