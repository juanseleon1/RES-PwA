/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ReanudarActividad;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class RecibirNotificacionReanudar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public RecibirNotificacionReanudar() {
        System.out.println("--- Task Recibir Notificacion Reanudar Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Notificacion Reanudar ---");
        infoServicio.put("WAKEUP", null);
        ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.WAKEUP, infoServicio);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Notificacion Reanudar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Notificar Reanudar ---");
    }
    
}
