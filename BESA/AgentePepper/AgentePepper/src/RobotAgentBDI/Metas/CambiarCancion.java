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
import RobotAgentBDI.Believes.PerfilPwA.ActMusicoterapia;
import Init.InitRESPwA;
import RobotAgentBDI.ResPwAActivity;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Tareas.CambiarCancion.RecibirRetroalimentacion;
import Tareas.CambiarCancion.*;
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
public class CambiarCancion extends GoalBDI{

        private static String descrip;

    public static CambiarCancion buildGoal() {
    
        BusquedaCancionYoutube busquedaCancionYT = new BusquedaCancionYoutube();
        RepetirCancion repetirCancion = new RepetirCancion();
        SeleccionarCancion seleccionarCancion = new SeleccionarCancion();
        RecibirRetroalimentacion recibirRetroalimentacion = new RecibirRetroalimentacion();

        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();
        Plan rolePlan= new Plan(tarea,resources,null);

        rolePlan.addTask(seleccionarCancion); //evaluar estado emocional, si no es la primera cancion, saltar contenido de esa tarea
        rolePlan.addTask(busquedaCancionYT); //buscar API o desde la BD y se envia
        rolePlan.addTask(repetirCancion); //preguntar si quiere una canción, inflar funcion contribucion cambiar cancion
        rolePlan.addTask(recibirRetroalimentacion); //cuando acabe la canción pedir retroalimentacion

        RationalRole cambiarCancionRole = new RationalRole(descrip, rolePlan);
        CambiarCancion b= new CambiarCancion(InitRESPwA.getPlanID(), cambiarCancionRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    public CambiarCancion(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        System.out.println("Meta CambiarCancion created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion evaluateViability");
        return 0;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion detectGoal");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        
        //cambiar strings y numero
        if((blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.SADNESS) || blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.ANGER)) && 
                blvs.getbEstadoActividad().getActividadActual().equals(ResPwAActivity.MUSICOTERAPIA) && blvs.getbPerfilPwA().getPreferencias().isGustoMusica()) {
            return 1.0;
        }
        
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion evaluatePlausibility");
        return 0;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion evaluateContribution");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        
        //valenciaIra + valenciaTriste
        return blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante()+((ActMusicoterapia)blvs.getbPerfilPwA().getPreferencias().getActividadesSis().get("ActMusicoterapia")).getCancionEscogida().getGusto();

    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion predictResultUnlegality");
        return false;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta CambiarCancion goalSucceeded");
        return false;
    }
    
}
