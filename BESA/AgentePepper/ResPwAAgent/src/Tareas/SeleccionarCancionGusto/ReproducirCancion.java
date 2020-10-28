/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCancionGusto;

import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class ReproducirCancion extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public ReproducirCancion() {
        System.out.println("--- Task Reproducir Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Reproducir Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        String urlcancion = YTUtils.searchYTVideo(blvs.getbEstadoActividad().getCancionActual().getNombre());
        infoServicio.put("SHOWVIDEO", urlcancion);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWVIDEO, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Reproducir Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Reproducir Cancion ---");
    }
    
}
