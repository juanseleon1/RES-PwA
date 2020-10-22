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
public class SeleccionarCancion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public SeleccionarCancion() {
        System.out.println("--- Task Seleccionar Cancion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");
        infoServicio.put("obtenerEstadoEmocional", null);
        //buscar cancion BD
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //cambiar parametro cancion seleccionada
        infoServicio.put("reproducirSonido", blvs.getbEstadoActividad().getCancionActual().getLinkVideo());
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
    }
    
}
