/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ReanudarActividad;

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
public class RecibirNotificacionReanudar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public RecibirNotificacionReanudar() {
//        System.out.println("--- Task Recibir Notificacion Reanudar Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Notificacion Reanudar ---");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        infoServicio.put("WAKEUP", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.WAKEUP, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("WAKETABLET", null);
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.WAKETABLET, infoServicio);
        requestService(srb);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Notificacion Reanudar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Notificar Reanudar ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() && blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            return true;
        }
        return false;
    }
    
}
