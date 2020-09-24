package rational.mapping;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andres
 */
public class Plan implements Serializable {

    private long planID;
    private int priority;
    private List<Task> taskList;
    private boolean inExecution;
    private List<String> resources;
    private String command;

    public Plan(List<Task> taskList, List<String> resources, String command) {
        this.taskList = taskList;
        this.resources = resources;
        this.command = command;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean inExecution() {
        return inExecution;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setInExecution(boolean inExecution) {
        this.inExecution = inExecution;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public long getPlanID() {
        return planID;
    }

    public void setPlanID(long planID) {
        this.planID = planID;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
