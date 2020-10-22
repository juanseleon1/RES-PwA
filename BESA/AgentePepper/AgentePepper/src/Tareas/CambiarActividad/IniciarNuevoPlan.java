/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tareas.CambiarActividad;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class IniciarNuevoPlan extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio;

    public IniciarNuevoPlan() {
        System.out.println("--- Task Iniciar Nuevo Plan Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Iniciar Nuevo Plan ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Iniciar Nuevo Plan ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Iniciar Nuevo Plan ---");
    }

}
