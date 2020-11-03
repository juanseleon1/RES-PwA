/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class Saludar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    //incluye detectar la cara del PwA y saludarlo
    
    public Saludar() {
//        System.out.println("--- Task Saludar Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Saludar ---");
        ServiceDataRequest srb = null;
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("SAY", "Hola" + blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getNombrepreferido());
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        //mirar url animacion
        infoServicio.put("RUNANIMATION", "Saludar");
        srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Saludar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Saludar ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
