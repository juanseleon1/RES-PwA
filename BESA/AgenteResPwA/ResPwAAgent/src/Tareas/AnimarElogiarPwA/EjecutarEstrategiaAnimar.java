/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaAnimar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    

    public EjecutarEstrategiaAnimar() {
//        System.out.println("--- Task Ejecutar Estrategia Animar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Estrategia Animar PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        ResPwAStrategy estrategia = blvs.getbEstadoActividad().getEstrategia();
        
        ServiceDataRequest srb = estrategia.execStrategy();
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Estrategia Animar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Estrategia Animar PwA ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
