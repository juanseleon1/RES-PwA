/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoFuturo.Tareas.ModificarPreferencias;

import Tareas.Test.*;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Task;

/**
 *
 * @author LaMafecitaBebeLean
 */
public class ModificarPreferencias extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public ModificarPreferencias() {
//        System.out.println("--- Task Revisar Perfil Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Modificar Preferencias ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        String preferencia = blvs.getbEstadoInteraccion().getRespuestaPreferencia();
        String resulset[] = preferencia.split(" ");
        ServiceDataRequest srb = null;
        if (resulset[1].equals("brightness")){
            double brilloRobot = blvs.getbEstadoRobot().getBrilloRobot();
            if (resulset[0].equals( "increase")){
                brilloRobot += 20;
                blvs.getbEstadoRobot().setBrilloRobot(brilloRobot);
               
            }else{
                brilloRobot -= 20;
                blvs.getbEstadoRobot().setBrilloRobot(brilloRobot);
                
            }
            infoServicio.put("SETTABLETBRIGHT", brilloRobot);
            srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETBRIGHT, infoServicio);
            
        }
            
        if (resulset[1].equals("volume")){
            int volumenRobot = blvs.getbEstadoRobot().getVolumenVoz();
            if (resulset[0].equals("increase")){
                volumenRobot += 20;
                blvs.getbEstadoRobot().setVolumenVoz(volumenRobot);
            }else{
                volumenRobot -=20;
                blvs.getbEstadoRobot().setVolumenVoz(volumenRobot);
            }
            infoServicio.put("SETSAYVOLUME",volumenRobot );
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SETSAYVOLUME, infoServicio);
        }
        
        
        ResPwaUtils.requestService(srb,blvs);
        ResPwaUtils.activateTopic(PepperTopicsNames.SALUDARTOPIC, parameters);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Modificar Preferencias ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Modificar Preferencias ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        return false;
    }

}
