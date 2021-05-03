/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class InicializarBaile extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    //revisa el espacio para que no se choque
    public InicializarBaile() {
//        System.out.println("--- Task Cambiar Baile Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cambiar Baile ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        if (blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustobaile() > 0.5) {
            infoServicio.put("RADIO", 0.5);
            infoServicio.put("DISTANCIAMAX", 0.5);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(LocationServiceRequestType.SEARCHFREEZONE, infoServicio);
//            requestService(srb, blvs);

//            infoServicio.put("MOVETOX", blvs.getbEstadoRobot().getDistanciaX());
//            infoServicio.put("MOVETOY", blvs.getbEstadoRobot().getDistanciaY());
            infoServicio.put("MOVETOX", 5);
            infoServicio.put("MOVETOY", 5);
            srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, infoServicio);
            requestService(srb, blvs);

            HashMap<String, Object> hm = new HashMap<>();
            hm.put("SAY", "Kikin! Kikin! ¿Porque eres asi?");
            ServiceDataRequest data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
            requestService(data, blvs);
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Baile---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoInteraccion().isDesplazandose()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.STOPMOVEMENT, null);
            requestService(srb, blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoRobot().isLibreEntorno() && !blvs.getbEstadoInteraccion().isDesplazandose()) {
            return true;
        }
        return false;
    }

}
