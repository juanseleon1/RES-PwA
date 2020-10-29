/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MostrarEmocionesRobot;

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
public class EjecutarEstrategiaEmociones extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEstrategiaEmociones() {
        System.out.println("--- Task Ejecutar Estrategia Emociones Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Estrategia Emociones ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Estrategia Emociones ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Estrategia Emociones ---");
    }
    
}
