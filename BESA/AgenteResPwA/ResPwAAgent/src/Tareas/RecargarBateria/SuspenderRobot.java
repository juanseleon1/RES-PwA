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
        System.out.println("--- Task Suspender Robot Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Suspender Robot ---");
        //buscar texto
        infoServicio.put("SAY", "textoDespedir");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        if(blvs.getbEstadoInteraccion().isEstaBailando()) {
            infoServicio.put("STOPANIMATION", null);
            srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, infoServicio);
            requestService(srb);
        }
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            infoServicio.put("STOPALL", null);
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, infoServicio);
            requestService(srb);
        }
        
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            infoServicio.put("STOPVIDEO", null);
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.PAUSEVIDEO, infoServicio);
            requestService(srb);
        }
        
        infoServicio.put("SUSPEND", null);
        srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, infoServicio);
        requestService(srb);
        infoServicio.put("SUSPENDTABLET", null);
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SUSPENDTABLET, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Suspender Robot ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Suspender Robot ---");
    }
    
}
