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
import Tareas.GenerarReporteInteraccion.MostrarInfo;
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
public class GenerarReporteInteraccion extends GoalBDI{

            private static String descrip;

    public static GenerarReporteInteraccion buildGoal() {

        MostrarInfo mostrarInfo = new MostrarInfo();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(mostrarInfo);//sacar info y mostrar

        RationalRole genRepRole = new RationalRole(descrip, rolePlan);
        GenerarReporteInteraccion b= new GenerarReporteInteraccion(RunAgentePepper.getPlanID(), genRepRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public GenerarReporteInteraccion(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta GenerarReporteInteraccion created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if(blvs.getbEstadoActividad().isFinalizoActividad()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion evaluateContribution");
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta GenerarReporteInteraccion goalSucceeded");
        return false;
    }
    
}
