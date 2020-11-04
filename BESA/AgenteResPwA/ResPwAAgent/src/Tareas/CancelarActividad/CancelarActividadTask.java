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
import Tareas.AnimarElogiarPwA.AnimarStrategy;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class CancelarActividadTask extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public CancelarActividadTask() {
//        System.out.println("--- Task Cancelar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cancelar Actividad ---");
        ServiceDataRequest srb = null;
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
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.QUITVIDEO, null);
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

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaMoviendo() && !blvs.getbEstadoInteraccion().isEstaBailando() && !blvs.getbEstadoInteraccion().isConfirmacionRepDisp() && !blvs.getbEstadoInteraccion().isEstaHablando()) {
            return true;
        }
        return false;
    }
    
}
