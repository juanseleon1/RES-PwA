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
import Tareas.MantenerAtencionPwA.EjecutarEstrategiaAtencion;
import Tareas.MantenerAtencionPwA.SeleccionarEstrategiaAtencion;
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
public class MantenerAtencionPwA extends GoalBDI{

    private static String descrip = "MantenerAtencionPwA";

    public static MantenerAtencionPwA buildGoal() {

        //evaluar atencion, estado emocional
        SeleccionarEstrategiaAtencion seleccionarEstrategiaA = new SeleccionarEstrategiaAtencion();
        EjecutarEstrategiaAtencion ejecutarEstrategia = new EjecutarEstrategiaAtencion();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(seleccionarEstrategiaA);
        //crear interface estrategia que permita ejecutarEstrategia(), guardar estrategia en believes y despues sacarla de estos
        rolePlan.addTask(ejecutarEstrategia);

        RationalRole mantAtenRole = new RationalRole(descrip, rolePlan);
        MantenerAtencionPwA b= new MantenerAtencionPwA(InitRESPwA.getPlanID(), mantAtenRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public MantenerAtencionPwA(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta MantenerAtencionPwA created");
    }

@Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //se debe cambiar el tiempo de acuerdo a dificultad, tiempo, intereses
        if(blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 1 && blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() > 1) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() + blvs.getbEstadoEmocionalPwA().getTiempoSinRelajacion() + blvs.getbEstadoActividad().getBoostMantenerAtencionPwA();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MantenerAtencionPwA goalSucceeded");
        return true;
    }
}
