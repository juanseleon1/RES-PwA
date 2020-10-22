/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarCancion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class RecibirRetroalimentacion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public RecibirRetroalimentacion() {
        System.out.println("--- Task Recibir Retroalimentacion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Retroalimentacion ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        //buscar texto
        infoServicio.put("SAY", "Texto");
        //buscar url
        infoServicio.put("SHOWIMG", "url");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Retroalimentacion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Retroalimentacion ---");
    }
    
}
