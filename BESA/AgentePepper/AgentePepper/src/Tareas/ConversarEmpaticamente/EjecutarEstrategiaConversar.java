/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaConversar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public EjecutarEstrategiaConversar() {
        System.out.println("--- Task Ejecutar Estrategia Conversar Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void interruptTask(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelTask(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
