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
import Tareas.LogIn.CargarPerfilPwA;
import Tareas.LogIn.ConversacionInicial;
import Tareas.LogIn.DetectarPwA;
import Tareas.LogIn.IniciarServicios;
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
        CargarPerfilPwA cargarPerfil = new CargarPerfilPwA();
        IniciarServicios iniciarServicios = new IniciarServicios();
        Saludar saludar = new Saludar();
        ConversacionInicial conversacion = new ConversacionInicial();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(detectarPwA);
        rolePlan.addTask(cargarPerfil);
        rolePlan.addTask(iniciarServicios);
        rolePlan.addTask(saludar);
        rolePlan.addTask(conversacion);

        RationalRole reiActRole = new RationalRole(descrip, rolePlan);
        LogIn b= new LogIn(InitRESPwA.getPlanID(), reiActRole, descrip, GoalBDITypes.DUTY);
        return b;
    }

    public LogIn(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta LogIn created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluateViability");
        return 1.0;

    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        if (blvs.getbEstadoInteraccion().isDetectaPwA()) {
            return 1.0;
        }
        
        return 1.0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluatePlausibility");
        return 1.0;

    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta LogIn evaluateContribution");
        return 1.0;
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
