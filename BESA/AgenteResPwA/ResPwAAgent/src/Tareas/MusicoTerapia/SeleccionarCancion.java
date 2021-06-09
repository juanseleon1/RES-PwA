/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import Personalizacion.Modelo.CromosomaCancion;
import Personalizacion.Modelo.ModeloSeleccion;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Preferenciaxcancion;
import ResPwAEntities.Preferenciaxcuento;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.Utils.ResPwAActivity;
import rational.mapping.Believes;
import Utils.ResPwaUtils;

import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCancion extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public SeleccionarCancion() {
//        System.out.println("--- Task Seleccionar Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        blvs.getbEstadoActividad().setActividadActual(ResPwAActivity.MUSICOTERAPIA);
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        blvs.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("SAY", "Estoy seleccionando una cancion, quiero que cantes conmigo.");
        ServiceDataRequest data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        ResPwaUtils.requestService(data, blvs);

        List<Preferenciaxcancion> canciones = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxcancionList();
        ModeloSeleccion<Preferenciaxcancion> modeloCancion = new ModeloSeleccion<Preferenciaxcancion>(canciones);
        Preferenciaxcancion cancionSelected = null;
        CromosomaCancion cromosoma = null;
        cromosoma = (CromosomaCancion) modeloCancion.selectCromosoma();

        if (cromosoma != null) {
            cancionSelected = cromosoma.getCancion();
            blvs.getbEstadoActividad().setCancionActual(cancionSelected);
        }

        if (!blvs.getbEstadoRobot().isStoryMode()) {
            blvs.getbEstadoRobot().setStoryMode(true);
        }
        hm = new HashMap<>();
        hm.put("SAY", "La canción seleccionada es " + cancionSelected.getCancion().getNombre());
        data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        ResPwaUtils.requestService(data, blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

        blvs.getbEstadoActividad().setCancionActual(null);
        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoActividad().getCancionActual() != null) {
            return true;
        }
        return false;
    }

}
