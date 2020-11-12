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
import Tareas.ActivarKaraoke.MoverseFrentePwA;
import Tareas.Cuenteria.EvaluarEstrategiaEnriquecer;
import Tareas.Cuenteria.EvaluarEstrategiaEnriquecer;
import Tareas.Cuenteria.RecibirRetroalimentacion;
import Tareas.Cuenteria.RecomendarCuento;
import Tareas.Cuenteria.ReproducirCuento;
import Tareas.Cuenteria.SeleccionarCuento;
import Tareas.Cuenteria.VerificarObstaculos;
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
public class Cuenteria extends GoalBDI {
    
    private static String descrip = "Cuenteria";
    
    public static Cuenteria buildGoal() {
        EvaluarEstrategiaEnriquecer eEstrategia = new EvaluarEstrategiaEnriquecer();
        MoverseFrentePwA moversePwA = new MoverseFrentePwA();
        RecibirRetroalimentacion retro = new RecibirRetroalimentacion();
        RecomendarCuento recomCuento = new RecomendarCuento();
        ReproducirCuento rCuento = new ReproducirCuento();
        SeleccionarCuento sCuento = new SeleccionarCuento();
        VerificarObstaculos vObstaculos = new VerificarObstaculos();
        
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();
        Plan rolePlan = new Plan();
        
        rolePlan.addTask(sCuento);
        rolePlan.addTask(vObstaculos);
        
        tarea.add(vObstaculos);
        rolePlan.addTask(moversePwA,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(moversePwA);
        tarea.add(sCuento);
        rolePlan.addTask(eEstrategia,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(sCuento);
        tarea.add(moversePwA);
        rolePlan.addTask(rCuento,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCuento);
        rolePlan.addTask(retro,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(retro);
        rolePlan.addTask(recomCuento,tarea);
        
        RationalRole cuenteriaRole = new RationalRole(descrip, rolePlan);
        Cuenteria b = new Cuenteria(InitRESPwA.getPlanID(), cuenteriaRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public Cuenteria(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Cuenteria predictResultUnlegability");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Cuenteria evaluateViability");
        return true;
    }
    
}
