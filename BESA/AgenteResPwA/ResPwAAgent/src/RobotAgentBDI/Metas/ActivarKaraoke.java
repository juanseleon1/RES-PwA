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
import Tareas.ActivarKaraoke.MoverseFrentePwA;
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
public class ActivarKaraoke extends GoalBDI{

    private static String descrip = "ActivarKaraoke";

    public static ActivarKaraoke buildGoal() {

        MoverseFrentePwA activarSubtitulos = new MoverseFrentePwA();
        List<String> resources = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        
        Plan rolePlan= new Plan(taskList, resources, null);

        rolePlan.addTask(activarSubtitulos); //se buscan y activan
        
        RationalRole karaokeRole = new RationalRole(descrip, rolePlan);
        ActivarKaraoke b= new ActivarKaraoke(InitRESPwA.getPlanID(), karaokeRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public ActivarKaraoke(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta EntrarModoKaraoke created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //tiempoActMusical>30sec && letraDisponibleCancion && (PwAQuiereCantar||perfil.gustosKaraoke)
        //List<Cancion> canciones = ((ActMusicoterapia)blvs.getbPerfilPwA().getPreferencias().getMusicoterapia()).getCanciones(); -> si tiene letra solo se puede con el API
        if(blvs.getbEstadoActividad().getActividadActual().equals(ResPwAActivity.MUSICOTERAPIA) && blvs.getbEstadoActividad().calcTiempoActividad() > 30 && 
                (blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustokaraoke()>0.5 || blvs.getbEstadoInteraccion().isQuiereCantar()) && !blvs.getbEstadoInteraccion().isEstaBailando() && !blvs.getbEstadoActividad().isFinalizoActividad()) {
            return 1.0;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        
        //PwA quiere cantar + letraDisponibleCancion
        if(blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustokaraoke()>0.5 || blvs.getbEstadoInteraccion().isQuiereCantar()) {
            return 1.0 + blvs.getbEstadoActividad().getBoostActivarKaraoke();
        }
        
        return blvs.getbEstadoActividad().getBoostActivarKaraoke();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta EntrarModoKaraoke goalSucceeded");
        return true;
    }
    
}
