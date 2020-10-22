/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.EnviarNotificacion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class ObtenerInfoCuidador extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public ObtenerInfoCuidador() {
        System.out.println("--- Task Obtener Informacion Cuidador Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Obtener Informacion ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Obtener Informacion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Obtener Informacion ---");
    }
    
}
