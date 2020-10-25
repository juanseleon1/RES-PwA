/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;


import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class InicializarBaile extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    //revisa el espacio para que no se choque

    public InicializarBaile() {
        System.out.println("--- Task Cambiar Baile Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cambiar Baile ---");
        infoServicio.put("RADIO", 0.5);
        infoServicio.put("DISTANCIAMAX", 0.5);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(LocationServiceRequestType.SEARCHFREEZONE, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Baile---");
    }
    
}
