/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class Multiplicar extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public Multiplicar() {
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Multiplicar ---");
        //buscar cuento
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        System.out.println("Factor: "+blvs.getResultado());
        blvs.setResultado(blvs.getResultado()*blvs.getFactor());
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Multiplicar ---");
        System.out.println("--- INTERRUMPIENDO ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Multiplicar ---");
        System.out.println("--- CANCELANDO ---");

    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getResultado() > 10000;
    }

}
