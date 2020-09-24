package rational.mapping;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.data.TaskFinalizedData;
import rational.guards.GoalExecutionGuard;
import rational.guards.NotifyTaskFinalizedGuard;

/**
 * 
 * @author andres
 */
public abstract class Task  implements Serializable{
    
    private String workerAlias;
    
    private int executionIndex;
    
    protected enum STATE{
        WAITING_FOR_EXECUTION,
        IN_EXECUTION,
        FINALIZED;
    }

    public Task() {
        executionIndex = 0;
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }
    
    public void setTaskInExecution(){
        this.taskState = STATE.IN_EXECUTION;
    }
    
    public void setTaskWaitingForExecution(){
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }
    
    public boolean isInExecution(){
        return this.taskState == STATE.IN_EXECUTION;
    }
    
    public boolean isWaitingForExecution(){
        return this.taskState == STATE.WAITING_FOR_EXECUTION;
    }
    
    public boolean isFinalized(){
        return this.taskState == STATE.FINALIZED;
    }
    
    STATE taskState;
        
    public abstract void executeTask(Believes parameters);

    public abstract void interruptTask(Believes believes);

    public abstract void cancelTask(Believes believes);
    
    public void taskFinalized() {
        try {
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(workerAlias);
            handler.sendEvent(new EventBESA(NotifyTaskFinalizedGuard.class.getName()));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GoalExecutionGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getWorkerAlias() {
        return workerAlias;
    }

    public void setWorkerAlias(String workerAlias) {
        this.workerAlias = workerAlias;
    }
    
    public int getExecutionIndex() {
        return executionIndex;
    }

    public void setExecutionIndex(int executionIndex) {
        this.executionIndex = executionIndex;
    }
        
}
