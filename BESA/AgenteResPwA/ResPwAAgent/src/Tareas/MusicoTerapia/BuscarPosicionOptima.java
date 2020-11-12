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
public class BuscarPosicionOptima extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public BuscarPosicionOptima() {
//        System.out.println("--- Task Buscar Posicion Optima Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Buscar Posicion Optima ---");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Buscar Posicion Optima ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Buscar Posicion Optima ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        System.out.println("--- Check Finish Task Buscar Posicion Optima ---");
        return false;
    }
    
}
