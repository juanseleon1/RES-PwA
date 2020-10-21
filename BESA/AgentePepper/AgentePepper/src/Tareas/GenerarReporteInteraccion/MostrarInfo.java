/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.GenerarReporteInteraccion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;

/**
 *
 * @author mafegarces
 */
public class MostrarInfo extends ResPwaTask{

    public MostrarInfo() {
        System.out.println("--- Task Mostrar Informacion Interaccion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mostrar Informacion Interaccion ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mostrar Informacion Interaccion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mostrar Informacion Interaccion ---");
    }
    
}
