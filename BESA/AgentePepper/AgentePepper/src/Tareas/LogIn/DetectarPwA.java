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
public class DetectarPwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;
    
    public DetectarPwA() {
        System.out.println("--- Task Detectar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Detectar PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("DETECTNEWFACE", blvs.getbPerfilPwA().getNombre()+" "+blvs.getbPerfilPwA().getApellidos());
        infoServicio.put("LOGIN", null);
        //¿si ya existe?
        infoServicio.put("GETFACELIST", null);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Detectar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Detectar PwA ---");
    }
    
    
}
