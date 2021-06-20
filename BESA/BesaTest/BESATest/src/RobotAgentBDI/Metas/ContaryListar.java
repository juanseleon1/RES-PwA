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
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.Contar;
import Tareas.Listar;
import besatest.BESATest;
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
public class ContaryListar extends GoalBDI{
    
    private static String descrip = "cl";

    public static ContaryListar buildGoal() {

        Contar c= new Contar();
        Listar l = new Listar();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan();
        taskList = new ArrayList<>();

        rolePlan.addTask(c);
        taskList.add(l);
        rolePlan.addTask(l, taskList);
        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        ContaryListar b= new ContaryListar(BESATest.getPlanID(), reiActRole, descrip, GoalBDITypes.NEED);
        return b;
    }

    public ContaryListar(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta LogIn created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta LogIn evaluateViability");
        return 1.0;

    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return 1;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1.0;

    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta LogIn evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta LogIn predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return false;
    }
    
}
