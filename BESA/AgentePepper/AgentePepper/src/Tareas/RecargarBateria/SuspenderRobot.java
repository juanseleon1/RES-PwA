/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.RecargarBateria;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class SuspenderRobot extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public SuspenderRobot() {
        System.out.println("--- Task Suspender Robot Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Suspender Robot ---");
        //buscar texto
        infoServicio.put("SAY", "texto");
        infoServicio.put("SUSPEND", null);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Suspender Robot ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Suspender Robot ---");
    }
    
}
