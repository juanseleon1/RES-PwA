/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataRequest;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import ServiceAgentPepper.RobotProviderAgent;
import Tareas.Bailar.InicializarBaile;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Task;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public abstract class ResPwaTask extends Task{
    public void requestService(String serviceToUse)
    {
         try {
            String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(serviceToUse);
            String SHID = AdmBESA.getInstance().searchAidByAlias(RunAgentePepper.aliasSHAAgent);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            ServiceProviderDataRequest spdr= new ServiceProviderDataRequest(SHID,serviceToUse, new SPServiceDataRequest(GetInfoGuard.class.getName(), SensorData.class.getName()));
            EventBESA evt= new EventBESA(ActivateAsynchronousServiceGuard.class.getName(), spdr);
            evt.setSenderAgId(SHID);
            agH.sendEvent(evt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ResPwaTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
