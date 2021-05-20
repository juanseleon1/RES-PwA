/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class AnimarStrategy implements ResPwAStrategy {
    
    private OpcionesAnimar opcion;
    
    @Override
    public void execStrategy(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.ADIVINANZA;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.CHISTE;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.CONSEJO;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.DATOCURIOSO;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.FRASEELOGIANTE;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 || blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.PREGUNTAEMPATICA;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    public OpcionesAnimar getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionesAnimar opcion) {
        this.opcion = opcion;
    }

    @Override
    public void execStrategy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
