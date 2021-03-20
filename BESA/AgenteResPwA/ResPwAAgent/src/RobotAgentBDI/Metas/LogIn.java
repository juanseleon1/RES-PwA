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
import RobotAgentBDI.ResPwAActivity;
import Tareas.LogIn.ConversacionInicial;
import Tareas.LogIn.DetectarPwA;
import Tareas.LogIn.IniciarServicios;
import Tareas.LogIn.LogInTask;
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
    
    private static String descrip = "LogIn";

    public static LogIn buildGoal() {

        //falta mirar iniciarServicios
        DetectarPwA detectarPwA = new DetectarPwA();
        IniciarServicios iniciarServicios = new IniciarServicios();
        Saludar saludar = new Saludar();
        ConversacionInicial conversacion = new ConversacionInicial();
        LogInTask loginT = new LogInTask();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan();
        rolePlan.addTask(detectarPwA);
        taskList = new ArrayList<>();
        taskList.add(detectarPwA);
        rolePlan.addTask(loginT,taskList);
        taskList = new ArrayList<>();
        taskList.add(loginT);
        rolePlan.addTask(iniciarServicios,taskList);
        taskList = new ArrayList<>();
        taskList.add(loginT);
        rolePlan.addTask(saludar,taskList);
        taskList = new ArrayList<>();
        taskList.add(saludar);
        rolePlan.addTask(conversacion,taskList);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        LogIn b= new LogIn(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public LogIn(int id, RationalRole role, String description, GoalBDITypes type) {
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
        System.out.println("LogIn Params: "+blvs.getbEstadoInteraccion().isDetectaPwA()+ "LogIn Params: "+ blvs.getbEstadoInteraccion().isLogged());
        if (blvs.getbEstadoInteraccion().isDetectaPwA() && blvs.getbEstadoInteraccion().isLogged()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta LogIn evaluatePlausibility");
        return 1.0;

    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        return 1.0 + blvs.getbEstadoActividad().getBoostLogIn();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn goalSucceeded");
        return false;
    }
    
}
