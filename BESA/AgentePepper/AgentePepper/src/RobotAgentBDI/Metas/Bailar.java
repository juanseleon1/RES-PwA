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
import Tareas.Bailar.InicializarBaile;
import Tareas.Bailar.EjecutarBaile;
import Tareas.Bailar.FinalizarBaile;
import Tareas.CambiarCancion.RecibirRetroalimentacion;
import Tareas.Bailar.SeleccionarBaile;
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

    private static String descrip;

    public static Bailar buildGoal() {
        //crear clases tareas y agregar al plan
        EjecutarBaile ejecutarBaile = new EjecutarBaile();
        InicializarBaile inicializarBaile = new InicializarBaile();
        FinalizarBaile finalizarBaile = new FinalizarBaile();
        SeleccionarBaile seleccionarBaile = new SeleccionarBaile();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);
        rolePlan.addTask(seleccionarBaile); //evaluar Estado emocional, mirar tipo de baile que puede hacer dependiendo de la canción
        rolePlan.addTask(ejecutarBaile);
        rolePlan.addTask(inicializarBaile);
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
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //completar
        if (blvs.getbEstadoActividad().calcTiempoActividad() > 30 && blvs.getbPerfilPwA().getPreferencias().getActividadesSis().get("ActMusicoterapia").getGusto() > 5 &&
               blvs.getbPerfilPwA().getPreferencias().isGustoBaile() && blvs.getbPerfilPwA().getPreferencias().getActividadesSis().get("ActMusicoterapia").getEnriquecimiento() > 2) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar evaluateContribution");
       
        //perfil.gustaBaile
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        
        if(blvs.getbPerfilPwA().getPreferencias().isGustoBaile()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Bailar goalSucceeded");
        return false;
    }
    
}
