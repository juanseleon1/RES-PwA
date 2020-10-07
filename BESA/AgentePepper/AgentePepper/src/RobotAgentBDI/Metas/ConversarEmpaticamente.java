/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Metas;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import Tareas.ConversarEmpaticamente.PreguntarSentimientos;
import Tareas.PwA.EvaluarEstadoEmocional;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class ConversarEmpaticamente extends GoalBDI{

    private static String descrip;

    public static ConversarEmpaticamente buildGoal() {

        EvaluarEstadoEmocional evaluarEstadoE = new EvaluarEstadoEmocional();
        PreguntarSentimientos preguntarSentimientos = new PreguntarSentimientos();
    
        Plan rolePlan= new Plan();

        rolePlan.addTask(evaluarEstadoE);
        rolePlan.addTask(preguntarSentimientos);

        RationalRole convEmpRole = new RationalRole(descrip, rolePlan);
        ConversarEmpaticamente b= new ConversarEmpaticamente(0, convEmpRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public ConversarEmpaticamente(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta ConversarEmpaticamente created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente goalSucceeded");
        return false;
    }
    
}
