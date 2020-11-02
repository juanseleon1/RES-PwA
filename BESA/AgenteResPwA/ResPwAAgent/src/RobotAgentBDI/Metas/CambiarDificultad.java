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
import Tareas.CambiarDificultad.EvaluarDesempeño;
import Tareas.CambiarDificultad.SeleccionarEstrategiaDificultad;
import Tareas.CambiarDificultad.EjecutarEstrategiaDificultad;
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
public class CambiarDificultad extends GoalBDI{

    
        private static String descrip;

    public static CambiarDificultad buildGoal() {

        //Hace parte de memorama
        EvaluarDesempeño evaluarDesempeño = new EvaluarDesempeño();
        SeleccionarEstrategiaDificultad seleccionarEstrategiaD = new SeleccionarEstrategiaDificultad();
        EjecutarEstrategiaDificultad ejecutarEstrategia = new EjecutarEstrategiaDificultad();
        
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);


        rolePlan.addTask(evaluarDesempeño);
        rolePlan.addTask(seleccionarEstrategiaD); 
        //crear interface estrategia que permita ejecutarEstrategia(), guardar estrategia en believes y despues sacarla de estos
        rolePlan.addTask(ejecutarEstrategia);

        RationalRole cambiarDifRole = new RationalRole(descrip, rolePlan);
        CambiarDificultad b= new CambiarDificultad(InitRESPwA.getPlanID(), cambiarDifRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CambiarDificultad(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta CambiarDificultad created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //faltan intentos fallidos ((intentosFallidos> + 50% errores || FallosSeguidos>V || intentosCorrectos > 100%)
        if (blvs.getbEstadoInteraccion().isCambioDificultadVoz()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarDificultad goalSucceeded");
        return true;
    }
    
}
