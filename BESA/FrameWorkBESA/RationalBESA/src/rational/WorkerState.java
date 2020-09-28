/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational;

import BESA.Kernel.Agent.StateBESA;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author usuario
 */
public class WorkerState extends StateBESA {

    private Plan plan;
    private Believes believes;
    private int currentTaskIndex;
    private List<Task> taskList;
    private String ownerAgentAlias;
    
    public WorkerState() {        
    }
    
    public WorkerState(Believes believes, String ownerAgentAlias) {
        this.believes = believes;
        this.ownerAgentAlias = ownerAgentAlias;
        currentTaskIndex = 0;
    }
    
    public void reset() {
        plan = null;
        taskList = null;
        currentTaskIndex = 0;
    }
        
    public int upTaskIndex() {
        currentTaskIndex++;
        if (currentTaskIndex > taskList.size() - 1) {
            currentTaskIndex = 0;
        }
        return currentTaskIndex;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public void setCurrentTaskIndex(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public Believes getBelieves() {
        return believes;
    }

    public void setBelieves(Believes believes) {
        this.believes = believes;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getOwnerAgentAlias() {
        return ownerAgentAlias;
    }

    public void setOwnerAgentAlias(String ownerAgentAlias) {
        this.ownerAgentAlias = ownerAgentAlias;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
        
}
