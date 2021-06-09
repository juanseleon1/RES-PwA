/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SalirDeSuspension;

/**
 *
 * @author mafegarces
 */
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class DesuspenderRobot extends Task{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public DesuspenderRobot() {
//        System.out.println("--- Task Suspender Robot Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Desuspender Robot ---");
        //buscar texto
        infoServicio.put("SAY", "Ya me dieron ganas de despertarme");
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        ResPwaUtils.requestService(srb, (RobotAgentBelieves) parameters);
        infoServicio = new HashMap<>();
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
               
        srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.WAKEUP, null);
        ResPwaUtils.requestService(srb,blvs);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task DesSuspender Robot ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isSistemaSuspendido()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, null);
            ResPwaUtils.requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Desuspender Robot ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isSistemaSuspendido()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SUSPEND, null);
            ResPwaUtils.requestService(srb,blvs);
        }        
    }

    @Override
    public boolean checkFinish(Believes believes) {
                

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return !blvs.getbEstadoInteraccion().isSistemaSuspendido();
    }
    
}
