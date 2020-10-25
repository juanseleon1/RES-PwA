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
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class DetectarPwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>(); 
    
    public DetectarPwA() {
        System.out.println("--- Task Detectar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Detectar PwA ---");
        ServiceDataRequest srb = null;
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("DETECTPWA", blvs.getbPerfilPwA().getNombre()+" "+blvs.getbPerfilPwA().getApellidos());
        srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.DETECTNEWFACE, infoServicio);
        requestService(srb);
        infoServicio.clear();
        
        infoServicio.put("LOGIN", null);
        srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.LOGIN, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Detectar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Detectar PwA ---");
    }
    
    
}
