/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ABC extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public ABC() {
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task ABC ---");
        //buscar cuento
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        System.out.println("Letra ABC: "+blvs.getLetra());
        char c=blvs.getLetra();
        c++;
        blvs.setLetra(c);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task ABC ---");
        System.out.println("--- INTERRUMPIENDO ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task ABC ---");
        System.out.println("--- CANCELANDO ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getLetra()=='Z';
    }

}
