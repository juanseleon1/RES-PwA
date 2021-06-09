/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.VerificarDispositivos;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateService;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class VerificacionDispositivos extends Task{

    private HashMap<String,Object> infoServicio = new HashMap<>();
    private long tiempoInicio = 0;

    public VerificacionDispositivos() {
//        System.out.println("--- Task VerificacionDispositivos PwA Iniciada ---");
        tiempoInicio = System.currentTimeMillis();
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        System.out.println("--- Check Finish VerificacionDispositivos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoRobot().isVerificacionDispositivos() && (System.currentTimeMillis()-tiempoInicio) >= 30000)
        {
            return true;
        }
        return false;
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task VerificacionDispositivos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("VERIFYDEVICES", false);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.VERIFYDEVICES, infoServicio);
        ResPwaUtils.requestService(srb,blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task VerificacionDispositivos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task VerificacionDispositivos ---");
    }
    
}
