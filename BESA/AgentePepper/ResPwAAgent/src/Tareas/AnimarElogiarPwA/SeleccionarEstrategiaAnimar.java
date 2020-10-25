/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaAnimar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    

    public SeleccionarEstrategiaAnimar() {
        System.out.println("--- Task Seleccionar Estrategia Animar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Animar PwA ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Animar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Animar PwA ---");
    }
    
}
