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
import Init.RunAgentePepper;
import Tareas.PwA.DetectarPwA;
import Tareas.PwA.EvaluarConcentracionAtencion;
import Tareas.SeleccionarCuentoGusto.BuscarAnimaciones;
import Tareas.SeleccionarCuentoGusto.MoverseFrentePwA;
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
    
    private static String descrip;

    public static SeleccionarCuentoGusto buildGoal() {

        EvaluarConcentracionAtencion evaluarCA = new EvaluarConcentracionAtencion();
        BuscarAnimaciones buscarAnimaciones = new BuscarAnimaciones();
        MoverseFrentePwA moverseFrente = new MoverseFrentePwA();
        SeleccionarCuento seleccionarCuento = new SeleccionarCuento();
        DetectarPwA detectarPwA = new DetectarPwA();
        VerificarObstaculos verificarObstaculos = new VerificarObstaculos();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(evaluarCA);
        rolePlan.addTask(seleccionarCuento);
        rolePlan.addTask(buscarAnimaciones);
        rolePlan.addTask(verificarObstaculos);
        rolePlan.addTask(detectarPwA);
        rolePlan.addTask(moverseFrente);
        
        RationalRole selCuenGRole = new RationalRole(descrip, rolePlan);
        SeleccionarCuentoGusto b= new SeleccionarCuentoGusto(RunAgentePepper.getPlanID(), selCuenGRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public SeleccionarCuentoGusto(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta SeleccionarCuentoGusto created");
    }

   @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta SeleccionarCuentoGusto goalSucceeded");
        return false;
    }
    
}
