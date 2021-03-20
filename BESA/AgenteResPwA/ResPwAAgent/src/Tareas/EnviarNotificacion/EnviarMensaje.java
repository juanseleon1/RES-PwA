/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.EnviarNotificacion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class EnviarMensaje extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EnviarMensaje() {
//        System.out.println("--- Task Enviar Mensaje Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Enviar Mensaje ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Enviar Mensaje ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Enviar Mensaje ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
