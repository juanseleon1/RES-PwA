/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ActivarKaraoke;

import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class MoverseFrentePwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MoverseFrentePwA() {
//        System.out.println("--- Task Moverse Frente PwA Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Moverse Frente PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("MOVETOX", blvs.getbEstadoRobot().getDistanciaX());
        infoServicio.put("MOVETOY", blvs.getbEstadoRobot().getDistanciaY());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Moverse Frente PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Moverse Frente PwA ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDesplazandose()) {
            return false;
        }
        return true;
    }
    
}
