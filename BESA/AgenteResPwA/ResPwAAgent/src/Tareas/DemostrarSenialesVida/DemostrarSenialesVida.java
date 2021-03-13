/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.DemostrarSenialesVida;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityService;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class DemostrarSenialesVida extends ResPwaTask{
    
    private HashMap<String, Object> infoServicio = new HashMap<>();
    private static final String ACTIVATE_LIFE_SIGNALS = "ACTIVATELIFESIGNALS";
    
    @Override
    public boolean checkFinish(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executeTask(Believes parameters) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put( ACTIVATE_LIFE_SIGNALS, true); 
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.ACTIVATELIFESIGNALS , infoServicio);
        requestService(srb, blvs);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task DemostrarSenialesVida ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task DemostrarSenialesVida ---");
    }
    
}
