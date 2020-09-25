/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProviderAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class ServiceProviderAgent extends AgentBESA {
    public static String RequestActivityGuard= "RequestActivityGuard";
    public ServiceProviderAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias,new ServiceProviderState(), new StructBESA(), 0.96);
    }

    @Override
    public void setupAgent() {
        try {
            StructBESA struct=this.getStructAgent();
            struct.addBehavior("RequestActivityGuard");
            struct.bindGuard(RequestActivityGuard, RequestActivityGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ServiceProviderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
