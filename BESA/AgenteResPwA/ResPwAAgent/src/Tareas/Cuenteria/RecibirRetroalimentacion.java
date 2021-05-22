/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaUtils;
import rational.mapping.Believes;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class RecibirRetroalimentacion extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public RecibirRetroalimentacion() {
//        System.out.println("--- Task Recibir Retroalimentacion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Retroalimentacion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;

        if (blvs.getbEstadoInteraccion().getRetroalimentacionValue() == null) {

            infoServicio = new HashMap<>();
            infoServicio.put("SAY", "comenzar");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.FORCEINPUT, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        } else {
            String retroalimentacion = blvs.getbEstadoInteraccion().getRetroalimentacionValue();
            List<String> resulset = Arrays.asList(retroalimentacion.split(" "));
            ServiceDataRequest srb = null;
            Double respuestaRetroalimentacion = -1.0;
            if (resulset != null) {
                HashMap<String, Long> resultados = new HashMap<String, Long>();
                resultados.put("Bien", resulset.stream().filter(retroa -> retroa.equals("Bien")).count() * 3);
                resultados.put("Regular", resulset.stream().filter(retroa -> retroa.equals("Regular")).count() * 2);
                resultados.put("Mal", resulset.stream().filter(retroa -> retroa.equals("Mal")).count());
                Double resulRetroAlimentacion = Double.valueOf(resultados.get("bien") + resultados.get("Regular") + resultados.get("Mal") / resulset.size());
                if (resulRetroAlimentacion > 2.5) {
                    respuestaRetroalimentacion = 1.0;
                }
                if (resulRetroAlimentacion <= 2.5 && resulRetroAlimentacion >= 1.5) {
                    respuestaRetroalimentacion = 0.5;
                }
                if (resulRetroAlimentacion < 1.5) {
                    respuestaRetroalimentacion = 0.0;
                }

                blvs.feedbackActivity(respuestaRetroalimentacion);

            }
        }

        //ResPwaUtils.requestService(srb,blvs);
        //ResPwaUtils.activateTopic(PepperTopicsNames.SALUDARTOPIC, parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Retroalimentacion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            ResPwaUtils.requestService(srb, blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Retroalimentacion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            ResPwaUtils.requestService(srb, blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoInteraccion().isRecibirRespuestaPwA()) {
            return true;
        }
        return false;
    }

}
