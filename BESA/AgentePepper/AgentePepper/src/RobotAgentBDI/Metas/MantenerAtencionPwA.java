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
import Tareas.MantenerAtencionPwA.EjecutarEstrategiaAtencion;
import Tareas.MantenerAtencionPwA.SeleccionarEstrategiaAtencion;
import Tareas.PwA.EvaluarConcentracionAtencion;
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
public class MantenerAtencionPwA extends GoalBDI{

            private static String descrip;

    public static MantenerAtencionPwA buildGoal() {

        EvaluarConcentracionAtencion evaluarCA = new EvaluarConcentracionAtencion();
        SeleccionarEstrategiaAtencion seleccionarEstrategiaA = new SeleccionarEstrategiaAtencion();
        EjecutarEstrategiaAtencion ejecutarEstrategia = new EjecutarEstrategiaAtencion();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(evaluarCA);
        rolePlan.addTask(seleccionarEstrategiaA);
        rolePlan.addTask(ejecutarEstrategia);

        RationalRole mantAtenRole = new RationalRole(descrip, rolePlan);
        MantenerAtencionPwA b= new MantenerAtencionPwA(0, mantAtenRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public MantenerAtencionPwA(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta MantenerAtencionPwA created");
    }

@Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA goalSucceeded");
        return false;
    }
}