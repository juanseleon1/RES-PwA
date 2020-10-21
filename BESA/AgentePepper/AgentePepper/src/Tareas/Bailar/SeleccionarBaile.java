/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;

/**
 *
 * @author mafegarces
 */
public class SeleccionarBaile extends ResPwaTask{

    public SeleccionarBaile() {
        System.out.println("--- Task Seleccionar Baile Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Baile ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Baile ---");
    }
    
}
