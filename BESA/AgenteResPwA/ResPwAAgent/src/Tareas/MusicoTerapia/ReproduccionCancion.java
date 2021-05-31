/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import Personalizacion.Modelo.CromosomaBaile;
import Personalizacion.Modelo.ModeloSeleccion;
import ResPwAEntities.Preferenciaxbaile;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import ResPwaUtils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ReproduccionCancion extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();
    private boolean envioVideo;
    private long start = -1;

    public ReproduccionCancion() {
//        System.out.println("--- Task Busqueda Cancion Iniciada ---");
        envioVideo = false;

    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Reproducir Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        List<Preferenciaxbaile> bailes = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxbaileList();
        System.out.println("BaileList");
        long now = System.currentTimeMillis();

//        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.CHECKANIMATIONFINISH, infoServicio);
//        ResPwaUtils.requestService(srb, blvs);
        if ((now - start > 3000 || start == -1) && !blvs.getbEstadoActividad().isEstaBailando()) {
            start = now;

            infoServicio = new HashMap<>();
            ModeloSeleccion<Preferenciaxbaile> modeloBaile = new ModeloSeleccion<Preferenciaxbaile>(bailes);
            Preferenciaxbaile baileSelected = null;
            CromosomaBaile cromosoma = null;
            cromosoma = (CromosomaBaile) modeloBaile.selectCromosoma();
            if (cromosoma != null) {
                baileSelected = cromosoma.getBaile();
                blvs.getbEstadoActividad().setBaileActual(baileSelected);
                infoServicio = new HashMap<>();
                infoServicio.put("TAGSDANCE", blvs.getbEstadoActividad().getBaileActual().getBaile().getNombre());
                infoServicio.put("FACTOR", null);
                ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
                ResPwaUtils.requestService(srb, blvs);
            }
        }

        if (!envioVideo) {
            String urlcancion = blvs.getbEstadoActividad().getCancionActual().getCancion().getUrl();
            infoServicio = new HashMap<>();
            infoServicio.put("SHOWVIDEO", urlcancion);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWVIDEO, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
            envioVideo = true;
        }

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            if (!blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.RETROTOPIC) && envioVideo) {
                ResPwaUtils.activateTopic(PepperTopicsNames.RETROTOPIC, believes);
                envioVideo = false;
            }
            blvs.getbEstadoRobot().setStoryMode(false);

            return true;
        } else {
            setTaskWaitingForExecution();
            return false;
        }
    }

}
