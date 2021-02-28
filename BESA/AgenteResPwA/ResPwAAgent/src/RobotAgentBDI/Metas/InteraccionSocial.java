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
import Tareas.InteraccionSocial.Interacciones;
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
public class InteraccionSocial extends GoalBDI{
    
    private static String descrip = "InteraccionSocial";
    
    public static InteraccionSocial buildGoal() {
        
        Interacciones inter = new Interacciones();
        List<Task> taskList = new ArrayList<>();
        Plan rolePlan= new Plan();
        rolePlan.addTask(inter);
        
        RationalRole interSocial = new RationalRole(descrip, rolePlan);
        InteraccionSocial b = new InteraccionSocial(InitRESPwA.getPlanID(), interSocial, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public InteraccionSocial(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta InteraccionSocial evaluateViability");
        return 1.0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta InteraccionSocial detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //revisar tiempo, si pasa de 15 min debe decir hola
        if(blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante() > 20000)
        {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta InteraccionSocial evaluatePlausibility");
        return 1.0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta InteraccionSocial evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta InteraccionSocial predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta InteraccionSocial goalSucceeded");
        return false;
    }
    
}
