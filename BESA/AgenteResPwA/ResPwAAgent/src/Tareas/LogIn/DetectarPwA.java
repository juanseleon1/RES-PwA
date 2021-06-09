/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;


import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class DetectarPwA extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>(); 
    
    public DetectarPwA() {
//        System.out.println("--- Task Detectar PwA Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Detectar PwA ---");
        ServiceDataRequest srb = null;
        
        //si existe o no -> si no tiene el id de la BD es porque no ha interactuado con el robot
        /*if(Existeid) {
            infoServicio.put("GETIDROBOT", "");
            srb = ServiceRequestBuilder.buildRequest(RobotServiceRequestType.IDROBOT, infoServicio);
            ResPwaUtils.requestService(srb);
        } else {
            infoServicio.put("DETECTPWA", blvs.getbPerfilPwA().getNombre()+" "+blvs.getbPerfilPwA().getApellidos());
            srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.DETECTNEWFACE, infoServicio);
            ResPwaUtils.requestService(srb);
        }
        toca recibir el id*/
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //hay que cambiar este parametro
        infoServicio.put("DETECTPWA", blvs.getbPerfilPwA().getPerfil().getCedula());
        srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.DETECTNEWFACE, infoServicio);
        ResPwaUtils.requestService(srb,blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Detectar PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Detectar PwA ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDetectaPwA()) {
            System.out.println("///////////////TF//////////////");
            return true;
        }
        return false;
    }
    
    
}
