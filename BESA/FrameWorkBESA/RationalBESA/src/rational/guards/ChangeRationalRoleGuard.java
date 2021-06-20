package rational.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import rational.RationalRole;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
//Guarda para el manejo de la expropiacion
public class ChangeRationalRoleGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        RationalState state = (RationalState) this.getAgent().getState();
        RationalRole newrole = (RationalRole) ebesa.getData();

        if (state.getMainRole() != null && !state.getMainRole().getRoleName().equals(((RationalRole) ebesa.getData()).getRoleName())) {
            //System.out.println("Intentando cambiar de rol a " + newrole.getRoleName());
            if (state.getMainRole() != null) {
                Plan plan = state.getMainRole().getRolePlan();
                if (plan != null) {
                    for (Task task : plan.getTasksInExecution()) {
                        if (task.isInExecution()) {
                            if (!task.checkFinish(state.getBelieves())) {
                                task.setTaskInterrupted();
                                task.interruptTask(state.getBelieves());
                                plan.getTasksInExecution().remove(task);
                                plan.getTasksInInterruption().add(task);
                            } else {
                               plan.getTasksInExecution().remove(task);
                               plan.getTasksWaitingForExecution().add(task);
                            }

                        } else if (task.isFinalized()) {
                            plan.getTasksInExecution().remove(task);
                        }
                        task.setTaskFinalized();
                    }
                }
            }
            newrole.resetPlan(state.getBelieves());
            state.setMainRole(newrole);
        } else if (state.getMainRole() == null) {
            //System.out.println("Insertando nuevo plan: " + newrole.getRoleName());
            newrole.resetPlan(state.getBelieves());
            state.setMainRole(newrole);
        } else if (!state.getMainRole().getRolePlan().inExecution()) {
            //System.out.println("Reseteando plan: " + state.getMainRole().getRoleName());
            state.getMainRole().getRolePlan().reset(state.getBelieves());
        }
    }
}
