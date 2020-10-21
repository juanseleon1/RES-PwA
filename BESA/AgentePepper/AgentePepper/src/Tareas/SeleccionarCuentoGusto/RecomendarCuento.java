/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import RobotAgentBDI.ResPwaTask;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class RecomendarCuento extends ResPwaTask{
    
    public RecomendarCuento() {
        System.out.println("--- Task Recomendar Cuento Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recomendar Cuento ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recomendar Cuento ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recomendar Cuento ---");
    }
    
}
