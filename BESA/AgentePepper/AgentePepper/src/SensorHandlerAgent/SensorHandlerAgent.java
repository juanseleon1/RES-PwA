/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import ServiceProviderAgent.ServiceProviderAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class SensorHandlerAgent extends AgentBESA {
public static String GetEmotionalInfoPeriodicGuard= "GetEmotionalInfoPeriodicGuard";
public static String GetOtherInfoPeriodicGuard= "GetOtherInfoPeriodicGuard";
public static String RequestInfoGuard= "RequestInfoGuard";
    public SensorHandlerAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias, new SensorHandlerState(), new StructBESA(), 0.96);
    }

    @Override
    public void setupAgent() {
        try {
            StructBESA struct=this.getStructAgent();
            struct.addBehavior("GetEmotionalInfoPeriodicGuard");
            struct.bindGuard(GetEmotionalInfoPeriodicGuard, RequestEmotionalInfoPeriodicGuard.class);
            struct.addBehavior("GetOtherInfoPeriodicGuard");
            struct.bindGuard(GetOtherInfoPeriodicGuard, RequestOtherInfoPeriodicGuard.class);
            struct.addBehavior("RequestInfoGuard");
            struct.bindGuard(RequestInfoGuard, GetInfoGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ServiceProviderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
