/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.PwA;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ActualizarPerfil extends Task{

    public ActualizarPerfil() {
        System.out.println("--- Task Actualizar Perfil Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Actualizar Perfil ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Actualizar Perfil ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Actualizar Perfil ---");
    }
    
}
