/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class Saludar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;
    
    //incluye detectar la cara del PwA y saludarlo
    
    public Saludar() {
        System.out.println("--- Task Saludar Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Saludar ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("SAY", "Hola" + blvs.getbPerfilPwA().getPreferencias().getNombrePreferido());
        //mirar url animacion
        infoServicio.put("RUNANIMATION", "animacionSaludar");
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
