/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaAtencion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEstrategiaAtencion() {
        System.out.println("--- Task Ejecutar Estrategia Atencion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Estrategia Atencion ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Estrategia Atencion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Estrategia Atencion ---");
    }
    
}
