/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarCancion;

import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class RecibirRetroalimentacion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public RecibirRetroalimentacion() {
        System.out.println("--- Task Recibir Retroalimentacion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Retroalimentacion ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        //buscar texto
        infoServicio.clear();
        infoServicio.put("SAY", "Texto");
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        //buscar url
        infoServicio.clear();
        infoServicio.put("SHOWIMG", "url");
        ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Retroalimentacion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Retroalimentacion ---");
    }
    
}
