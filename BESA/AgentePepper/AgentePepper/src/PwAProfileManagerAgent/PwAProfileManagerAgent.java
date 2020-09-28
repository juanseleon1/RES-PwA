/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PwAProfileManagerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import EmotionalAnalyzerAgent.ProcessEmotionGuard;
import ServiceProviderAgent.ServiceProviderAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PwAProfileManagerAgent extends AgentBESA {

    public static String UpdateProfileGuard= "UpdateProfileGuard";
    public PwAProfileManagerAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias, new PwAProfileManagerState(), new StructBESA(), 0.96);
    }
    @Override
    public void setupAgent() {
        try {
            StructBESA struct=this.getStructAgent();
            struct.addBehavior("UpdateProfileGuard");
            struct.bindGuard(UpdateProfileGuard, UpdateProfileGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ServiceProviderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
