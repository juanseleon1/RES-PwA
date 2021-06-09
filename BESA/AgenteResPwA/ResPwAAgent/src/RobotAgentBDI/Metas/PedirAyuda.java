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
import Init.InitRESPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.PedirAyuda.ConfirmarAyuda;
import Tareas.PedirAyuda.PeticionAyuda;
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
public class PedirAyuda extends GoalBDI {

    private static String descrip = "PedirAyuda";

    public static PedirAyuda buildGoal() {

        PeticionAyuda peticionAyuda = new PeticionAyuda();
        ConfirmarAyuda confirmarAyuda = new ConfirmarAyuda();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();

        Plan rolePlan = new Plan();

        rolePlan.addTask(peticionAyuda); //dar respuesta a PwA
        taskList.add(peticionAyuda);
        rolePlan.addTask(confirmarAyuda);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        PedirAyuda b = new PedirAyuda(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public PedirAyuda(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta PedirAyuda created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta PedirAyuda evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta PedirAyuda detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() && blvs.getbEstadoInteraccion().isLogged() && blvs.getbEstadoInteraccion().isAyudaActividadSolicitada()) {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta PedirAyuda evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta PedirAyuda evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves) stateBDI.getBelieves();
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta PedirAyuda predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta PedirAyuda goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //revisar si se da la respuesta
        return blvs.getbEstadoInteraccion().isAyudaExitosa();     
    }
    
}
