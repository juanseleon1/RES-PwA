/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class EjecutarBaile extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public EjecutarBaile() {
        System.out.println("--- Task Ejecutar Baile Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Baile ---");
        //buscar tipo de baile dependiendo de canción
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("RUNANIMATION", blvs.getbEstadoActividad().getCancionActual().getTags());
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Baile ---");
    }
    
}
