/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.WorkerState;
import rational.data.CheckExecutionData;

/**
 *
 * @author usuario
 */
public class NotifyTaskFinalizedGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            WorkerState state = (WorkerState) this.agent.getState();
            CheckExecutionData data = new CheckExecutionData(state.getPlan()); 
            AgHandlerBESA handlerByAlias = AdmBESA.getInstance().getHandlerByAlias(state.getOwnerAgentAlias());
            EventBESA evt = new EventBESA(InformationFlowGuard.class.getName(), data);
            handlerByAlias.sendEvent(evt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(NotifyTaskFinalizedGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
