package rational;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderSuscribe;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataSuscribe;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.ChangeRationalRoleGuard;
import rational.guards.InformationFlowGuard;
import rational.guards.PlanCancelationGuard;
import rational.guards.PlanExecutionGuard;
import rational.mapping.Believes;

public abstract class RationalAgent extends AgentBESA {

        public RationalAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA, ExceptionBESA {
            super(alias, state, setupRationalStructure(structAgent), passwd);
            
	}
        
        public RationalAgent(String alias, StateBESA state, StructBESA structAgent) throws KernelAgentExceptionBESA, ExceptionBESA {
            super(alias, state, setupRationalStructure(structAgent), 0.91f);
            
	}

        public RationalAgent(String alias, Believes believes, RationalRole role) throws KernelAgentExceptionBESA, ExceptionBESA {
            super(alias, new RationalState(believes, role), setupRationalStructure(), 0.91f);
            
            
        }    
        
        private static StructBESA setupRationalStructure() throws ExceptionBESA{
            StructBESA structure = new StructBESA();
            try {
                structure.addBehavior("InfoBehavior");
                structure.bindGuard("InfoBehavior", InformationFlowGuard.class);
                structure.addBehavior("RationalGuard");
                structure.bindGuard("RationalGuard", PlanExecutionGuard.class);
                structure.bindGuard("RationalGuard", ChangeRationalRoleGuard.class);
                structure.addBehavior("CancelationGuard");
                structure.bindGuard("CancelationGuard", PlanCancelationGuard.class);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            }    
                
            return structure;
        }
        
        private static StructBESA setupRationalStructure(StructBESA structure) throws ExceptionBESA{
            try {
             
                structure.addBehavior("InfoBehavior");
                structure.bindGuard("InfoBehavior", InformationFlowGuard.class);
                structure.addBehavior("RationalGuard");
                structure.bindGuard("RationalGuard", PlanExecutionGuard.class);
                structure.bindGuard("RationalGuard", ChangeRationalRoleGuard.class);
                structure.addBehavior("CancelationGuard");
                structure.bindGuard("CancelationGuard", PlanCancelationGuard.class);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            return structure;
        }
        
        @Override
        final public void setupAgent() {
            RationalState rationalState = (RationalState) getState();
            for (String serviceName : rationalState.getAsyncronicServices().keySet()) {
                for (Class guard: rationalState.getAsyncronicServices().get(serviceName)) {
                    try {
                        String agId = getAdmLocal().lookupSPServiceInDirectory(rationalState.getSPservices().get(serviceName));
                        AgHandlerBESA agh = getAdmLocal().getHandlerByAid(agId);
                        ServiceProviderDataSuscribe data = new ServiceProviderDataSuscribe(guard.getName(), ServiceProviderBESA.ASYNCHRONIC_SERVICE, serviceName, null);
                        EventBESA event = new EventBESA(GuardServiceProviderSuscribe.class.getName(), data);
                        event.setSenderAgId(getAid());
                        agh.sendEvent(event);
                    } catch (ExceptionBESA ex) {
                        Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            for (String serviceName : rationalState.getSyncronicServices().keySet()){
                for (Class guard: rationalState.getSyncronicServices().get(serviceName)) {
                    try {
                        String agId = getAdmLocal().lookupSPServiceInDirectory(serviceName);
                        AgHandlerBESA agh = getAdmLocal().getHandlerByAid(agId);
                        ServiceProviderDataSuscribe data = new ServiceProviderDataSuscribe(guard.getName(), ServiceProviderBESA.SYNCHRONIC_SERVICE, serviceName, null);
                        EventBESA event = new EventBESA(GuardServiceProviderSuscribe.class.getName(), data);
                        event.setSenderAgId(getAid());
                        agh.sendEvent(event);
                    } catch (ExceptionBESA ex) {
                        Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            rationalState.subscribeGuardToUpdate(PlanExecutionGuard.class.getName());
            setupRationalAgent();
        }

        @Override
        final public void shutdownAgent() {
            shutdownRationalAgent();
        }
        
        public abstract void setupRationalAgent();
        public abstract void shutdownRationalAgent();
        
//
}
