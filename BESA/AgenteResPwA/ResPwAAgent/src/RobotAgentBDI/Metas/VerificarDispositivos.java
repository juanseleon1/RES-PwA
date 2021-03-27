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
import Tareas.VerificarDispositivos.VerificacionDispositivos;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class VerificarDispositivos extends GoalBDI{
    
    private static String descrip = "VerificarDispositivos";
    
    public static VerificarDispositivos buildGoal() {
        VerificacionDispositivos vd = new VerificacionDispositivos();
        Plan rolePlan= new Plan();
        rolePlan.addTask(vd);
        
        RationalRole interSocial = new RationalRole(descrip, rolePlan);
        VerificarDispositivos b = new VerificarDispositivos(InitRESPwA.getPlanID(), interSocial, descrip, GoalBDITypes.SURVIVAL);
        return b;
    }

    public VerificarDispositivos(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta VerificarDispositivos created
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta VerificarDispositivos evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta VerificarDispositivos detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoRobot().isVerificacionDispositivos() && !blvs.getbEstadoRobot().getBateria())
        {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta VerificarDispositivos evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta VerificarDispositivos evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta VerificarDispositivos predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta VerificarDispositivos goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoRobot().isVerificacionDispositivos();
    }
    
}
