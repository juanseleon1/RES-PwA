package rational.mapping;

/**
 * 
 * @author andres
 */
public abstract class Task {
    
    protected enum STATE{
        WAITING_FOR_EXECUTION,
        IN_EXECUTION,
        FINALIZED;
    }

    public Task() {
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }
    
    public void setTaskInExecution(){
        this.taskState = STATE.IN_EXECUTION;
    }
    
    public void setTaskWaitingForExecution(){
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }
    
    public void setTaskFinalized(){
        this.taskState = STATE.FINALIZED;
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
    
    protected STATE taskState;

    public void run(Believes believes){
        if(this.checkFinish(believes)){
            this.setTaskFinalized();
        }else{
            if(!this.isInExecution()){
                this.setTaskInExecution();
                this.executeTask(believes);
            }
        }
    }
    
    public abstract boolean checkFinish(Believes believes);
    
    public abstract void executeTask(Believes parameters);

    public abstract void interruptTask(Believes believes);

    public abstract void cancelTask(Believes believes);
}
