/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public abstract class ResPwaTask extends Task{
    
    public void requestService(ServiceDataRequest sdr, RobotAgentBelieves blvs)
    {
         try {
             if(sdr.getParams()!=null)
             {
                 Map<String, Object> map = blvs.getbEstadoEmocionalRobot().getEms().modulateAction(sdr);
                sdr.setParams((HashMap<String, Object>) map);
             }
            String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(sdr.getServiceName());
            String SHID = AdmBESA.getInstance().searchAidByAlias(InitRESPwA.aliasSPAgent);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            EventBESA evt= new EventBESA(ActivateAsynchronousServiceGuard.class.getName(), sdr);
            evt.setSenderAgId(SHID);
            agH.sendEvent(evt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ResPwaTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void activateTopic(PepperTopicsNames topic, Believes parameters){
        
        HashMap<String, Object> infoServicio = new HashMap<>();
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("TOPICNAME", topic.getTopic());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTIVATECONVTOPIC, infoServicio);
        requestService(srb, blvs);
                
    }
    
    public void deactivateTopic(PepperTopicsNames topic, Believes parameters){
        HashMap<String, Object> infoServicio = new HashMap<>();
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("TOPICNAME", topic.getTopic());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.DEACTCONVTOPIC, infoServicio);
        requestService(srb, blvs);
    }
    
}
