/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.InitRESPwA;
import RobotAgentBDI.ResPwaTask;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public abstract class EmotionalModel {
    public abstract void updateModel();
    public abstract void updtModelFromEvt(EmotionalData sd);
    public abstract Map<String,Object> filterFromEM(Map<String,Object> map);

    protected void sendAct(EmotionalData ed) throws ExceptionBESA{
        AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
        EventBESA sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),ed);
        handler.sendEvent(sensorEvtA);
    }
    
    public void requestService(ServiceDataRequest sdr)
    {
         try {
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
}
