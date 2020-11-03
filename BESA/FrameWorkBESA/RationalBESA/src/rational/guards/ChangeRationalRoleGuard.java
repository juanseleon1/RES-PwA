package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.RationalRole;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
public class ChangeRationalRoleGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        RationalState state = (RationalState)this.getAgent().getState();
        RationalRole newrole = (RationalRole)ebesa.getData();

        if(state.getMainRole()!=null && !state.getMainRole().getRoleName().equals(((RationalRole)ebesa.getData()).getRoleName())){            

            //System.out.println("Intentando cambiar de rol a " + newrole.getRoleName());
            if (state.getMainRole() != null) {
            Plan plan = state.getMainRole().getRolePlan();
                if (plan != null) {
                    for (Task task : plan.getTasksInExecution()) {
                        if (task.isInExecution()) {
                            task.cancelTask(state.getBelieves());
                            plan.getTasksInExecution().remove(task);
                        }else if(task.isFinalized()){
                            plan.getTasksInExecution().remove(task);
                        }
                        task.setTaskFinalized();
                    }
                }
            }
            newrole.resetPlan();
            state.setMainRole(newrole);
        }else if(state.getMainRole()==null){
             //System.out.println("Insertando nuevo plan: " + newrole.getRoleName());
            newrole.resetPlan();
            state.setMainRole(newrole);
        }else if (!state.getMainRole().getRolePlan().inExecution()){
            //System.out.println("Reseteando plan: " + state.getMainRole().getRoleName());
            state.getMainRole().getRolePlan().reset();
        }
    }
    
}
