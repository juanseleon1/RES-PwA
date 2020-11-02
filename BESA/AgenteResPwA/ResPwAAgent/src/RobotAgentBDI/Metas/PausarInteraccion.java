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
import Tareas.PausarInteraccion.PausarActividad;
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
public class PausarInteraccion extends GoalBDI{
    
    private static String descrip = "PausarInteraccion";

    public static PausarInteraccion buildGoal() {

        PausarActividad pausarActividad = new PausarActividad();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(pausarActividad);

        RationalRole PaIntRole = new RationalRole(descrip, rolePlan);
        PausarInteraccion b= new PausarInteraccion(InitRESPwA.getPlanID(), PaIntRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public PausarInteraccion(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta PausarInteraccion created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //3 min
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged()){
            if(blvs.getbEstadoInteraccion().isPausarInt() || blvs.getbEstadoInteraccion().getTiempoSinInt()/60 > 3) {
            return 1.0;
        }
        }
        
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return 1.0 + blvs.getbEstadoActividad().getBoostPausarInteraccion();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PausarInteraccion goalSucceeded");
        return true;
    }
    
}
