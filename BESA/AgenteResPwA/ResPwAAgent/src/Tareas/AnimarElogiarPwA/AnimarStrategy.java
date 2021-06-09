/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.Utils.ResPwAStrategy;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
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
        HashMap<String, Object> infoServicio = new HashMap<>();
        ServiceDataRequest srb = null;

        if (blvs.getbEstadoEmocionalPwA().getEmocionPredominante() > 0.5) {
            this.opcion = OpcionesAnimar.FRASEELOGIANTE;
            infoServicio.put("SAY", "Mi mundo se ilumina cuando estás cerca, eres lo más lindo, tierno y hermoso que ocupa mi corazón");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        } else if (blvs.getbEstadoEmocionalPwA().getEmocionPredominante() < -0.5) {
            this.opcion = OpcionesAnimar.PREGUNTAEMPATICA;
            infoServicio.put("SAY", "¿Como te sientes?");
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        }
    }

    public OpcionesAnimar getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionesAnimar opcion) {
        this.opcion = opcion;
    }

    @Override
    public boolean isFinished(Believes b) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) b;
        return blvs.getbEstadoInteraccion().isEstaHablando();
    }

}
