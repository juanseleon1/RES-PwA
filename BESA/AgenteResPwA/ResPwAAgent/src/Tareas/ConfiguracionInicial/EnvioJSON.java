/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tareas.ConfiguracionInicial;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Accion;
import ResPwAEntities.Perfilpwa;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author maria.f.garces.cala
 */
public class EnvioJSON extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EnvioJSON() {
//        System.out.println("--- Task Envio JSON Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Envio JSON ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Envio JSON ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Envio JSON ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        return false;
    }

}
