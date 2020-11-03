/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Metas;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernelAgentExceptionBESA;
import Tareas.AnimarElogiarPwA.EjecutarEstrategiaAnimar;
import Init.InitRESPwA;
import Tareas.EstimularEmocionalmente.ContinuarActividad;
import Tareas.EstimularEmocionalmente.InterpretarEstadoFlujo;
import Tareas.EstimularEmocionalmente.RetroalimentarBDI;
import Tareas.EstimularEmocionalmente.SeleccionarEstrategiaEmocional;
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
public class EstimularEmocionalmente extends GoalBDI{

    
    private static String descrip;

    public static EstimularEmocionalmente buildGoal() {
        
        //Pertenece a Actividad Memorama

        //evaluar estado emocional
        ContinuarActividad continuarActividad = new ContinuarActividad();
        EjecutarEstrategiaAnimar ejecutarEstrategia = new EjecutarEstrategiaAnimar();
        InterpretarEstadoFlujo interpretarEstadoFlujo = new InterpretarEstadoFlujo();
        SeleccionarEstrategiaEmocional seleccionarEstrategiaE = new SeleccionarEstrategiaEmocional();
        RetroalimentarBDI retroalimentar = new RetroalimentarBDI();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        //evaluar estado emocional
        rolePlan.addTask(interpretarEstadoFlujo);
        rolePlan.addTask(seleccionarEstrategiaE);
        rolePlan.addTask(ejecutarEstrategia);
        rolePlan.addTask(retroalimentar);
        rolePlan.addTask(continuarActividad);

        RationalRole estimEmoRole = new RationalRole(descrip, rolePlan);
        EstimularEmocionalmente b= new EstimularEmocionalmente(InitRESPwA.getPlanID(), estimEmoRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public EstimularEmocionalmente(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta EstimularEmocionalmente created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta EstimularEmocionalmente goalSucceeded");
        return true;
    }
    
}
