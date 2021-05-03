/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class ActivarLetra extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public ActivarLetra() {
//        System.out.println("--- Task Activar Letra Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Activar Letra ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        if(blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustokaraoke() > 0.5 || blvs.getbEstadoInteraccion().isQuiereCantar()) {
            infoServicio.put("ACTIVATELYRICS", true);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(LocationServiceRequestType.SEARCHFREEZONE, infoServicio);
            requestService(srb,blvs);
        }
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Activar Letra ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Activar Letra ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        System.out.println("--- Check Finish Task Activar Letra ---");
        return false;
    }
    
}
