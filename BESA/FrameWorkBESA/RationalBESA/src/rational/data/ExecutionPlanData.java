/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.data;

import BESA.Kernel.Agent.Event.DataBESA;
import rational.mapping.Task;

/**
 *
 * @author usuario
 */
public class ExecutionPlanData extends DataBESA {
    
    private Task task;

    public ExecutionPlanData() {
    }

    public ExecutionPlanData(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
}
