/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarCancion;

import RobotAgentBDI.Believes.PerfilPwA.Cancion;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCancion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarCancion() {
        System.out.println("--- Task Seleccionar Cancion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
        //buscar cancion BD
        Cancion cancion = null;
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setCancionActual(cancion);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
    }
    
}
