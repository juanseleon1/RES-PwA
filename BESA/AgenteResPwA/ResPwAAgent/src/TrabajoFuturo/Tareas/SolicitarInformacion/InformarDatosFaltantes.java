/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoFuturo.Tareas.SolicitarInformacion;

import rational.mapping.Believes;
import Utils.ResPwaUtils;
import java.util.HashMap;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class InformarDatosFaltantes extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public InformarDatosFaltantes() {
//        System.out.println("--- Task Informar Datos Faltantes Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Informar Datos Faltantes ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Informar Datos Faltantes ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Informar Datos Faltantes ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
