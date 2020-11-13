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
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.ConversarEmpaticamente.EjecutarEstrategiaConversar;
import Tareas.ConversarEmpaticamente.SeleccionarEstrategiaConversar;
import Tareas.ConversarEmpaticamente.PreguntarSentimientos;
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
public class ConversarEmpaticamente extends GoalBDI{

    private static String descrip = "ConversarEmpaticamente";

    public static ConversarEmpaticamente buildGoal() {

        //evaluar estado emocional
        PreguntarSentimientos preguntarSentimientos = new PreguntarSentimientos();
        SeleccionarEstrategiaConversar seleccionarEstrategiaC = new SeleccionarEstrategiaConversar();
        EjecutarEstrategiaConversar ejecutarEstrategiaC = new EjecutarEstrategiaConversar();
    
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan();

        rolePlan.addTask(preguntarSentimientos);
        tarea= new ArrayList<>();
        tarea.add(preguntarSentimientos);
        //crear interface estrategia que permita ejecutarEstrategia(), guardar estrategia en believes y despues sacarla de estos
        rolePlan.addTask(seleccionarEstrategiaC,tarea);
        tarea.add(seleccionarEstrategiaC);
        tarea= new ArrayList<>();
        rolePlan.addTask(ejecutarEstrategiaC,tarea);
        
        RationalRole convEmpRole = new RationalRole(descrip, rolePlan);
        ConversarEmpaticamente b= new ConversarEmpaticamente(InitRESPwA.getPlanID(), convEmpRole, descrip, GoalBDITypes.DUTY);
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
        //System.out.println("Meta ConversarEmpaticamente detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
       if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged())
       {
           if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante()!=null &&(blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.SADNESS) || blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.ANGER)) && blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante()>15) {
            return 1.0;
        }
        
       }
        
        return 0;
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
        return blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante() + blvs.getbEstadoActividad().getBoostConversarEmpaticamente();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConversarEmpaticamente goalSucceeded");
        //verificar objetivo cumplido ej: que este feliz en algun punto del plan y este se termine
        return true;
    }
    
}
