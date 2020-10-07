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
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
