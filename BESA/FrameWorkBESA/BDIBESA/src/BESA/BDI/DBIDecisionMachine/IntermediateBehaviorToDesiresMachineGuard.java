/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.BDI.DBIDecisionMachine;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.ChangeRationalRoleGuard;

/**
 *
 * @author proyecto
 */
public class IntermediateBehaviorToDesiresMachineGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        AgentBDI agentBDI = (AgentBDI) this.agent;
        StateBDI stateBDI = (StateBDI) agentBDI.getState();
        if (stateBDI.isEndedTheDesiresMachine()){
            try {
                EventBESA eventBesa = new EventBESA(DesireToIntentionInstantiationGuard.class.getName(), null);
                AgHandlerBESA agHandlerBESA;
                agHandlerBESA = agentBDI.getAdmLocal().getHandlerByAlias(agentBDI.getAlias());
                agHandlerBESA.sendEvent(eventBesa);
                stateBDI.setEndedTheDesiresMachine(false);
               
            } catch (ExceptionBESA ex) {
               ReportBESA.error(ex.getMessage());
            }
        }else {
            stateBDI.setInQueue(true);
        }
    }
    
}
