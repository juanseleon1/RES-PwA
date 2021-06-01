/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TrabajoFuturo.Tareas.CambiarDificultad;

import rational.mapping.Believes;
import Utils.ResPwaUtils;
import java.util.HashMap;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaDificultad extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEstrategiaDificultad() {
//        System.out.println("--- Task Solicitar Estrategia Iniciada ---");
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

    @Override
    public boolean checkFinish(Believes believes) {
                

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
