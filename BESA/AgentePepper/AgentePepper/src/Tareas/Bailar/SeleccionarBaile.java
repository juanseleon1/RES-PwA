/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;

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
public class SeleccionarBaile extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarBaile() {
        System.out.println("--- Task Seleccionar Baile Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Baile ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
        //buscar baile
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Baile ---");
    }
    
}
