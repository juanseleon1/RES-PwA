/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaConversar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarEstrategiaConversar() {
        System.out.println("--- Task Seleccionar Estrategia Conversar Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Conversar ---");
        //preguntar sobre sus sentimientos, le cuente un poco sobre como se siente, escucha activa
        //Actuar como su amigo "Te entiendo" "¿Te gustaria hablar de eso?" "Te puedo ayudar" "Hablame un poco más"
        //Dar consejos, solucion
        //"Todo estará bien" Poner mano en el hombro, cosas así
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Conversar ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Conversar ---");
    }
    
}