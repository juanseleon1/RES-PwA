/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversarEmpaticamente;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import Tareas.AnimarElogiarPwA.AnimarStrategy;
import Tareas.Cuenteria.EnriquecerStrategy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaConversar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarEstrategiaConversar() {
//        System.out.println("--- Task Seleccionar Estrategia Conversar Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Conversar ---");
        //Hacer preguntas: sobre sus sentimientos
        //Actuar como su amigo "Te entiendo" "¿Te gustaria hablar de eso?" "Te puedo ayudar" "Hablame un poco más"
        //Dar consejos, solucion ej:respirar
        //"Todo estará bien" Poner mano en el hombro, cosas así
        //Siempre: Tener contacto visual, escuchar
        
        Random rand = new Random();
        List<String> estrategias = Arrays.asList("PreguntaEmp","Consejo");
        String estrategia = estrategias.get(rand.nextInt(estrategias.size()));
        ConversarStrategy cs = new ConversarStrategy();
        cs.setNombre(estrategia);
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setEstrategia(cs);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Conversar ---");
        
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Conversar ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setEstrategia(null);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoActividad().getEstrategia()!=null && blvs.getbEstadoActividad().getEstrategia() instanceof ConversarStrategy) {
            return true;
        }
        return false;
    }
    
}
