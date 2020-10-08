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
import Tareas.EntrarModoKaraoke.ActivarSubtitulos;
import Tareas.EntrarModoKaraoke.BuscarLetra;
import Tareas.EntrarModoKaraoke.EvaluarPerfilPwA;
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
public class EntrarModoKaraoke extends GoalBDI{

    private static String descrip;

    public static EntrarModoKaraoke buildGoal() {

        ActivarSubtitulos activarSubtitulos = new ActivarSubtitulos();
        BuscarLetra buscarLetras = new BuscarLetra();
        EvaluarPerfilPwA evaluarPerfil = new EvaluarPerfilPwA();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        
        rolePlan.addTask(evaluarPerfil);
        rolePlan.addTask(buscarLetras);
        rolePlan.addTask(activarSubtitulos);

        RationalRole karaokeRole = new RationalRole(descrip, rolePlan);
        EntrarModoKaraoke b= new EntrarModoKaraoke(0, karaokeRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public EntrarModoKaraoke(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta EntrarModoKaraoke created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke detectGoal");
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluateContribution");
        return 0;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke goalSucceeded");
        return false;
    }
    
}
