/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderRequest;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataRequest;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import ServiceAgentPepper.RobotProviderAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class getBatteryPeriodicGuard extends PeriodicGuardBESA{

    @Override
    public void funcPeriodicExecGuard(EventBESA ebesa) {
        try {
            System.out.println("getBatteryPeriodicGuard Event Received: "+ebesa);
            String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servBateria);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            ServiceProviderDataRequest spdr= new ServiceProviderDataRequest(this.getAgent().getAid(),RobotProviderAgent.servBateria, new SPServiceDataRequest(GetInfoGuard.class.getName(), SensorData.class.getName()));
            EventBESA evt= new EventBESA(GuardServiceProviderRequest.class.getName(), spdr);
            evt.setSenderAgId(this.getAgent().getAid());
            agH.sendEvent(evt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(getBatteryPeriodicGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
