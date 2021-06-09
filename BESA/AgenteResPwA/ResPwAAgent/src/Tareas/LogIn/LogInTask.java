/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;

import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class LogInTask extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>(); 
    
    public LogInTask() {
//        System.out.println("--- Task LogInTask Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task LogIn ---");

        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.LOGIN, null);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task LogIn ---");
        
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task LogIn ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
                    System.out.println("///////////////TF2//////////////");

        if(blvs.getbEstadoInteraccion().isLogged()) {
            return true;
        }
        return false;
    }
    
    
}
