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
public class EstimularEmocionalmente extends GoalBDI{

    
    private static String descrip;

    public static EstimularEmocionalmente buildGoal() {

        EvaluarEstadoEmocional evaluarEstadoE = new EvaluarEstadoEmocional();
        ContinuarActividad continuarActividad = new ContinuarActividad();
        EjecutarEstrategia ejecutarEstrategia = new EjecutarEstrategia();
        InterpretarEstadoFlujo interpretarEstadoFlujo = new InterpretarEstadoFlujo();
        SeleccionarEstrategiaEmocional seleccionarEstrategiaE = new SeleccionarEstrategiaEmocional();
        RetroalimentarBDI retroalimentar = new RetroalimentarBDI();

        Plan rolePlan= new Plan();

        rolePlan.addTask(evaluarEstadoE);
        rolePlan.addTask(interpretarEstadoFlujo);
        rolePlan.addTask(seleccionarEstrategiaE);
        rolePlan.addTask(ejecutarEstrategia);
        rolePlan.addTask(retroalimentar);
        rolePlan.addTask(continuarActividad);

        RationalRole estimEmoRole = new RationalRole(descrip, rolePlan);
        EstimularEmocionalmente b= new EstimularEmocionalmente(0, estimEmoRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public EstimularEmocionalmente(int id, RationalRole role, String description, GoalBDITypes type) {
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
