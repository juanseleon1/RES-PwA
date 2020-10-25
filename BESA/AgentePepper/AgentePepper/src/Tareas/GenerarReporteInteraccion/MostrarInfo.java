/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.GenerarReporteInteraccion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class MostrarInfo extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MostrarInfo() {
        System.out.println("--- Task Mostrar Informacion Interaccion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mostrar Informacion Interaccion ---");
        ServiceDataRequest srb = null;
    
        infoServicio.put("SAY", "INFO INTERACCION");
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
        infoServicio.clear();
        
        infoServicio.put("SHOWIMG", "INFO INTERACCION");
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mostrar Informacion Interaccion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mostrar Informacion Interaccion ---");
    }
    
}
