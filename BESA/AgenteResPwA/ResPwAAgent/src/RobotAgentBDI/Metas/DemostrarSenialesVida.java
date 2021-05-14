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
import Tareas.DemostrarSenialesVida.ActivarSenialesVida;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class DemostrarSenialesVida extends GoalBDI {
    
    private static String descrip = "DemostrarSenialesVida";
    
    public static DemostrarSenialesVida buildGoal() {
        ActivarSenialesVida asv = new ActivarSenialesVida();
        Plan rolePlan= new Plan();
        rolePlan.addTask(asv);
        
        RationalRole interSocial = new RationalRole(descrip, rolePlan);
        DemostrarSenialesVida b = new DemostrarSenialesVida(InitRESPwA.getPlanID(), interSocial, descrip, GoalBDITypes.NEED);
        return b;
    }

    public DemostrarSenialesVida(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta DemostrarSenialesVida created
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta DemostrarSenialesVida evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta DemostrarSenialesVida detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged()){
            if(!blvs.getbEstadoRobot().isActivadoSenalesDeVida())
            {
                return 1.0;
            }
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta DemostrarSenialesVida evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta DemostrarSenialesVida evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta DemostrarSenialesVida predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta DemostrarSenialesVida goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoRobot().isActivadoSenalesDeVida();
    }
}
