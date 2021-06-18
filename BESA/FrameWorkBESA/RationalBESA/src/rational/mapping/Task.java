package rational.mapping;

/**
 *
 * @author andres
 */
public abstract class Task {

    protected enum STATE {
        INTERRUPTED,
        WAITING_FOR_EXECUTION,
        IN_EXECUTION,
        FINALIZED;
    }

    public Task() {
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }

    public void setTaskInExecution() {
        this.taskState = STATE.IN_EXECUTION;
    }

    public void setTaskWaitingForExecution() {
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }

    public void setTaskFinalized() {
        this.taskState = STATE.FINALIZED;
    }

    public boolean isInExecution() {
        return this.taskState == STATE.IN_EXECUTION;
    }

    public boolean isWaitingForExecution() {
        return this.taskState == STATE.WAITING_FOR_EXECUTION;
    }

    public boolean isFinalized() {
        return this.taskState == STATE.FINALIZED;
    }

    public boolean isInterrupted() {
        return this.taskState == STATE.INTERRUPTED;
    }
    protected STATE taskState;

    public void runTask(Believes believes) {
        if (!this.isFinalized() && this.checkFinish(believes)) {
            this.setTaskFinalized();
        } else {
            //colocar un switch case que decida que hacer para todos los posibles estados 
            //!this.isInExecution()= this.isInterrupted() || this.isWaitingForExecution()
//            if(!this.isInExecution()){
//                this.setTaskInExecution();
//                this.executeTask(believes);
//            }
            if (this.isInExecution()) {
                this.executeTask(believes);
            }
        }
    }

    //Verifica a partir de las creencias si la tarea ya termino(logro su cometido).
    public abstract boolean checkFinish(Believes believes);

    //Equivale a ejecutar un ciclo de la tarea
    public abstract void executeTask(Believes parameters);

    //Es invocado desde el que interrumpe la tarea. Es la ultima oportunidad de hacer algo antes de ser expropiada.
    public abstract void interruptTask(Believes believes);

    public abstract void cancelTask(Believes believes);
}
