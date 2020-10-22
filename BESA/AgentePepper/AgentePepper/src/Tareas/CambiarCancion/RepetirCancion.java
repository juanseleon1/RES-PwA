/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarCancion;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class RepetirCancion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public RepetirCancion() {
        System.out.println("--- Task Repetir Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Repetir Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("reproducirSonido", blvs.getbEstadoActividad().getCancionActual().getLinkVideo());
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Repetir Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Repetir Cancion ---");
    }
    
}
