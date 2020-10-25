/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarCancion;

import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class BusquedaCancionYoutube extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public BusquedaCancionYoutube() {
        System.out.println("--- Task Busqueda Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        YTUtils yt = new YTUtils();
        String urlcancion = yt.searchYTVideo(blvs.getbEstadoActividad().getCancionActual().getNombre());
        infoServicio.put("SHOWVIDEO", urlcancion);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWVIDEO, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Busqueda Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Busqueda Cancion ---");
    }
    
}
