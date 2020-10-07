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
import Tareas.CambiarCancion.*;
import Tareas.PwA.EvaluarEstadoEmocional;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class CambiarCancion extends GoalBDI{

        private static String descrip;

    public static CambiarCancion buildGoal() {
    
        EvaluarEstadoEmocional solicitarEstadoE = new EvaluarEstadoEmocional();
        BusquedaCancionYoutube busquedaCancionYT = new BusquedaCancionYoutube();
        ConfirmarCancion confirmarCancion = new ConfirmarCancion();
        RepetirCancion repetirCancion = new RepetirCancion();
        SeleccionarCancion seleccionarCancion = new SeleccionarCancion();

        Plan rolePlan= new Plan();

        rolePlan.addTask(solicitarEstadoE);
        rolePlan.addTask(seleccionarCancion);
        rolePlan.addTask(busquedaCancionYT);
        rolePlan.addTask(confirmarCancion);
        rolePlan.addTask(repetirCancion);

        RationalRole cambiarCancionRole = new RationalRole(descrip, rolePlan);
        CambiarCancion b= new CambiarCancion(0, cambiarCancionRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CambiarCancion(int id, RationalRole role, String description, GoalBDITypes type) {
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