/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PausarInteraccion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class PausarActividad extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public PausarActividad() {
        System.out.println("--- Task Pausar Actividad Iniciada ---");
        infoServicio.put("SUSPEND", null);
        ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, infoServicio);
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Pausar Actividad ---");
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
