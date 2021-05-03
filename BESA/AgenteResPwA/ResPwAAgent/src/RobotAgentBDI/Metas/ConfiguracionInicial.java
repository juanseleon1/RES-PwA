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
import EmotionalAnalyzerAgent.EmotionPwA;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Init.InitRESPwA;
import Tareas.ConfiguracionInicial.CreacionJSON;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author maria.f.garces.cala
 */
public class ConfiguracionInicial extends GoalBDI {

    private static String descrip = "ConfiguracionInicial";

    public static ConfiguracionInicial buildGoal() {

        CreacionJSON creacionJSON = new CreacionJSON();
        
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();
        Plan rolePlan = new Plan();

        rolePlan.addTask(creacionJSON);

        RationalRole confInicialRole = new RationalRole(descrip, rolePlan);
        ConfiguracionInicial b= new ConfiguracionInicial(InitRESPwA.getPlanID(), confInicialRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    
    public ConfiguracionInicial(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ConfiguracionInicial created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConfiguracionInicial evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial goalSucceeded");
        return true;
    }

}
