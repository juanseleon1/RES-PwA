/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import EmotionalAnalyzerAgent.EmotionalEventType;
import EmotionalAnalyzerAgent.WHO;
import ResPwAEntities.Cuento;
import ResPwAEntities.Frases;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalEvent;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ReproducirCuento extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public ReproducirCuento() {
//        System.out.println("--- Task Buscar Animaciones Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Buscar Animaciones ---");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        Cuento cuento = blvs.getbEstadoActividad().getCuentoActual();
        
        infoServicio.put("SAYWITHMOVEMENT", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getContenido());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAYWITHMOVEMENT, infoServicio);
        ResPwaUtils.requestService(srb,blvs);
        
        if (!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getUrlimagen().equals(" ")){
            infoServicio.put("SHOWIMG", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getUrlimagen());
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getAccion().equals(" ")){
            infoServicio.put("TAGSDANCE", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getAccion());
            infoServicio.put("FACTOR", null);
            ResPwaUtils.requestService(srb,blvs);
        }
        
        if(!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getEmotionalevent().equals(" ")){
            EmotionalEvent evt = new EmotionalEvent(WHO.ROBOT.toString(), cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getEmotionalevent(), null);
            blvs.getbEstadoRobot().processEmotionalEvent(evt);
        }
        
        blvs.getbEstadoActividad().setIndexCuento(blvs.getbEstadoActividad().getIndexCuento()+1);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoActividad().getCuentoActual().getFrasesList().size() == blvs.getbEstadoActividad().getIndexCuento()) {
            blvs.getbEstadoActividad().setIndexCuento(0);
            return true;
        }
        return false;
    }
    
}
