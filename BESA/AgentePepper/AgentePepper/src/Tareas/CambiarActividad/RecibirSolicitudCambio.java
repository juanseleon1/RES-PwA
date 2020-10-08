/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tareas.CambiarActividad;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class RecibirSolicitudCambio extends Task{

    public RecibirSolicitudCambio() {
        System.out.println("--- Task Recibir Solicitud Cambio Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Solicitud Cambio ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Solicitud Cambio ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Solicitud Cambio ---");
    }

}
