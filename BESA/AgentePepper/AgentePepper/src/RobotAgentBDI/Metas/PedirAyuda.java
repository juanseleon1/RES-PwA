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
import Tareas.PedirAyuda.PeticionAyuda;
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
public class PedirAyuda extends GoalBDI{
    
    private static String descrip;

    public static PedirAyuda buildGoal() {

        PeticionAyuda peticionAyuda = new PeticionAyuda();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(peticionAyuda); //dar respuesta a PwA

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        PedirAyuda b= new PedirAyuda(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public PedirAyuda(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta PedirAyuda created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if (blvs.getbEstadoInteraccion().isAyudaActividadSolicitada()) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda goalSucceeded");
        return false;
    }
    
}
