package rational.mapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import rational.tasks.VoidTask;

/**
 *
 * @author andres
 */
public class Plan {

    HashMap<Task, List<Task>> graphPlan;
    HashMap<Task, List<Task>> dependencyGraph;
    List<Task> tasksInExecution;
    List<Task> tasksWaitingForExecution;
    List<Task> tasksInInterruption;
    Task initial;

    public Plan() {
        graphPlan = new HashMap<>();
        tasksInExecution = new LinkedList<>();
        tasksWaitingForExecution = new LinkedList<>();
        tasksInInterruption = new LinkedList<>();
        dependencyGraph = new HashMap<>();
        initial = new VoidTask();
        initial.setTaskFinalized();
        tasksInExecution.add(initial);
        graphPlan.put(initial, new LinkedList<Task>());
        dependencyGraph.put(initial, new LinkedList<Task>());
    }

    public HashMap<Task, List<Task>> getGraphPlan() {
        return graphPlan;
    }

    public HashMap<Task, List<Task>> getDependencyGraph() {
        return dependencyGraph;
    }

    public List<Task> getTasksInExecution() {
        return tasksInExecution;
    }

    public List<Task> getTasksWaitingForExecution() {
        return tasksWaitingForExecution;
    }

    public void setTasksWaitingForExecution(List<Task> tasksWaitingForExecution) {
        this.tasksWaitingForExecution = tasksWaitingForExecution;
    }

    public List<Task> getTasksInInterruption() {
        return tasksInInterruption;
    }

    public void setTasksInInterruption(List<Task> tasksInInterruption) {
        this.tasksInInterruption = tasksInInterruption;
    }

    public Set<Task> getTasks() {
        return graphPlan.keySet();
    }

    public void addTask(Task task) {
        List<Task> l = new LinkedList<>();
        l.add(initial);
        addTask(task, l);
    }

    public boolean addTask(Task task, List<Task> previousTask) {
        for (Task prevTask : previousTask) {
            if (!graphPlan.containsKey(prevTask)) {
                return false;
            }
        }
        graphPlan.put(task, new LinkedList<Task>());
        if (!dependencyGraph.containsKey(task)) {
            dependencyGraph.put(task, new LinkedList<Task>());
        }
        dependencyGraph.get(task).addAll(previousTask);
        for (Task prevTask : previousTask) {
            graphPlan.get(prevTask).add(task);
        }
        return true;
    }

    public boolean inExecution() {
        return !tasksInExecution.isEmpty();
    }

    public void reset(Believes believes) {
        tasksInExecution.clear();
        tasksWaitingForExecution.clear();
        for (Task task : this.graphPlan.keySet()) {
            if (!task.isInterrupted()) {
                task.setTaskWaitingForExecution();
                task.initTask(believes);
            } else {
                task.setTaskInExecution();
                tasksInExecution.add(task);
            }
        }
        if (tasksInExecution.isEmpty()) {
            tasksInExecution.add(initial);
        }
//        initial.setTaskFinalized();
    }

}
