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
import ResPwAEntities.Actividadpwa;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.Bailar.InicializarBaile;
import Tareas.Bailar.FinalizarBaile;
import Tareas.Bailar.SeleccionarBaile;
import java.math.BigInteger;
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
public class Bailar extends GoalBDI{

    private static String descrip="Bailar";

    public static Bailar buildGoal() {
        
        InicializarBaile inicializarBaile = new InicializarBaile();
        FinalizarBaile finalizarBaile = new FinalizarBaile();
        SeleccionarBaile seleccionarBaile = new SeleccionarBaile();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);
        rolePlan.addTask(inicializarBaile);
        rolePlan.addTask(seleccionarBaile); //evaluar Estado emocional, mirar tipo de baile que puede hacer dependiendo de la canción
        rolePlan.addTask(finalizarBaile);
        
        //while de espera activa para saber que se movia a un lugar seguro
        
        RationalRole musicTherapyRole = new RationalRole(descrip, rolePlan);
        Bailar b= new Bailar(InitRESPwA.getPlanID(), musicTherapyRole, descrip, GoalBDITypes.DUTY);
        
        return b;
    }

    public Bailar(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta Bailar created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        //completar
        List<Actividadpwa> lista = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActividadpwaList();
        double gusto = 0;
        int enriq = 0;
        for (Actividadpwa act : lista) {
            if(act.getNombre().equalsIgnoreCase("musioterapia"))
            {
                gusto=act.getGusto();
                enriq=(int) act.getEnriquecimientofavorito().floatValue();
            }
        }
        if (blvs.getbEstadoActividad().calcTiempoActividad() > 30 && gusto > 5 &&
               blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustobaile() > 0.5 && enriq > 2) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar evaluateContribution");
       
        //perfil.gustaBaile
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        
        if(blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustobaile()>0.5 && !blvs.getbEstadoActividad().isFinalizoActividad()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar goalSucceeded");
        return false;
    }
    
}
