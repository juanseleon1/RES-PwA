/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

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
public class EvaluarEstrategiaEnriquecer extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EvaluarEstrategiaEnriquecer() {
        System.out.println("--- Task Evaluar Enriquecer Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Evaluar Enriquecer ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Evaluar Enriquecer ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Evaluar Enriquecer ---");
    }
    
}
