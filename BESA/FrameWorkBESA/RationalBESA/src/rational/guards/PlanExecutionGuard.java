/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import rational.WorkerState;
import rational.data.ExecutionPlanData;
import rational.mapping.Task;

/**
 *
 * @author usuario
 */
public class PlanExecutionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        WorkerState workerState = (WorkerState) this.getAgent().getState();
        ExecutionPlanData executionPlanData = (ExecutionPlanData) event.getData();                
        Task task = executionPlanData.getTask();
        task.setWorkerAlias(this.agent.getAlias());
        task.setExecutionIndex(workerState.getCurrentTaskIndex());
        task.executeTask(workerState.getBelieves());  
    }
    
}
