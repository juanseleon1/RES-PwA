/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import ResPwaUtils.Imagen;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class MostrarFotos extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public MostrarFotos() {
//        System.out.println("--- Task Mostrar Fotos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Mostrar Fotos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        List<Imagen> listImgs = blvs.getImgsPerfil();
        List<String> listUrls= listImgs.stream().map(e-> e.getUrl()).collect(Collectors.toList());
        infoServicio.put("SHOWIMG", listUrls);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Mostrar Fotos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Mostrar Fotos ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        System.out.println("--- Check Finish Task Mostrar Fotos ---");
        return false;
    }
    
}
