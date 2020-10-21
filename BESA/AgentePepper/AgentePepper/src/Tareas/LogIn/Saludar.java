/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.ResPwaTask;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class Saludar extends ResPwaTask{
    
    //incluye detectar la cara del PwA y saludarlo
    
    public Saludar() {
        System.out.println("--- Task Saludar Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Saludar ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Saludar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Saludar ---");
    }
    
}
