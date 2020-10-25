/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.RecargarBateria;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
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
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        infoServicio.clear();
        infoServicio.put("SUSPEND", null);
        ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, infoServicio);
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
