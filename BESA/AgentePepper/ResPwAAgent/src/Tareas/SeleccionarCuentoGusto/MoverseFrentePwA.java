/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class MoverseFrentePwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MoverseFrentePwA() {
        System.out.println("--- Task Moverse Frente PwA Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Moverse Frente PwA ---");
        //no se los parametros jiji, creo que es más desde naoqi
        infoServicio.put("MOVETO", null);
        ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, infoServicio);
        
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Moverse Frente PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Moverse Frente PwA ---");
    }
}
