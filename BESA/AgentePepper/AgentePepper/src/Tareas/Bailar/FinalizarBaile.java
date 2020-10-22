/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class FinalizarBaile extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public FinalizarBaile() {
        System.out.println("--- Task Finalizar Baile Iniciada ---");
        infoServicio.put("detenerAnimacion", null);
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Finalizar Baile ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Finalizar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Finalizar Baile ---");
    }
    
}
