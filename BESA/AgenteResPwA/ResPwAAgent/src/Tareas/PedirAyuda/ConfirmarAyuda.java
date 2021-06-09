/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PedirAyuda;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ConfirmarAyuda extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public ConfirmarAyuda() {
//        System.out.println("--- Task Peticion Ayuda Iniciada ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        //System.out.println("--- Check Finish Confirmar Ayuda ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isAyudaExitosa()) {
            return true;
        }
        return false;
    }

    @Override
    public void executeTask(Believes parameters) {
        //System.out.println("--- Execute Task Confirmar Ayuda ---");
        infoServicio.put("SAY", "¿Ya se solucionó su duda?");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        //System.out.println("--- Interrupt Task Confirmar Ayuda ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        //System.out.println("--- Cancel Task Confirmar Ayuda ---");        
    }
    
}
