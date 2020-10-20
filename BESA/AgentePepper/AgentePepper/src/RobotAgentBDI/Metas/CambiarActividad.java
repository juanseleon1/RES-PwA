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
import Init.RunAgentePepper;
import Tareas.CambiarActividad.*;
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
public class CambiarActividad extends GoalBDI{

        private static String descrip;
        private static float inflar;

    public static CambiarActividad buildGoal() {

        DetenerPlan detenerPlan = new DetenerPlan();
        IniciarNuevoPlan iniciarNuevoP = new IniciarNuevoPlan();
        RecibirSolicitudCambio recibirSolicitudC = new RecibirSolicitudCambio();

        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);

        rolePlan.addTask(recibirSolicitudC);
        rolePlan.addTask(detenerPlan);
        rolePlan.addTask(iniciarNuevoP);

        RationalRole changeRole = new RationalRole(descrip, rolePlan);
        CambiarActividad b= new CambiarActividad(RunAgentePepper.getPlanID(), changeRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CambiarActividad(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta CambiarActividad created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //valor gusto actividad nueva, mirar valor concentracion depende de cada PwA
        if(blvs.getbEstadoEmocionalPwA().getConcentracion() > 10 && blvs.getbEstadoEmocionalPwA().getEstadoEmocional() == "triste" || blvs.getbEstadoEmocionalPwA().getEstadoEmocional() == "ira") {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarActividad goalSucceeded");
        return false;
    }
    
}
