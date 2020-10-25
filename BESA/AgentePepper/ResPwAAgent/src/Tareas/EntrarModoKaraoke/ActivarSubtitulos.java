/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.EntrarModoKaraoke;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class ActivarSubtitulos extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public ActivarSubtitulos() {
        System.out.println("--- Task Activar Subtitulos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Activar Subtitulos ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Activar Subtitulos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Activar Subtitulos ---");
    }
    
}
