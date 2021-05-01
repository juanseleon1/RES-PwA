/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import ResPwAEntities.Frases;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class ReproducirCuento extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public ReproducirCuento() {
//        System.out.println("--- Task Buscar Animaciones Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Buscar Animaciones ---");
        //busca animaciones y mensajes base de datos
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        //enviar cuento
        infoServicio.put("SAYWITHMOVEMENT", blvs.getbEstadoActividad().getCuentoActual().getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()));
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAYWITHMOVEMENT, infoServicio);
        requestService(srb,blvs);
        blvs.getbEstadoActividad().setIndexCuento(blvs.getbEstadoActividad().getIndexCuento()+1);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoActividad().getCuentoActual().getFrasesList().size() == blvs.getbEstadoActividad().getIndexCuento()) {
            blvs.getbEstadoActividad().setIndexCuento(0);
            return true;
        }
        return false;
    }
    
}
