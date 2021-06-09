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
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class PeticionAyuda extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    double tiempo = 0;
    
    public PeticionAyuda() {
//        System.out.println("--- Task Peticion Ayuda Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Peticion Ayuda ---");
        tiempo = System.currentTimeMillis();
        ResPwaUtils.activateTopic(PepperTopicsNames.AYUDATOPIC, parameters);
        infoServicio.put("SAY", "¿En que te puedo ayudar?");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        //System.out.println("--- Interrupt Task Peticion Ayuda ---");
        ResPwaUtils.deactivateTopic(PepperTopicsNames.AYUDATOPIC, believes);
    }

    @Override
    public void cancelTask(Believes believes) {
        //System.out.println("--- Cancel Task Peticion Ayuda ---");
        ResPwaUtils.deactivateTopic(PepperTopicsNames.AYUDATOPIC, believes);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().getRespuestasPorContexto() > 1) {
            ResPwaUtils.deactivateTopic(PepperTopicsNames.AYUDATOPIC, believes);
            blvs.getbEstadoInteraccion().setRespuestasPorContexto(0);
            return true;
        }
        return false;
    }
    
}
