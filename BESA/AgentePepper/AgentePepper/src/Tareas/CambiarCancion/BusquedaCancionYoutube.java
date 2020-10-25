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
public class BusquedaCancionYoutube extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public BusquedaCancionYoutube() {
        System.out.println("--- Task Busqueda Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //hacer parte API Youtube en el metodo buscarCancion
        blvs.getbEstadoActividad().getCancionActual().buscarCancion();
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Busqueda Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Busqueda Cancion ---");
    }
    
}
