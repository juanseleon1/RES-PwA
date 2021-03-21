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
import Tareas.RecargarBateria.SuspenderRobot;
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
public class RecargarBateria extends GoalBDI {

    private static String descrip = "RecargarBateria";

    public static RecargarBateria buildGoal() {

        //MoverseEstacionCarga moverseEstacionCarga = new MoverseEstacionCarga();
        SuspenderRobot suspenderRobot = new SuspenderRobot();//Suspender a robot y notificar/decir
        //UbicarEstacionCarga ubicarEstacionCarga = new UbicarEstacionCarga();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();

        Plan rolePlan = new Plan();

        //rolePlan.addTask(ubicarEstacionCarga);
        //rolePlan.addTask(moverseEstacionCarga);
        rolePlan.addTask(suspenderRobot);

        RationalRole recBatRole = new RationalRole(descrip, rolePlan);
        RecargarBateria b = new RecargarBateria(InitRESPwA.getPlanID(), recBatRole, descrip, GoalBDITypes.SURVIVAL);
        return b;
    }

    public RecargarBateria(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta RecargarBateria created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta RecargarBateria evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta RecargarBateria detectGoal");

        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (!blvs.getbEstadoInteraccion().isSistemaSuspendido() && blvs.getbEstadoRobot().getBateria()) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta RecargarBateria evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta RecargarBateria evaluateContribution");
        //RobotAgentBelieves blvs = (RobotAgentBelieves) stateBDI.getBelieves();
        //return 1.0 + blvs.getbEstadoActividad().getBoostRecargarBateria();
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta RecargarBateria predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta RecargarBateria goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoInteraccion().isSistemaSuspendido();
    }

}
