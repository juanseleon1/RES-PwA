/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ActivarKaraoke;

import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class CambiarVelocidadMov extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();

    public CambiarVelocidadMov() {
       // System.out.println("--- Task Cambiar Velocidad Movimientos Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cambiar Velocidad Movimientos ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("TAGSKARAOKE", blvs.getbEstadoActividad().getCancionActual().getTagsList());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Velocidad Movimientos ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Velocidad Movimientos ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
