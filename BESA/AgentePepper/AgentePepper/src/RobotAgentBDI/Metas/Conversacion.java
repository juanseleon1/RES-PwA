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
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.Conversacion.PreguntarEstAnimo;
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
public class Conversacion extends GoalBDI{

    private static String descrip;

    public static Conversacion buildGoal() {

        PreguntarEstAnimo preguntarEA = new PreguntarEstAnimo();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(preguntarEA);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        Conversacion b= new Conversacion(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    
    public Conversacion(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta Conversacion created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if (blvs.getbEstadoInteraccion().isDetectaPwA()) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Conversacion goalSucceeded");
        return false;
    }
    
}
