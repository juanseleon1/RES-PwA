/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PedirAyuda;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class PeticionAyuda extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    double tiempo = 0;
    
    public PeticionAyuda() {
//        System.out.println("--- Task Peticion Ayuda Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Peticion Ayuda ---");
        tiempo = System.currentTimeMillis();
        //dar respuesta a petición
        activateTopic(PepperTopicsNames.AYUDATOPIC, parameters);
        infoServicio.put("SAY", "¿En que te puedo ayudar?");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb, (RobotAgentBelieves) parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        //System.out.println("--- Interrupt Task Peticion Ayuda ---");
        deactivateTopic(PepperTopicsNames.AYUDATOPIC, believes);
    }

    @Override
    public void cancelTask(Believes believes) {
        //System.out.println("--- Cancel Task Peticion Ayuda ---");
        deactivateTopic(PepperTopicsNames.AYUDATOPIC, believes);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if((System.currentTimeMillis() - tiempo)/1000 >= 90) {
            return true;
        }
        return false;
    }
    
}
