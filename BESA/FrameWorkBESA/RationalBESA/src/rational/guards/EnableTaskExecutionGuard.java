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
import rational.data.ExecutionPlanData;
import rational.mapping.Task;

/**
 *
 * @author fjroldan
 */
public class EnableTaskExecutionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            WorkerState state = (WorkerState) this.agent.getState();            
            state.upTaskIndex();
            Task task = state.getTaskList().get(state.getCurrentTaskIndex());
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(this.agent.getAlias());
            handler.sendEvent(new EventBESA(PlanExecutionGuard.class.getName(), new ExecutionPlanData(task)));            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(NotifyTaskFinalizedGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
