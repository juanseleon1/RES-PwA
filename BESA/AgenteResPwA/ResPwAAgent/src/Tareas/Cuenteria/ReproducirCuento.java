/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import EmotionalAnalyzerAgent.Utils.*;
import ResPwAEntities.Cuento;
import ResPwAEntities.Frases;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalEvent;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ReproducirCuento extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();
    private TimeUnit unit;
    private long start = -1;

    public ReproducirCuento() {
//        System.out.println("--- Task Buscar Animaciones Iniciada ---");
        unit = TimeUnit.SECONDS;
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Buscar Animaciones ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        long now = System.currentTimeMillis();
        System.out.println("UNIT SECOND" + (now - start));
        System.out.println("START" + start);
        System.out.println("NOW" + now);

        if ((now - start > 3000 || start == -1) && !blvs.getbEstadoInteraccion().isEstaHablando()) {
            start = now;

            Cuento cuento = blvs.getbEstadoActividad().getCuentoActual().getCuento();

            infoServicio = new HashMap<>();
            infoServicio.put("SAY", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getContenido());
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);

            if (!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getUrlimagen().equals(" ")) {
                infoServicio = new HashMap<>();
                infoServicio.put("SHOWIMG", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getUrlimagen());
                srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
                ResPwaUtils.requestService(srb, blvs);
            }

            if (!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getAccion().equals(" ")) {
                infoServicio = new HashMap<>();
                infoServicio.put("TAGSDANCE", cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getAccion());
                srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
                ResPwaUtils.requestService(srb, blvs);
            }

            if (!cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getEmotionalevent().equals(" ")) {
                String emoEvt = cuento.getFrasesList().get(blvs.getbEstadoActividad().getIndexCuento()).getEmotionalevent();
                System.out.println("EVENTOS:");

                String[] emoPos = emoEvt.split("_");
                if (emoPos.length > 2) {
                    for (String emoPo : emoPos) {
                        System.out.println(emoPo);

                    }
                    EmotionalEvent evt = new EmotionalEvent(EmotionalSubjectType.getFromId(emoPos[0]).toString(), EmotionalEventType.getFromId(emoPos[1]).toString(), EmotionalObjectType.getFromId(emoPos[2]).toString());
                    blvs.getbEstadoRobot().processEmotionalEvent(evt);
                }

            } else {
                blvs.getbEstadoRobot().emotionalStateChanged();
            }
            blvs.getbEstadoActividad().setIndexCuento(blvs.getbEstadoActividad().getIndexCuento() + 1);
        }
        System.out.println("TOTAL FRASES" + blvs.getbEstadoActividad().getCuentoActual().getCuento().getFrasesList().size());
        System.out.println("VA EN ESTA FRASE: " + blvs.getbEstadoActividad().getIndexCuento());
        if (blvs.getbEstadoActividad().getCuentoActual().getCuento().getFrasesList().size() > blvs.getbEstadoActividad().getIndexCuento()) {
            setTaskWaitingForExecution();
        }

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Buscar Animaciones ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        System.out.println("TOTAL FRASES" + blvs.getbEstadoActividad().getCuentoActual().getCuento().getFrasesList().size());
        System.out.println("VA EN ESTA FRASE: " + blvs.getbEstadoActividad().getIndexCuento());
        if (!blvs.getbEstadoInteraccion().isEstaHablando() && blvs.getbEstadoActividad().getCuentoActual().getCuento().getFrasesList().size() == blvs.getbEstadoActividad().getIndexCuento()) {
            System.out.println("--- Se acabo ---");
            infoServicio = new HashMap<>();
            infoServicio.put("SAY", "El Fin. Cuentame si te gusto");
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
            infoServicio.put("SHOWIMG", "Placeholder");
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.HIDEIMG, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
            ResPwaUtils.activateTopic(PepperTopicsNames.RETROCUENTOTOPIC, believes);

            blvs.getbEstadoRobot().setStoryMode(false);

            return true;
        }
        return false;
    }

}
