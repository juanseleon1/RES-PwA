/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import Personalizacion.Modelo.CromosomaBaile;
import Personalizacion.Modelo.ModeloSeleccion;
import ResPwAEntities.Preferenciaxbaile;
import ResPwAEntities.Preferenciaxcuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class InicializarBaile extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    //revisa el espacio para que no se choque
    public InicializarBaile() {
//        System.out.println("--- Task Cambiar Baile Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cambiar Baile ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //buscar baile
        List<Preferenciaxbaile> bailes = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxbaileList();
        System.out.println("BaileList");
        ModeloSeleccion<Preferenciaxbaile> modeloBaile = new ModeloSeleccion<Preferenciaxbaile>(bailes);

        Preferenciaxbaile baileSelected = null;
        CromosomaBaile cromosoma = null;
        cromosoma = (CromosomaBaile) modeloBaile.selectCromosoma();
        if (cromosoma != null) {
            baileSelected = cromosoma.getBaile();
            blvs.getbEstadoActividad().setBaileActual(baileSelected.getBaile());
            infoServicio.put("TAGSDANCE", blvs.getbEstadoActividad().getBaileActual().getNombre());
            infoServicio.put("FACTOR", null);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Baile---");
    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoActividad().getBaileActual() != null;
        
    }

}
