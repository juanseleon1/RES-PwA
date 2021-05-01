/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarDificultad;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class EvaluarDesempeño extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EvaluarDesempeño() {
//        System.out.println("--- Task Evaluar Desempeño Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Evaluar Desempeño ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Evaluar Desempeño ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Evaluar Desempeño ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
