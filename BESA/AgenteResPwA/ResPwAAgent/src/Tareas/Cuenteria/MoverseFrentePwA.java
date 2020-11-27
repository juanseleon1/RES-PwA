/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
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
        //parametros desde naoqi
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        infoServicio.put("RADIO", 0.5);
        infoServicio.put("DISTANCIAMAX", 0.5);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(LocationServiceRequestType.SEARCHFREEZONE, infoServicio);
        requestService(srb,blvs);
        
        infoServicio = new HashMap<>();
        infoServicio.put("MOVETOX", blvs.getbEstadoRobot().getDistanciaX());
        infoServicio.put("MOVETOY", blvs.getbEstadoRobot().getDistanciaY());
        srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, infoServicio);
        requestService(srb,blvs);

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Moverse Frente PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Moverse Frente PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoRobot().isLibreEntorno() && !blvs.getbEstadoInteraccion().isDesplazandose()) {
            return true;
        }
        return false;
    }
}
