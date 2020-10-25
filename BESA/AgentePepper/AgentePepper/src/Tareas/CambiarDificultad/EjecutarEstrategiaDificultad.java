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
public class EjecutarEstrategiaDificultad extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEstrategiaDificultad() {
        System.out.println("--- Task Solicitar Estrategia Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Solicitar Estrategia ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Solicitar Estrategia ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Solicitar Estrategia ---");
    }

}
