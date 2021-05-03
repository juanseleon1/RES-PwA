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
public class LogInTask extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>(); 
    
    public LogInTask() {
//        System.out.println("--- Task LogInTask Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task LogIn ---");

        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.LOGIN, null);
        requestService(srb, (RobotAgentBelieves) parameters);
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
                super.checkFinish(believes);

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
                    System.out.println("///////////////TF2//////////////");

        if(blvs.getbEstadoInteraccion().isLogged()) {
            return true;
        }
        return false;
    }
    
    
}
