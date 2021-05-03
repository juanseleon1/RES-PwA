package RobotAgentBDI.Metas;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import Init.InitRESPwA;
import Tareas.Test.TestTask;
import java.util.ArrayList;
import java.util.List;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class TestPlan extends GoalBDI {

    private static String descrip = "TestPlan";

    public static TestPlan buildGoal() {

        TestTask test = new TestTask();
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();

        Plan rolePlan = new Plan();
        rolePlan.addTask(test);

        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        TestPlan b = new TestPlan(InitRESPwA.getPlanID(), animateRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public TestPlan(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta TestPlan evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        return 100;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta TestPlan evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta TestPlan evaluateContribution");
        return 100;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta TestPlan predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta TestPlan goalSucceeded");
        return true;
    }

}
