/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CancelarActividad;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class CancelarActividadTask extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public CancelarActividadTask() {
        System.out.println("--- Task Cancelar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cancelar Actividad ---");
        ServiceDataRequest srb = null;
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        if(blvs.getbEstadoInteraccion().isEstaBailando()) {
            infoServicio.put("STOPANIMATION", null);
            srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, infoServicio);
            requestService(srb);
            infoServicio = new HashMap<>();
        }
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            infoServicio.put("STOPALL", null);
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, infoServicio);
            requestService(srb);
            infoServicio = new HashMap<>();
        }
        
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            infoServicio.put("QUITVIDEO", null);
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.QUITVIDEO, infoServicio);
            requestService(srb);
        }

        blvs.getbEstadoActividad().setFinalizoActividad(true);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cancelar Actividad ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cancelar Actividad ---");
    }
    
}
