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
        
        //modificar reglas
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.ADIVINANZA;
            infoServicio.put("SAY", "¿Cuál es el animal que come con las patas?");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
            //tiempo
            infoServicio.put("SAY", "El pato");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.CHISTE;
            infoServicio.put("SAY", "¿Que le dice una foca a su madre?");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
            //tiempo
            infoServicio.put("SAY", "Ai lof iu moder foca");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.CONSEJO;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.DATOCURIOSO;
            infoServicio.put("SAY", "");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.FRASEELOGIANTE;
            infoServicio.put("SAY", "Wow hoy estas muy lindo");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 0 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 0){
            this.opcion = OpcionesAnimar.PREGUNTAEMPATICA;
            infoServicio.put("SAY", "¿Como te sientes?");
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
