package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
public class PlanCancelationGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        RationalState rst = (RationalState) this.getAgent().getState();
        if (rst.getMainRole() != null) {
            Plan plan = rst.getMainRole().getRolePlan();
            if (plan != null) {
                for (Task task : plan.getTasksInExecution()) {
                    if (task.isInExecution()) {
                        task.cancelTask(rst.getBelieves());
                        plan.getTasksInExecution().remove(task);
                    }else if(task.isFinalized()){
                        plan.getTasksInExecution().remove(task);
                    }
                    task.setTaskFinalized();
                }
            }
        }
    }
}
