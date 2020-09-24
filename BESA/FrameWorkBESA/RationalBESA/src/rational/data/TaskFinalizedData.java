/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author usuario
 */
public class TaskFinalizedData extends DataBESA {
    
    private int executionIndex;

    public TaskFinalizedData(int executionIndx) {
        this.executionIndex = executionIndx;
    }

    public int getExecutionIndex() {
        return executionIndex;
    }

    public void setExecutionIndex(int executionIndex) {
        this.executionIndex = executionIndex;
    }
    
}
