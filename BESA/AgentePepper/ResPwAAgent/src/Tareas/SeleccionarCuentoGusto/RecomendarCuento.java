/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import RobotAgentBDI.Believes.PerfilPwA.ActCuenteria;
import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
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
        System.out.println("--- Task Recomendar Cuento Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recomendar Cuento ---");
        //buscar cuento
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        List<Cuento> cuentos = ((ActCuenteria)blvs.getbPerfilPwA().getPreferencias().getCuenteria()).getCuentos();
        for(Cuento c: cuentos) {
            //escoger cuento
        }
        infoServicio.put("SAY", "nombreCuentoElegido");
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recomendar Cuento ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recomendar Cuento ---");
    }
    
}
