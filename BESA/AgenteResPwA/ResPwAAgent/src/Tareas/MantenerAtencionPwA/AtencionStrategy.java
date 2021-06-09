/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.Utils.ResPwAStrategy;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import Tareas.AnimarElogiarPwA.OpcionesAnimar;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class AtencionStrategy implements ResPwAStrategy {

    private OpcionesAtencion opcion;

    @Override
    public void execStrategy(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        HashMap<String, Object> infoServicio = new HashMap<>();
        ServiceDataRequest srb = null;
        blvs.getbEstadoEmocionalPwA().setAtencion(0.4);
        blvs.getbEstadoEmocionalPwA().setRelajacion(0.6);   
        if (blvs.getbEstadoEmocionalPwA().getAtencion() >= 0.5 && blvs.getbEstadoEmocionalPwA().getRelajacion() < 0.5) {
            this.opcion = OpcionesAtencion.CHISTE;
            infoServicio.put("SAY", "¿Qué diferencia hay entre una pila y una suegra?");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);

            infoServicio = new HashMap<>();
            infoServicio.put("SAY", "Que la pila tiene un lado positivo");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            
            infoServicio = new HashMap<>();
            ResPwaUtils.requestService(srb, blvs);
            infoServicio.put("SAY", "Jajajajaja");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        } else if (blvs.getbEstadoEmocionalPwA().getAtencion() >= 0.5 && blvs.getbEstadoEmocionalPwA().getRelajacion() >= 0.5) {
            this.opcion = OpcionesAtencion.DATOCURIOSO;
            infoServicio.put("SAY", "En la Antigua Grecia la esperanza de vida era muy alta, hasta el punto de que muchas personas vivían hasta los 100 años o más");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        } else if (blvs.getbEstadoEmocionalPwA().getAtencion() < 0.5 && blvs.getbEstadoEmocionalPwA().getRelajacion() >= 0.5) {
            this.opcion = OpcionesAtencion.SILBAR;
            infoServicio.put("EMOTION", "SILBAR");
            srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.PLAYANIMATION, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        } else if (blvs.getbEstadoEmocionalPwA().getAtencion() < 0.5 && blvs.getbEstadoEmocionalPwA().getRelajacion() < 0.5) {
            this.opcion = OpcionesAtencion.LLAMARPWA;
            infoServicio.put("SAY", blvs.getbPerfilPwA().getPerfil().getNombre() + ", no te distraigas");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        }
    }

    public OpcionesAtencion getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionesAtencion opcion) {
        this.opcion = opcion;
    }

    @Override
    public boolean isFinished(Believes b) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) b;
        if(this.opcion.equals(OpcionesAtencion.SILBAR)){
           return blvs.getbEstadoInteraccion().isEstaMoviendo();
        }
        return blvs.getbEstadoInteraccion().isEstaHablando();
    }
}
