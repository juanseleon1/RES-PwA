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
import EmotionalAnalyzerAgent.EmotionPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.AnimarElogiarPwA.EjecutarEstrategiaAnimar;
import Init.InitRESPwA;
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
public class AnimarElogiarPwA extends GoalBDI{

    private static String descrip;

    public static AnimarElogiarPwA buildGoal() {

        //evaluar estado emocional
        EjecutarEstrategiaAnimar ejecutarEstrategia = new EjecutarEstrategiaAnimar();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        tarea.add(ejecutarEstrategia);
        Plan rolePlan= new Plan(tarea,resources,null);
        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        AnimarElogiarPwA b= new AnimarElogiarPwA(InitRESPwA.getPlanID(), animateRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    
    public AnimarElogiarPwA(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta AnimarPwA created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA detectGoal");
        
        //crear interface estrategia que permita ejecutarEstrategia(), guardar estrategia en believes y despues sacarla de estos
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //alto numero de errores, tiene aciertos, cierto tiempo activo
        if (blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.SADNESS)) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA goalSucceeded");
        return false;
    }
    
}
