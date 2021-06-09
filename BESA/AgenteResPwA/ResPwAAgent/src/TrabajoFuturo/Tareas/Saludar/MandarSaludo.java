/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoFuturo.Tareas.Saludar;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class MandarSaludo extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MandarSaludo() {
        //System.out.println("--- Task Mandar Saludo Iniciada ---");
    }
    
    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoInteraccion().isRecibirRespuestaPwA()) {
            blvs.getbEstadoInteraccion().setSaludo(true);
            ResPwaUtils.activateTopic( PepperTopicsNames.BASICTOPIC, believes);
            return true;
        }
        return false;
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mandar Saludo ---");
        infoServicio.put("SAY", "jelou bish");
        ResPwaUtils.activateTopic( PepperTopicsNames.BASICTOPIC, parameters);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
    }
    
    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mandar Saludo ---");
        ResPwaUtils.deactivateTopic( PepperTopicsNames.BASICTOPIC, believes);
    }
    
    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mandar Saludo ---");
        ResPwaUtils.deactivateTopic( PepperTopicsNames.BASICTOPIC, believes);
    }
    
}
