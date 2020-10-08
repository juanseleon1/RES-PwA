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
import Tareas.AnimarPwA.EjecutarEstrategiaAnimar;
import Tareas.PwA.EvaluarEstadoEmocional;
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
public class AnimarPwA extends GoalBDI{

    private static String descrip;

    public static AnimarPwA buildGoal() {

        EvaluarEstadoEmocional evaluarEstadoE = new EvaluarEstadoEmocional();
        EjecutarEstrategiaAnimar ejecutarEstrategia = new EjecutarEstrategiaAnimar();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        tarea.add(evaluarEstadoE);
        tarea.add(ejecutarEstrategia);
        Plan rolePlan= new Plan(tarea,resources,null);
        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        AnimarPwA b= new AnimarPwA(0, animateRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public AnimarPwA(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta AnimarPwA created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA goalSucceeded");
        return false;
    }
    
}
