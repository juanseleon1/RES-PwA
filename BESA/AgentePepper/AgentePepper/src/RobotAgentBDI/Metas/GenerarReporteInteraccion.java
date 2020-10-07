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
public class GenerarReporteInteraccion extends GoalBDI{

            private static String descrip;

    public static GenerarReporteInteraccion buildGoal() {

        PersistirInfoInteraccion persistirInfoI = new PersistirInfoInteraccion();
        RecopilarInfoInteraccion recopilarInfoI = new RecopilarInfoInteraccion();
        MostrarInfo mostrarInfo = new MostrarInfo();

        Plan rolePlan= new Plan();

        rolePlan.addTask(persistirInfoI);
        rolePlan.addTask(recopilarInfoI);
        rolePlan.addTask(mostrarInfo);

        RationalRole genRepRole = new RationalRole(descrip, rolePlan);
        GenerarReporteInteraccion b= new GenerarReporteInteraccion(0, genRepRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public GenerarReporteInteraccion(int id, RationalRole role, String description, GoalBDITypes type) {
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
