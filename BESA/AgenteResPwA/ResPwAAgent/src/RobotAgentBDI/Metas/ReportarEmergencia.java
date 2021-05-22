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
import Tareas.ReportarEmergencia.LlamarCuidador;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class ReportarEmergencia extends GoalBDI{
    
    private static String descrip = "ReportarEmergencia";
    private double tiempo = 0;
    
    public static ReportarEmergencia buildGoal() {
        LlamarCuidador lc = new LlamarCuidador();
        Plan rolePlan= new Plan();
        rolePlan.addTask(lc);
        
        RationalRole interSocial = new RationalRole(descrip, rolePlan);
        ReportarEmergencia b = new ReportarEmergencia(InitRESPwA.getPlanID(), interSocial, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public ReportarEmergencia(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ReportarEmergencia created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReportarEmergencia evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReportarEmergencia detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //PwA diga ayuda
        if(!blvs.getbEstadoInteraccion().isDetectaPwA() && blvs.getbEstadoInteraccion().isLogged() )

        {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReportarEmergencia evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReportarEmergencia evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReportarEmergencia predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReportarEmergencia goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoInteraccion().isDetectaPersona() || blvs.getbEstadoInteraccion().isDetectaPwA()){
            return true;
        }
        return false;
    }
    
}
