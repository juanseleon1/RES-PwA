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
import Tareas.MusicoTerapia.ActivarLetra;
import Tareas.MusicoTerapia.BuscarPosicionOptima;
import Tareas.MusicoTerapia.CambiarVelocidadMov;
import Tareas.MusicoTerapia.InicializarBaile;
import Tareas.MusicoTerapia.MostrarFotos;
import Tareas.MusicoTerapia.ReproduccionCancion;
import Tareas.MusicoTerapia.SeleccionarBaile;
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
        
        ActivarLetra aLetra = new ActivarLetra();
        BuscarPosicionOptima bPosicionOptima = new BuscarPosicionOptima();
        CambiarVelocidadMov cVelocidad = new CambiarVelocidadMov();
        InicializarBaile iBaile = new InicializarBaile();
        MostrarFotos mFotos = new MostrarFotos();
        RecibirRetroalimentacion retro = new RecibirRetroalimentacion();
        ReproduccionCancion rCancion = new ReproduccionCancion();
        SeleccionarBaile sBaile = new SeleccionarBaile();
        SeleccionarCancion sCancion = new SeleccionarCancion();
        
        List<String> resources = new ArrayList<>();
        List<Task> tarea = new ArrayList<>();
        Plan rolePlan = new Plan();
        
        rolePlan.addTask(sCancion);
        tarea.add(sCancion);
        rolePlan.addTask(bPosicionOptima,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(sCancion);
        rolePlan.addTask(rCancion,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCancion);
        rolePlan.addTask(mFotos,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCancion);
        rolePlan.addTask(aLetra,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(rCancion);
        tarea.add(bPosicionOptima);
        rolePlan.addTask(iBaile,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(iBaile);
        rolePlan.addTask(sBaile,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(iBaile);
        rolePlan.addTask(cVelocidad,tarea);
        
        tarea = new ArrayList<>();
        tarea.add(sCancion);
        rolePlan.addTask(retro,tarea);
        
        RationalRole musicTherapyRole = new RationalRole(descrip, rolePlan);
        MusicoTerapia b = new MusicoTerapia(InitRESPwA.getPlanID(), musicTherapyRole, descrip, GoalBDITypes.DUTY);
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
        //System.out.println("Meta MusicoTerapia detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isSistemaSuspendido() && blvs.getbEstadoInteraccion().isLogged()) {
            if(blvs.getbEstadoActividad().getActividadActual()!=null && (blvs.getbEstadoActividad().getActividadActual().equals(ResPwAActivity.MUSICOTERAPIA)) && !blvs.getbEstadoActividad().isFinalizoActividad()
                    && blvs.getbEstadoEmocionalPwA().getEmocionPredominante()!=null && (blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.SADNESS) || blvs.getbEstadoEmocionalPwA().getEmocionPredominante().equals(EmotionPwA.ANGER))) {
                return 1;
            }
        }
        return 0;
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
        
        return valor+blvs.getbEstadoEmocionalPwA().getTiempoEmocionPredominante();
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MusicoTerapia predictResultUnlegability");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta MusicoTerapia evaluateViability");
        return true;
    }
}
