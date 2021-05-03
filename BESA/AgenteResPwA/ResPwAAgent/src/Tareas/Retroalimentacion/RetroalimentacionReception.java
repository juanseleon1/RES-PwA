/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Retroalimentacion;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import rational.mapping.Believes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author LaMafecitaBebeLean
 */
public class RetroalimentacionReception extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public RetroalimentacionReception() {
//        System.out.println("--- Task Revisar Perfil Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Modificar Preferencias ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        String retroalimentacion = blvs.getbEstadoInteraccion().getRespuestaDialogInput();
        List<String> resulset = Arrays.asList(retroalimentacion.split(" "));
        ServiceDataRequest srb = null;
        Double respuestaRetroalimentacion = -1.0;
        if (resulset != null){
            HashMap <String, Long> resultados = new HashMap<String, Long>();
            resultados.put("Bien", resulset.stream().filter(retroa -> retroa.equals("Bien")).count()*3);
            resultados.put("Regular", resulset.stream().filter(retroa -> retroa.equals("Regular")).count()*2);
            resultados.put("Mal", resulset.stream().filter(retroa -> retroa.equals("Mal")).count());
            Double resulRetroAlimentacion = Double.valueOf(resultados.get("bien") + resultados.get("Regular") + resultados.get("Mal") / resulset.size());
            if (resulRetroAlimentacion >= 2.5)
                respuestaRetroalimentacion = 1.0;
            if (resulRetroAlimentacion <= 2.5 && resulRetroAlimentacion >= 1.5)
                respuestaRetroalimentacion = 0.5;
            if (resulRetroAlimentacion < 1.5)
                respuestaRetroalimentacion = 0.0;
            infoServicio.put("FALTA SABER QUE METER ACA", respuestaRetroalimentacion);
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETBRIGHT, infoServicio);
            
        }
        requestService(srb,blvs);
        this.activateTopic(PepperTopicsNames.SALUDARTOPIC, parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Modificar Preferencias ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Modificar Preferencias ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        return false;
    }

}
