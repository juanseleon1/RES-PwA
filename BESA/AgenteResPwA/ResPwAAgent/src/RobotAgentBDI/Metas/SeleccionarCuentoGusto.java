/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Metas;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernelAgentExceptionBESA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Init.InitRESPwA;
import RobotAgentBDI.ResPwAActivity;
import Tareas.SeleccionarCuentoGusto.BuscarAnimaciones;
import Tareas.SeleccionarCuentoGusto.MoverseFrentePwA;
import Tareas.SeleccionarCuentoGusto.RecomendarCuento;
import Tareas.SeleccionarCuentoGusto.SeleccionarCuento;
import Tareas.SeleccionarCuentoGusto.VerificarObstaculos;
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
public class SeleccionarCuentoGusto extends GoalBDI{
    
    private static String descrip = "SeleccionarCuentoGusto";

    public static SeleccionarCuentoGusto buildGoal() {

        BuscarAnimaciones buscarAnimaciones = new BuscarAnimaciones();
        MoverseFrentePwA moverseFrente = new MoverseFrentePwA();
        SeleccionarCuento seleccionarCuento = new SeleccionarCuento();
        VerificarObstaculos verificarObstaculos = new VerificarObstaculos();
        RecomendarCuento recomendarCuento = new RecomendarCuento();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(seleccionarCuento); //evalua estado emocional, mira perfil
        rolePlan.addTask(buscarAnimaciones); //buscar y enviar
        rolePlan.addTask(verificarObstaculos); 
        rolePlan.addTask(moverseFrente); //detectar PwA y moverse al frente de él
        rolePlan.addTask(recomendarCuento);
        
        RationalRole selCuenGRole = new RationalRole(descrip, rolePlan);
        SeleccionarCuentoGusto b= new SeleccionarCuentoGusto(InitRESPwA.getPlanID(), selCuenGRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public SeleccionarCuentoGusto(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta SeleccionarCuentoGusto created");
    }

   @Override
    public double evaluateViability(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta SeleccionarCuentoGusto evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta SeleccionarCuentoGusto detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //verificar gusto cuento seleccionado > 0.5
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendidoInt() &&  blvs.getbEstadoInteraccion().isLogged()){
            if(blvs.getbEstadoActividad().getCuentoActual()!=null && blvs.getbEstadoActividad().getCuentoActual().getGusto() > 0.5 && blvs.getbEstadoActividad().getActividadActual().equals(ResPwAActivity.CUENTERIA) && blvs.getbEstadoActividad().isFinalizoActividad()) {
            return 1.0;
        }
        }
        
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernelAgentExceptionBESA {
        //System.out.println("Meta SeleccionarCuentoGusto evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernelAgentExceptionBESA {
        //System.out.println("Meta SeleccionarCuentoGusto evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return blvs.getbEstadoActividad().getCuentoActual().getGusto() + blvs.getbEstadoActividad().getBoostSeleccionarCuentoGusto();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernelAgentExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernelAgentExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto goalSucceeded");
        return true;
    }
    
}
