/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CancelarActividad;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import Tareas.AnimarElogiarPwA.AnimarStrategy;
import java.util.HashMap;
import rational.mapping.Believes;

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
        //parametros desde naoqi
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("MOVETOX", blvs.getbEstadoRobot().getDistanciaX());
        infoServicio.put("MOVETOY", blvs.getbEstadoRobot().getDistanciaY());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Moverse Frente PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            requestService(srb);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Moverse Frente PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            requestService(srb);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isDesplazandose()) {
            return true;
        }
        return false;
    }
    
}
