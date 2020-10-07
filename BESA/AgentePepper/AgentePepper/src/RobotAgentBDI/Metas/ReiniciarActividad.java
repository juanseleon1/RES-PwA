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
import Tareas.PwA.ActualizarPerfil;
import Tareas.PwA.EvaluarConcentracionAtencion;
import Tareas.PwA.EvaluarEstadoEmocional;
import Tareas.ReiniciarActividad.ReiniciarActividadTask;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class ReiniciarActividad extends GoalBDI{
                private static String descrip;

    public static ReiniciarActividad buildGoal() {

        EvaluarEstadoEmocional evaluarEstadoE = new EvaluarEstadoEmocional();
        EvaluarConcentracionAtencion evaluarCA = new EvaluarConcentracionAtencion();
        ActualizarPerfil actualizarPerfil = new ActualizarPerfil();
        ReiniciarActividadTask reiniciarActividad = new ReiniciarActividadTask();

        Plan rolePlan= new Plan();

        rolePlan.addTask(evaluarEstadoE);
        rolePlan.addTask(evaluarCA);
        rolePlan.addTask(actualizarPerfil);
        rolePlan.addTask(reiniciarActividad);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        ReiniciarActividad b= new ReiniciarActividad(0, reiActRole, descrip, GoalBDITypes.DUTY);
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
        return 0;
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
