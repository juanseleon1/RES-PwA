/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class MostrarFotos extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MostrarFotos() {
//        System.out.println("--- Task Mostrar Fotos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mostrar Fotos ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mostrar Fotos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mostrar Fotos ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        System.out.println("--- Check Finish Task Mostrar Fotos ---");
        return false;
    }
    
}
