/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwaTask;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaConversar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public EjecutarEstrategiaConversar() {
//        System.out.println("--- Task Ejecutar Estrategia Conversar Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Estrategia Conversar ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        ResPwAStrategy estrategia = blvs.getbEstadoActividad().getEstrategia();
        
        ServiceDataRequest srb = estrategia.execStrategy();
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Estrategia Conversar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Estrategia Conversar ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            return false;
        }
        return true;
    }
}
