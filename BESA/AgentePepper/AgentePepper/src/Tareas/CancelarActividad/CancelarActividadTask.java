/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CancelarActividad;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class CancelarActividadTask extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public CancelarActividadTask() {
        System.out.println("--- Task Cancelar Actividad Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cancelar Actividad ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cancelar Actividad ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cancelar Actividad ---");
    }
    
}
