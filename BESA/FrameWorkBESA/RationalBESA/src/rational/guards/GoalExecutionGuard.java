package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.RationalRole;
import rational.RationalState;
import rational.WorkerAgent;
import rational.WorkerState;
import rational.data.ExecutionPlanData;
import rational.mapping.Plan;

/**
 *
 * @author Andres
 */
public class GoalExecutionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        RationalState rst = (RationalState) this.getAgent().getState();
        for (int i = 0; i < rst.getRoleList().size(); i++) {
            RationalRole role = rst.getRoleList().get(i);
            Plan plan = role.getRolePlan();
            if (!plan.inExecution()) {
                try {
                    plan.setInExecution(true);
                    WorkerAgent worker = (WorkerAgent) rst.getWorker(role.getRoleName());
                    WorkerState state = (WorkerState) worker.getState();
                    state.reset();
                    state.setPlan(plan);
                    state.setTaskList(plan.getTaskList());                    
                    AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(worker.getAlias());
                    handler.sendEvent(new EventBESA(PlanExecutionGuard.class.getName(), new ExecutionPlanData(plan.getTaskList().get(0))));
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(GoalExecutionGuard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
