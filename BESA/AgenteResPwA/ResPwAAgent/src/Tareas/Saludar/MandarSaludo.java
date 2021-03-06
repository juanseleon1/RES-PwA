/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Saludar;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class MandarSaludo extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MandarSaludo() {
        //System.out.println("--- Task Mandar Saludo Iniciada ---");
    }
    
    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando()) {
            return true;
        }
        return false;
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mandar Saludo ---");
        infoServicio.put("SAY", "jelou bish");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, (RobotAgentBelieves) parameters);
    }
    
    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mandar Saludo ---");
    }
    
    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mandar Saludo ---");
    }
    
}
