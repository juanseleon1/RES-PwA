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
import Tareas.CambiarActividad.DetenerPlan;
import Tareas.RecargarBateria.MoverseEstacionCarga;
import Tareas.RecargarBateria.ReportarNivelBateria;
import Tareas.RecargarBateria.SuspenderRobot;
import Tareas.RecargarBateria.UbicarEstacionCarga;
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
public class RecargarBateria extends GoalBDI{
                private static String descrip;

    public static RecargarBateria buildGoal() {

        DetenerPlan detenerPlan = new DetenerPlan();
        MoverseEstacionCarga moverseEstacionCarga = new MoverseEstacionCarga();
        ReportarNivelBateria reportarNivelBateria = new ReportarNivelBateria();
        SuspenderRobot suspenderRobot = new SuspenderRobot();
        UbicarEstacionCarga ubicarEstacionCarga = new UbicarEstacionCarga();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(detenerPlan);
        rolePlan.addTask(reportarNivelBateria);
        rolePlan.addTask(ubicarEstacionCarga);
        rolePlan.addTask(moverseEstacionCarga);
        rolePlan.addTask(suspenderRobot);

        RationalRole recBatRole = new RationalRole(descrip, rolePlan);
        RecargarBateria b= new RecargarBateria(0, recBatRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public RecargarBateria(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta RecargarBateria created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria goalSucceeded");
        return false;
    }
    
}