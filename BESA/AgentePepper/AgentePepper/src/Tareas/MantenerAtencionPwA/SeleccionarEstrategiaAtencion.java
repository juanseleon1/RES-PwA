/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaAtencion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarEstrategiaAtencion() {
        System.out.println("--- Task Seleccionar Estrategia Atencion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Atencion ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Atencion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Atencion ---");
    }
    
}
