/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ReanudarActividad;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class SolicitarPosicionPwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SolicitarPosicionPwA() {
        System.out.println("--- Task Solicitar Posicion PwA Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Solicitar Posicion PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("DETECTPWA", infoServicio.put("DETECTPWA", blvs.getbPerfilPwA().getPerfil().getIdrobot()));
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.GETFACELIST, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Solicitar Posicion PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Solicitar Posicion PwA ---");
    }
    
}
