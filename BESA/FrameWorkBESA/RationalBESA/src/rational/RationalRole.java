package rational;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.List;
import rational.mapping.Plan;
import rational.mapping.Task;

public class RationalRole extends DataBESA{

    String roleName;
    Plan rolePlan;

    public RationalRole(String roleName, Plan rolePlan) {
        this.roleName = roleName;
        this.rolePlan = rolePlan;
    }

    public RationalRole(String roleName) {
        this.roleName = roleName;
        this.rolePlan = new Plan();
    }

    public Plan getRolePlan() {
        return rolePlan;
    }

    public String getRoleName() {
        return roleName;
    }
    
    public void addTask(Task task){
        rolePlan.addTask(task);
    }
    
    public void addTask(Task task, List<Task> prevTasks){
        rolePlan.addTask(task, prevTasks);
    }

    @Override
    public String toString() {
        return this.roleName;
    }

    public void setRolePlan(Plan rolePlan) {
        this.rolePlan = rolePlan;
    }
    
    public void resetPlan(){
        this.rolePlan.reset();
    }
}
