/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.EstimularEmocionalmente;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class InterpretarEstadoFlujo extends Task{

    public InterpretarEstadoFlujo() {
        System.out.println("--- Task Interpretar Estado Flujo Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Interpretar Estado Flujo ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Interpretar Estado Flujo ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Interpretar Estado Flujo ---");
    }
    
}
