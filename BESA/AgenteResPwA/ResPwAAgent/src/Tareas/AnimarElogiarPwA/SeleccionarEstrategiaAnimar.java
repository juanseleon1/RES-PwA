/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import EmotionalAnalyzerAgent.EmotionPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaAnimar extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    

    public SeleccionarEstrategiaAnimar() {
        System.out.println("--- Task Seleccionar Estrategia Animar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Animar PwA ---");
        
        //animar con frases elogiantes, chistes
        Random rand = new Random();
        List<String> estrategias = Arrays.asList("Frase elogiante", "Chiste");
        String estrategia = estrategias.get(rand.nextInt(estrategias.size()));
        AnimarStrategy as = new AnimarStrategy();
        as.setNombre(estrategia);
        //si es necesario se mandan parametros (como blvs)
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setEstrategia(as);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Animar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Animar PwA ---");
    }
    
}
