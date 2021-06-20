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
import Tareas.ABC;
import Tareas.Multiplicar;
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
public class ABCyMultiplicar extends GoalBDI{
    
    private static String descrip = "am";

    public static ABCyMultiplicar buildGoal() {
        ABC abc = new ABC();
        Multiplicar m = new Multiplicar();

        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan();
        taskList = new ArrayList<>();
        
        rolePlan.addTask(abc);
        taskList.add(abc);
        rolePlan.addTask(m, taskList);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        ABCyMultiplicar b= new ABCyMultiplicar(BESATest.getPlanID(), reiActRole, descrip, GoalBDITypes.SURVIVAL);
        return b;
    }

    public ABCyMultiplicar(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ABCyMultiplicar created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ABCyMultiplicar evaluateViability");
        return 1.0;

    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ABCyMultiplicar detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getCont()==25 && !blvs.isTestDone()){
            return 1;
        }else{
            System.out.println("Se salio");
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1.0;

    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ABCyMultiplicar evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return 1.0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ABCyMultiplicar predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.isTestDone();
    }
    
}
