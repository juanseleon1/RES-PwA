/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PedirAyuda;

import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class PeticionAyuda extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;
    
    public PeticionAyuda() {
        System.out.println("--- Task Peticion Ayuda Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Peticion Ayuda ---");
        //dar respuesta a petición
        infoServicio.put("SAY", "respuesta");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Peticion Ayuda ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Peticion Ayuda ---");
    }
    
}
