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
import EmotionalAnalyzerAgent.Utils.EmotionPwA;
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.ConversarEmpaticamente.PreguntarSentimientos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class ConversarEmpaticamente extends GoalBDI{

    private static String descrip = "ConversarEmpaticamente";

    public static ConversarEmpaticamente buildGoal() {

        //evaluar estado emocional
        PreguntarSentimientos preguntarSentimientos = new PreguntarSentimientos();
    
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan();

        rolePlan.addTask(preguntarSentimientos);
        tarea.add(preguntarSentimientos);
        
        RationalRole convEmpRole = new RationalRole(descrip, rolePlan);
        ConversarEmpaticamente b= new ConversarEmpaticamente(InitRESPwA.getPlanID(), convEmpRole, descrip, GoalBDITypes.REQUIREMENT);
        return b;
    }
    public ConversarEmpaticamente(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ConversarEmpaticamente created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConversarEmpaticamente evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente detectGoal");

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged() && blvs.getbPerfilPwA().getPerfil().getPerfilMedico().getFast() <= 5)
        {
            if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante() < 0 && blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante()>15)//revisar valor 
            {
                return 1.0;
            }
        }
        return 1;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConversarEmpaticamente evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConversarEmpaticamente evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante() + 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConversarEmpaticamente predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConversarEmpaticamente goalSucceeded");
        //verificar objetivo cumplido ej: que este feliz en algun punto del plan y este se termine
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante() >0){
            return true;
        }
        return false;
    }
    
}
