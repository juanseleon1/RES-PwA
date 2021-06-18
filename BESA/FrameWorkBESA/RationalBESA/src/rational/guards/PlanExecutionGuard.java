package rational.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import java.util.Iterator;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
public class PlanExecutionGuard extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        RationalState rst = (RationalState) this.getAgent().getState();
        if (rst.getMainRole() != null) {
            Plan plan = rst.getMainRole().getRolePlan();
            for (Task task : plan.getTasksInExecution()) {
                //Si la tarea finalizo en un ciclo anterior (dentro del llamado a runTask), entra al if
                if (task.isFinalized()) {
                    //Obtiene en nextTask las tareas siguientes a ejecutar
                    for (Task nextTask : plan.getGraphPlan().get(task)) {
                        boolean canExecute = true;
                        //Verificar que todas las tareas anteriores a la tarea que se esta analizando(nextTast) ya terminaron
                        for (Task dependencyTask : plan.getDependencyGraph().get(nextTask)) {
                            if (!dependencyTask.isFinalized()) {
                                canExecute = false;
                                break;
                            }
                        }
                        //si todas terminaron, pueden ejecutar las siguientes y las agrega a waiting for execution
                        if (canExecute) {
                            nextTask.setTaskWaitingForExecution();
                            plan.getTasksWaitingForExecution().add(nextTask);
                        }
                    }
                    plan.getTasksInExecution().remove(task);
                } else {
                    task.runTask(rst.getBelieves());
                }
            }
            for (Iterator<Task> iterator = plan.getTasksWaitingForExecution().iterator(); iterator.hasNext();) {
                Task next = iterator.next();
                plan.getTasksInExecution().add(next);
                next.setTaskInExecution();
                next.runTask(rst.getBelieves());
                iterator.remove();
            }
        }
    }
}
