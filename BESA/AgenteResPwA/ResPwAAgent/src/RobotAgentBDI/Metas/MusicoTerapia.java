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
import Init.InitRESPwA;
import ResPwAEntities.Actxpreferencia;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAActivity;
import Tareas.Cuenteria.RecibirRetroalimentacion;
import Tareas.MusicoTerapia.InicializarBaile;
import Tareas.MusicoTerapia.ReproduccionCancion;
import Tareas.MusicoTerapia.SeleccionarCancion;
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
public class MusicoTerapia extends GoalBDI{
    
    private static String descrip = "MusicoTerapia";
    
    public static MusicoTerapia buildGoal() {
        
        InicializarBaile iBaile = new InicializarBaile();
        SeleccionarCancion sCancion = new SeleccionarCancion();
        ReproduccionCancion rCancion = new ReproduccionCancion();
        RecibirRetroalimentacion retro = new RecibirRetroalimentacion();
        
        List<String> resources = new ArrayList<>();
        List<Task> tarea;
        Plan rolePlan = new Plan();

        rolePlan.addTask(iBaile);
        rolePlan.addTask(sCancion);
        tarea = new ArrayList<>();
        tarea.add(sCancion);
        tarea.add(iBaile);
        rolePlan.addTask(rCancion,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCancion);
        rolePlan.addTask(retro,tarea);

        
        RationalRole musicTherapyRole = new RationalRole(descrip, rolePlan);
        MusicoTerapia b = new MusicoTerapia(InitRESPwA.getPlanID(), musicTherapyRole, descrip, GoalBDITypes.OPORTUNITY);
        return b;
    }
    
    public MusicoTerapia(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta MusicoTerapia created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MusicoTerapia detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        System.out.println("EmocionPredominante: "+blvs.getbEstadoEmocionalPwA().getEmocionPredominante());

        if(!blvs.getbEstadoInteraccion().isSistemaSuspendido() && blvs.getbEstadoInteraccion().isLogged()) {
            if(blvs.getbEstadoEmocionalPwA().getEmocionPredominante()<0) {
                return 1;
            }
        }
        return 1;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves)stateBDI.getBelieves();
        List<Actxpreferencia> listaAct = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActxpreferenciaList();
        double valor=0;
        
        for (Actxpreferencia act: listaAct) {
            if(act.getActividadpwa().getNombre().equals(ResPwAActivity.MUSICOTERAPIA)) {
                valor = act.getGusto();
            }
        }
        System.out.println("Gusto: "+valor);


        return valor;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia predictResultUnlegability");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia evaluateViability");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if((System.currentTimeMillis()-blvs.getbEstadoActividad().calcTiempoActividad()) >= 300 && blvs.getbEstadoEmocionalPwA().getEmocionPredominante() >0){
            return true;
        }
        return false;
    }
}
