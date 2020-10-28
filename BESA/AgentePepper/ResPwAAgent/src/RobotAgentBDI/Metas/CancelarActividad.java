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
import Init.InitRESPwA;
import Tareas.CancelarActividad.CancelarActividadTask;
import Tareas.CancelarActividad.PreguntarActividad;
import Tareas.SeleccionarCuentoGusto.MoverseFrentePwA;
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
public class CancelarActividad extends GoalBDI{

    private static String descrip = "CancelarActividad";

    public static CancelarActividad buildGoal() {

        CancelarActividadTask cancelarActividad = new CancelarActividadTask();
        MoverseFrentePwA moversePwA = new MoverseFrentePwA();
        PreguntarActividad preguntarA = new PreguntarActividad();

        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);
        
        rolePlan.addTask(cancelarActividad); //evaluar estado emocional
        rolePlan.addTask(moversePwA);
        rolePlan.addTask(preguntarA);

        RationalRole cancelarActRole = new RationalRole(descrip, rolePlan);
        CancelarActividad b= new CancelarActividad(InitRESPwA.getPlanID(), cancelarActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CancelarActividad(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta CancelarActividad created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if(blvs.getbEstadoInteraccion().isCancelarInt()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CancelarActividad goalSucceeded");
        return false;
    }
    
}
