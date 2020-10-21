/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaAnimar extends ResPwaTask{

    public EjecutarEstrategiaAnimar() {
        System.out.println("--- Task Ejecutar Estrategia Animar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Estrategia Animar PwA ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Estrategia Animar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Estrategia Animar PwA ---");
    }
    
}
