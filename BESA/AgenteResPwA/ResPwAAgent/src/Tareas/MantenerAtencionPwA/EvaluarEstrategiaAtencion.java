/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.Utils.ResPwAStrategy;
import rational.mapping.Believes;
import Utils.ResPwaUtils;

import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
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
    private ResPwAStrategy strat;
    public EvaluarEstrategiaAtencion() {
        
//        System.out.println("--- Task Seleccionar Estrategia Atencion Iniciada ---");
        
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Estrategia Atencion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        OpcionesAtencion estrategia = blvs.getbPerfilPwA().getAtencionStrategy();
        AtencionStrategy cs = new AtencionStrategy();
        blvs.getbEstadoActividad().setEstrategia(cs);
        cs.execStrategy(blvs);


    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Estrategia Atencion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setEstrategia(null);
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
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoActividad().getEstrategia()!=null && blvs.getbEstadoActividad().getEstrategia() instanceof AtencionStrategy && blvs.getbEstadoActividad().getEstrategia().isFinished(believes)) {
            return true;
        }
        return false;
    }
    
}
