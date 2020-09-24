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
import rational.guards.EnableTaskExecutionGuard;
import rational.guards.InformationFlowGuard;
import rational.guards.PlanCancelationGuard;
import rational.guards.GoalExecutionGuard;
import rational.guards.NotifyTaskFinalizedGuard;
import rational.mapping.Believes;

public class RationalAgent extends AgentBESA {

    public RationalAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, state, structAgent, passwd);
        try {
            setupRationalStructure((RationalState) this.getState());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RationalAgent(String alias, StateBESA state, StructBESA structAgent) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, 0.91f);
        try {
            setupRationalStructure((RationalState) this.getState());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RationalAgent(String alias, Believes believes, RationalRole role) throws KernelAgentExceptionBESA {
        super(alias, new RationalState(believes, role), new StructBESA(), 0.91f);
        try {
            setupRationalStructure((RationalState) this.getState());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setupAgent() {
        RationalState rationalState = (RationalState) getState();
        for (String serviceName : rationalState.getAsyncronicServices().keySet()) {
            for (Class guard : rationalState.getAsyncronicServices().get(serviceName)) {
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
        for (String serviceName : rationalState.getSyncronicServices().keySet()) {
            for (Class guard : rationalState.getSyncronicServices().get(serviceName)) {
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
    }

    @Override
    public void shutdownAgent() {
    }

    private void setupRationalStructure(RationalState rst) throws ExceptionBESA {
        StructBESA structure = this.getStructAgent();
        structure.addBehavior("InfoBehavior");
        structure.bindGuard("InfoBehavior", InformationFlowGuard.class);
        structure.addBehavior("RationalGuard");
        structure.bindGuard("RationalGuard", GoalExecutionGuard.class);
        structure.bindGuard("RationalGuard", ChangeRationalRoleGuard.class);
        structure.addBehavior("CancelationGuard");
        structure.bindGuard("CancelationGuard", PlanCancelationGuard.class);
    }

    public boolean notifyTaskFinalized(String roleName) {
        try {
            ((RationalState) this.state).getWorker(roleName).sendEvent(new EventBESA(NotifyTaskFinalizedGuard.class.getName()));
            return true;
        } catch (KernelAgentExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean notifyEnableTaskExecution(String roleName) {
        try {
            ((RationalState) this.state).getWorker(roleName).sendEvent(new EventBESA(EnableTaskExecutionGuard.class.getName()));
            return true;
        } catch (KernelAgentExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean notifyInformationFlow() {
        try {
            sendEvent(new EventBESA(InformationFlowGuard.class.getName()));
            return true;
        } catch (KernelAgentExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
