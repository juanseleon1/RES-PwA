package rational;

import BESA.Kernel.Agent.Event.DataBESA;
import java.io.Serializable;
import java.util.List;
import rational.mapping.Plan;
import rational.mapping.Task;

public class RationalRole   implements Serializable {

    private String roleName;
    private Plan rolePlan;

    public RationalRole() {
    }

    public RationalRole(String roleName, Plan rolePlan) {
        this.roleName = roleName;
        this.rolePlan = rolePlan;
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
    
    @Override
    public String toString() {
        return this.roleName;
    }

    public void setRolePlan(Plan rolePlan) {
        this.rolePlan = rolePlan;
    }
        
}
