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
import Tareas.AsegurarConexionInternet.AsegurarConexion;
import java.util.Random;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 *
 * @author mafegarces
 */
public class AsegurarConexionInternet extends GoalBDI{
    
    private static String descrip = "AsegurarConexionInternet";
    
    public static AsegurarConexionInternet buildGoal() {
        AsegurarConexion ms = new AsegurarConexion();
        Plan rolePlan= new Plan();
        rolePlan.addTask(ms);
        
        RationalRole interSocial = new RationalRole(descrip, rolePlan);
        AsegurarConexionInternet b = new AsegurarConexionInternet(InitRESPwA.getPlanID(), interSocial, descrip, GoalBDITypes.SURVIVAL);
        return b;
    }
    
    public AsegurarConexionInternet(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta AsegurarConexionInternet created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AsegurarConexionInternet evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta AsegurarConexionInternet detectGoal");
        //meta mas importante
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoRobot().isConexionInternet() && !blvs.getbEstadoRobot().getBateria() && blvs.getbEstadoInteraccion().isSistemaSuspendido())
        {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AsegurarConexionInternet evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AsegurarConexionInternet evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AsegurarConexionInternet predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta AsegurarConexionInternet goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getbEstadoRobot().isConexionInternet();
    }
    
}
