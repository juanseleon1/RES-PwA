/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.RecargarBateria;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class UbicarEstacionCarga extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public UbicarEstacionCarga() {
//        System.out.println("--- Task Ubicar Estacion Carga Iniciada ---");
    }

    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ubicar Estacion Carga ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ubicar Estacion Carga ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ubicar Estacion Carga ---");
    }
    
}
