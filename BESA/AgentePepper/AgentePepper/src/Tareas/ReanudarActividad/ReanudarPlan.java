/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ReanudarActividad;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ReanudarPlan extends Task{

    public ReanudarPlan() {
        System.out.println("--- Task Reanudar Plan Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Reanudar Plan ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Reanudar Plan ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Reanudar Plan ---");
    }
    
}