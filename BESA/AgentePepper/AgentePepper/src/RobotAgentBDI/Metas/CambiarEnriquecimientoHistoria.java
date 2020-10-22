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
import Tareas.CambiarEnriquecimientoHistoria.EvaluarEnriquecer;
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
public class CambiarEnriquecimientoHistoria extends GoalBDI{

    
        private static String descrip;

    public static CambiarEnriquecimientoHistoria buildGoal() {

        //evaluar atencion, emociones
        EvaluarEnriquecer evaluarEnriquecer = new EvaluarEnriquecer();

        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);

        //crear interface enriquecer(bajo,medio,alto) que permita ejecutarEstrategia(), guardar enriquecer en believes y despues sacarla de estos
        rolePlan.addTask(evaluarEnriquecer);//si es automatico o el PwA lo pide
        
        RationalRole cehRole = new RationalRole(descrip, rolePlan);
        CambiarEnriquecimientoHistoria b= new CambiarEnriquecimientoHistoria(RunAgentePepper.getPlanID(), cehRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CambiarEnriquecimientoHistoria(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta CambiarEnriquecimientoHistoria created");
    }

   @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //cambiar valor tiempo (10), debe variar segun la duracion de la historia
        if(blvs.getbEstadoInteraccion().isCambioEnriq() || blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 10) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        double value = 0;
        
        if(blvs.getbEstadoInteraccion().isCambioEnriq()) {
            value = 1.0;
        }
        
        return value;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarEnriquecimientoHistoria goalSucceeded");
        return false;
    }
    
}
