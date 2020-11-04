/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import ResPwAEntities.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class RecomendarCuento extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    
    public RecomendarCuento() {
//        System.out.println("--- Task Recomendar Cuento Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recomendar Cuento ---");
        //buscar cuento
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        float gusto = -1;
        Cuento cuentoEleg = null;
        List<Cuento> cuentos = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getCuentoList();
        for(Cuento c: cuentos) {
            if( c.getGusto()*0.7 + c.getGeneroGenero().getGusto()*0.3 <= gusto && !c.equals(blvs.getbEstadoActividad().getCuentoActual())){
                cuentoEleg = c;
                gusto = (float) (c.getGusto()*0.7 + c.getGeneroGenero().getGusto()*0.3);
            }
        }
        blvs.getbEstadoActividad().setCuentoActual(cuentoEleg);
        
        infoServicio.put("SAY", cuentoEleg);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
        requestService(srb);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recomendar Cuento ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recomendar Cuento ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando()) {
            return true;
        }
        return false;
    }
    
}
