/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.GenerarReporteInteraccion;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class PersistirInfoInteraccion extends Task{

    public PersistirInfoInteraccion() {
        System.out.println("--- Task Persistir Informacion Interaccion Iniciada ---");
    }

    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Persistir Informacion Interaccion ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Persistir Informacion Interaccion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Persistir Informacion Interaccion ---");
    }
    
}
