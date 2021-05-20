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
import Init.InitRESPwA;
import Tareas.AnimarElogiarPwA.EvaluarEstrategiaAnimar;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    private static String descrip = "AnimarElogiarPwA";

    public static AnimarElogiarPwA buildGoal() {

        //evaluar estado emocional
        EvaluarEstrategiaAnimar evaluarEstrategia = new EvaluarEstrategiaAnimar();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();

        Plan rolePlan= new Plan();
        rolePlan.addTask(evaluarEstrategia);

        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        AnimarElogiarPwA b= new AnimarElogiarPwA(InitRESPwA.getPlanID(), animateRole, descrip, GoalBDITypes.REQUIREMENT);
        return b;
    }
    
    public AnimarElogiarPwA(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta AnimarPwA created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AnimarPwA evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        

        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged()){
            if (blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 300000 || blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 300000) {
                return 1.0;
            }
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AnimarPwA evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AnimarPwA evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();

        if (blvs.getbEstadoEmocionalPwA().getEmocionPredominante() < 0) {
            return 1.0 + blvs.getbEstadoActividad().getBoostAnimarElogiarPwA() + blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante();
        }
        return blvs.getbEstadoActividad().getBoostAnimarElogiarPwA() + blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AnimarPwA predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AnimarPwA goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //REVISAR, termina cuando ejecuta la accion en la tarea?
        if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante()  < 0){
            return true;
        }
        return false;
    }
    
}
