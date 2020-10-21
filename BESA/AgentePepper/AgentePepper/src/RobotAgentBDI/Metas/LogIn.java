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
import Init.RunAgentePepper;
import Tareas.LogIn.DetectarPwA;
import Tareas.LogIn.Saludar;
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
public class LogIn extends GoalBDI{
    
    private static String descrip;

    public static LogIn buildGoal() {

        DetectarPwA detectarPwA = new DetectarPwA();
        Saludar saludar = new Saludar();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(detectarPwA);
        rolePlan.addTask(saludar);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        LogIn b= new LogIn(RunAgentePepper.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public LogIn(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta LogIn created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn goalSucceeded");
        return false;
    }
    
}
