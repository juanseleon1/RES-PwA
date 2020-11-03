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
import Tareas.ReanudarActividad.RecibirNotificacionReanudar;
import Tareas.ReanudarActividad.SolicitarPosicionPwA;
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
public class ReanudarActividad extends GoalBDI{

    private static String descrip = "ReanudarActividad";

    public static ReanudarActividad buildGoal() {

        //REVISAR
        RecibirNotificacionReanudar recibirNotificacionR = new RecibirNotificacionReanudar();
        SolicitarPosicionPwA solicitarPosicion = new SolicitarPosicionPwA();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan();

        rolePlan.addTask(recibirNotificacionR);
        rolePlan.addTask(solicitarPosicion);
        
        RationalRole reaActnRole = new RationalRole(descrip, rolePlan);
        ReanudarActividad b= new ReanudarActividad(InitRESPwA.getPlanID(), reaActnRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public ReanudarActividad(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ReanudarActividad created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReanudarActividad evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReanudarActividad detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

        //estaba suspendido
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged()){
            if(blvs.getbEstadoInteraccion().isDetectaPwA() && blvs.getbEstadoRobot().isEstaSuspendido()) {
            return 1.0;
        }
        }
        
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReanudarActividad evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ReanudarActividad evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return 1.0 + blvs.getbEstadoActividad().getBoostReanudarActividad();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReanudarActividad predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ReanudarActividad goalSucceeded");
        return true;
    }
    
}
