/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class VerificarObstaculos extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public VerificarObstaculos() {
        System.out.println("--- Task Verificar Obstaculos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Verificar Obstaculos ---");
        List<Double> parametros = null ; //1. radio, 2.restriccion desplazamiento (distancia max)
        parametros.add(0.5);
        parametros.add(0.5);
        infoServicio.put("SEARCHFREEZONE", parametros);
        
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Verificar Obstaculos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Verificar Obstaculos ---");
    }
    
}
