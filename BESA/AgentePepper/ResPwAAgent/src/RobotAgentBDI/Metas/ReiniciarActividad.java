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
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Init.InitRESPwA;
import Tareas.ReiniciarActividad.ReiniciarActividadTask;
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
public class ReiniciarActividad extends GoalBDI{
    
    private static String descrip = "ReiniciarActividad";

    public static ReiniciarActividad buildGoal() {

        //evaluar estado emocional
        ReiniciarActividadTask reiniciarActividad = new ReiniciarActividadTask();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(reiniciarActividad);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        ReiniciarActividad b= new ReiniciarActividad(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public ReiniciarActividad(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta ReiniciarActividad created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if(blvs.getbEstadoInteraccion().isReiniciarInt()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReiniciarActividad goalSucceeded");
        return false;
    }
    
}
