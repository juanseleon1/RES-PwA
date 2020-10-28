/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import Tareas.AnimarElogiarPwA.AnimarStrategy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mafegarces
 */
public class EvaluarEstrategiaEnriquecer extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EvaluarEstrategiaEnriquecer() {
        System.out.println("--- Task Evaluar Enriquecer Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Evaluar Enriquecer ---");
        Random rand = new Random();
        List<String> estrategias = Arrays.asList("Luces", "Pantalla", "Bailar", "Frase");
        String estrategia = estrategias.get(rand.nextInt(estrategias.size()));
        EnriquecerStrategy es = new EnriquecerStrategy();
        es.setNombre(estrategia);
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setEstrategia(es);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Evaluar Enriquecer ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Evaluar Enriquecer ---");
    }
    
}
