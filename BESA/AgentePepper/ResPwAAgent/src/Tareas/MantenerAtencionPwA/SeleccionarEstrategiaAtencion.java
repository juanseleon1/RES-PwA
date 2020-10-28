/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import Tareas.ConversarEmpaticamente.ConversarStrategy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mafegarces
 */
public class SeleccionarEstrategiaAtencion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarEstrategiaAtencion() {
        System.out.println("--- Task Seleccionar Estrategia Atencion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Atencion ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
        
        //complejidad actividad es baja -> mayor tiempo de interacción 
        //cambiar actividad, preguntar si desea cambiarla a una de sus favoritas
        //preguntar si desea cambiar cancion/cuento/dificultad
        //"Me siento solo" "no me dejes"
        //adivinanzas, dato curioso
        //si se va, irlo a buscar
        
        Random rand = new Random();
        List<String> estrategias = Arrays.asList("Adivinanza","Dato Curioso","Cambiar Actividad","Cambiar Cancion","Cambiar Cuento");
        String estrategia = estrategias.get(rand.nextInt(estrategias.size()));
        ConversarStrategy cs = new ConversarStrategy();
        cs.setNombre(estrategia);
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setEstrategia(cs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Atencion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Atencion ---");
    }
    
}
