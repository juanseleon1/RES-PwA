/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ReanudarActividad;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;

/**
 *
 * @author mafegarces
 */
public class RecibirNotificacionReanudar extends ResPwaTask{

    public RecibirNotificacionReanudar() {
        System.out.println("--- Task Recibir Notificacion Reanudar Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Notificacion Reanudar ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Notificacion Reanudar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Notificar Reanudar ---");
    }
    
}
