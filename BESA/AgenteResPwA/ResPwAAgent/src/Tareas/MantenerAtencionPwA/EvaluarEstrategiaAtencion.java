/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class EvaluarEstrategiaAtencion extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EvaluarEstrategiaAtencion() {
//        System.out.println("--- Task Seleccionar Estrategia Atencion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Atencion ---");
        
        //complejidad actividad es baja -> mayor tiempo de interacción 
        //cambiar actividad, preguntar si desea cambiarla a una de sus favoritas
        //preguntar si desea cambiar cancion/cuento/dificultad
        //"Me siento solo" "no me dejes"
        //adivinanzas, dato curioso
        //si se va, irlo a buscar
        
        //  ACÁ DEBE ACTIVARSE EL TOPICO PARA LLAMAR LA ATENCION
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        
        OpcionesAtencion estrategia = blvs.getbPerfilPwA().getAtencionStrategy();
        AtencionStrategy cs = new AtencionStrategy();
        
        cs.execStrategy();
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Atencion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Estrategia Atencion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setEstrategia(null);
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoActividad().getEstrategia()!=null && blvs.getbEstadoActividad().getEstrategia() instanceof AtencionStrategy) {
            return true;
        }
        return false;
    }
    
}
