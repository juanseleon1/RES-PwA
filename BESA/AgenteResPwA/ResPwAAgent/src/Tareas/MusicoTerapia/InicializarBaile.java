/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class InicializarBaile extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    //revisa el espacio para que no se choque
    public InicializarBaile() {
//        System.out.println("--- Task Cambiar Baile Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cambiar Baile ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //buscar baile
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Baile---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            ResPwaUtils.requestService(srb, blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoRobot().isLibreEntorno() && !blvs.getbEstadoInteraccion().isDesplazandose()) {
            return true;
        }
        return false;
    }

}
