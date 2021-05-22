/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import ResPwAEntities.Familiar;
import ResPwAEntities.Tags;
import ResPwaUtils.AudioUtils;
import ResPwaUtils.Imagen;
import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ReproduccionCancion extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public ReproduccionCancion() {
//        System.out.println("--- Task Busqueda Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;

            String urlcancion = blvs.getbEstadoActividad().getCancionActual().getUrl();
            infoServicio.put("SHOWVIDEO", urlcancion);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWVIDEO, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Busqueda Cancion ---");

    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Busqueda Cancion ---");

    }

    @Override
    public boolean checkFinish(Believes believes) {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoInteraccion().isConfirmacionRepDisp();
        
    }

}
