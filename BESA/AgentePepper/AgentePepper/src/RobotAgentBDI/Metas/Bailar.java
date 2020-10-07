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
import Tareas.Bailar.CambiarBaile;
import Tareas.Bailar.EjecutarBaile;
import Tareas.Bailar.FinalizarBaile;
import Tareas.Bailar.RecibirRetroalimentacion;
import Tareas.Bailar.RepetirBaile;
import Tareas.Bailar.SeleccionarBaile;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class Bailar extends GoalBDI{

    private static String descrip;

    public static Bailar buildGoal() {
        //crear clases tareas y agregar al plan
        EjecutarBaile ejecutarBaile = new EjecutarBaile();
        CambiarBaile cambiarBaile = new CambiarBaile();
        FinalizarBaile finalizarBaile = new FinalizarBaile();
        RecibirRetroalimentacion recibirRetroalimentacion = new RecibirRetroalimentacion();
        RepetirBaile repetirBaile = new RepetirBaile();
        SeleccionarBaile seleccionarBaile = new SeleccionarBaile();

        Plan rolePlan= new Plan();

        rolePlan.addTask(seleccionarBaile);
        rolePlan.addTask(ejecutarBaile);
        rolePlan.addTask(recibirRetroalimentacion);
        rolePlan.addTask(cambiarBaile);
        rolePlan.addTask(repetirBaile);
        rolePlan.addTask(finalizarBaile);

        RationalRole musicTherapyRole = new RationalRole(descrip, rolePlan);
        Bailar b= new Bailar(0, musicTherapyRole, descrip, GoalBDITypes.DUTY);
        
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
