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
import ResPwAEntities.Actxpreferencia;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAActivity;
import Tareas.Cuenteria.EvaluarEstrategiaEnriquecer;
import Tareas.Cuenteria.MoverseFrentePwA;
import Tareas.Cuenteria.RecibirRetroalimentacion;
import Tareas.Cuenteria.RecomendarCuento;
import Tareas.Cuenteria.ReproducirCuento;
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
        
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();
        Plan rolePlan = new Plan();
        
        rolePlan.addTask(recomCuento);
        rolePlan.addTask(moversePwA);
        
        tarea = new ArrayList<>();
        tarea.add(moversePwA);
        tarea.add(recomCuento);
        rolePlan.addTask(eEstrategia,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(recomCuento);
        tarea.add(moversePwA);
        rolePlan.addTask(rCuento,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCuento);
        rolePlan.addTask(retro,tarea);
        
        RationalRole cuenteriaRole = new RationalRole(descrip, rolePlan);
        Cuenteria b = new Cuenteria(InitRESPwA.getPlanID(), cuenteriaRole, descrip, GoalBDITypes.OPORTUNITY);
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
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendido() && blvs.getbEstadoInteraccion().isLogged()) {
            if(blvs.getbEstadoActividad().getActividadActual() != null && blvs.getbEstadoActividad().getActividadActual().equals(ResPwAActivity.CUENTERIA) && !blvs.getbEstadoActividad().isFinalizoActividad()) {
                return 1;
            }
        }
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
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        List<Actxpreferencia> listaAct = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActxpreferenciaList();
        double valor=0;
        
        for (Actxpreferencia act: listaAct) {
            if(act.getActividadpwa().getNombre().equals(ResPwAActivity.CUENTERIA)) {
                valor = act.getGusto();
            }
        }
        
        return valor+blvs.getbEstadoEmocionalRobot().getEm().getState().getInfluenceFactor();
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
