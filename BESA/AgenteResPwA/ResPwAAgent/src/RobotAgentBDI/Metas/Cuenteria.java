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
import ResPwAEntities.Actxpreferencia;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.Utils.ResPwAActivity;
import Tareas.Retroalimentacion.RecibirRetroalimentacionCuento;
import Tareas.Cuenteria.SeleccionarCuento;
import Tareas.Cuenteria.ReproducirCuento;
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
public class Cuenteria extends GoalBDI {

    private static String descrip = "Cuenteria";

    public static Cuenteria buildGoal() {
        RecibirRetroalimentacionCuento retro = new RecibirRetroalimentacionCuento();
        SeleccionarCuento recomCuento = new SeleccionarCuento();
        ReproducirCuento rCuento = new ReproducirCuento();

        List<Task> taskList;
        Plan rolePlan = new Plan();

        rolePlan.addTask(recomCuento);
        
        taskList = new ArrayList<>();
        taskList.add(recomCuento);
        rolePlan.addTask(rCuento, taskList);

        taskList = new ArrayList<>();
        taskList.add(rCuento);
        rolePlan.addTask(retro, taskList);


        RationalRole cuenteriaRole = new RationalRole(descrip, rolePlan);
        Cuenteria b = new Cuenteria(InitRESPwA.getPlanID(), cuenteriaRole, descrip, GoalBDITypes.OPORTUNITY);
        return b;
    }

    public Cuenteria(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta Cuenteria detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;

        if (!blvs.getbEstadoInteraccion().isSistemaSuspendido() && blvs.getbEstadoInteraccion().isLogged()&& blvs.getbPerfilPwA().getPerfil().getPerfilMedico().getFast() <= 5) {
            if (blvs.getbEstadoEmocionalPwA().getAtencion() < 0.4 && blvs.getbEstadoEmocionalPwA().getRelajacion() < 0.6) {
                return 0.4 + (blvs.getbEstadoActividad().getGustoActividad(ResPwAActivity.CUENTERIA)*0.6);

            }
        }
        return 1;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluateContribution");
        RobotAgentBelieves blvs = (RobotAgentBelieves) stateBDI.getBelieves();
        List<Actxpreferencia> listaAct = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActxpreferenciaList();
        double valor = 0;

        for (Actxpreferencia act : listaAct) {
            if (act.getActividadpwa().getNombre().equalsIgnoreCase(ResPwAActivity.CUENTERIA.toString())) {
                valor = act.getGusto();
            }
        }       

        return valor+1;

    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria predictResultUnlegability");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta Cuenteria evaluateViability");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if ((System.currentTimeMillis() - blvs.getbEstadoActividad().calcTiempoActividad()) >= 300 && (blvs.getbEstadoEmocionalPwA().getAtencion()>0.5 || blvs.getbEstadoEmocionalPwA().getRelajacion()> 0.7)) {

            return true;
        }
        return false;
    }
}
