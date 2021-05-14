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
 * @author LaMafecitaBebeLean
 */
public class RetroalimentacionReception extends GoalBDI {

    private static String descrip = "FeedbackPlan";

    public static RetroalimentacionReception buildGoal() {

        TestTask test = new TestTask();
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();

        Plan rolePlan = new Plan();
        rolePlan.addTask(test);

        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        RetroalimentacionReception b = new RetroalimentacionReception(InitRESPwA.getPlanID(), animateRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public RetroalimentacionReception(int id, RationalRole role, String description, GoalBDITypes type) {
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
        System.out.println("Meta Retroalimentacion evaluateContribution");
        return 100;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Retroalimentacion predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Retroalimentacion goalSucceeded");
        return true;
    }

}