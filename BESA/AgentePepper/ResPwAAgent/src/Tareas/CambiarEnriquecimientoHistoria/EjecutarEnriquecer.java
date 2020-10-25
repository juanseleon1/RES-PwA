/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EjecutarEnriquecer extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEnriquecer() {
        System.out.println("--- Task Ejecutar Enriquecer Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Enriquecer ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Enriquecer ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Enriquecer ---");
    }
    
}
