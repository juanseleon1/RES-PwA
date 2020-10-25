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
        ServiceRequestBuilder srb = null;
        System.out.println("--- Execute Task Cambiar Baile ---");
        List<Double> parametros = null ; //1. radio, 2.restriccion desplazamiento (distancia max)
        parametros.add(0.5);
        parametros.add(0.5);
        infoServicio.put("SEARCHFREEZONE", parametros);
        srb.buildRequest(LocationServiceRequestType.SEARCHFREEZONE, infoServicio);
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
