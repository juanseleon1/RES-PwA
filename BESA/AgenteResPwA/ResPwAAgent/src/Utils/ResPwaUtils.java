/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Kernel.System.SystemExceptionBESA;
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public class ResPwaUtils{

    protected boolean expropiated = false;
    public ResPwaUtils() {
        expropiated = false;
    }

    public static void requestService(ServiceDataRequest sdr, RobotAgentBelieves blvs) {
        try {
            blvs.getbEstadoRobot().updateEmotionalVariables();
            String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(sdr.getServiceName());
            String SHID = AdmBESA.getInstance().searchAidByAlias(InitRESPwA.aliasSPAgent);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            EventBESA evt = new EventBESA(ActivateAsynchronousServiceGuard.class.getName(), sdr);
            evt.setSenderAgId(SHID);
            agH.sendEvent(evt);
        } catch (SystemExceptionBESA ex) {
            Logger.getLogger(ResPwaUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ResPwaUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public static void activateTopic(PepperTopicsNames topic, Believes parameters) {

        HashMap<String, Object> infoServicio = new HashMap<>();
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("TOPICNAME", topic.getTopic());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTIVATECONVTOPIC, infoServicio);
        requestService(srb, blvs);

    }

    public static void deactivateTopic(PepperTopicsNames topic, Believes parameters) {
        HashMap<String, Object> infoServicio = new HashMap<>();
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        infoServicio.put("TOPICNAME", topic.getTopic());
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.DEACTCONVTOPIC, infoServicio);
        requestService(srb, blvs);
    }

    public static void updateEmo(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
            blvs.getbEstadoRobot().updateEmotionalVariables();
    }

    public static void requestService(ServiceDataRequest sdr)
    {
         try {
            String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(sdr.getServiceName());
            String SHID = AdmBESA.getInstance().searchAidByAlias(InitRESPwA.aliasSPAgent);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            EventBESA evt= new EventBESA(ActivateAsynchronousServiceGuard.class.getName(), sdr);
            evt.setSenderAgId(SHID);
            agH.sendEvent(evt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ResPwaUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
