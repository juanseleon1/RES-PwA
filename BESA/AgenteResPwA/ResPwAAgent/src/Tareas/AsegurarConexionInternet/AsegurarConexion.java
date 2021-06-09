/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AsegurarConexionInternet;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class AsegurarConexion extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public AsegurarConexion() {
//        System.out.println("--- Task AsegurarConexion PwA Iniciada ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        System.out.println("--- Check Finish AsegurarConexion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoRobot().isConexionInternet();
    }

    @Override
    public void executeTask(Believes parameters) {
        //System.out.println("--- Execute Task AsegurarConexion ---");
        System.out.println("----REVISAR CONEXIÓN INTERNET ROBOT----");
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task AsegurarConexion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setTiempoSinConexionInternet(0);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task AsegurarConexion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setTiempoSinConexionInternet(0);
    }
    
}
