/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import RobotAgentBDI.Believes.PerfilPwA.ActCuenteria;
import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCuento extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarCuento() {
        System.out.println("--- Task Seleccionar Cuento Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cuento ---");
        infoServicio.put("GETEMOTIONSTATE", null);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(HumanServiceRequestType.GETEMOTIONSTATE, infoServicio);
        requestService(srb);
        
        //escoge el cuento
        float gusto = -1;
        Cuento cuentoEleg = null;
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        List<Cuento> cuentos = ((ActCuenteria)blvs.getbPerfilPwA().getPreferencias().getCuenteria()).getCuentos();
        for(Cuento c: cuentos) {
            //escoger cuento
            if(c.getGusto() > gusto){
                cuentoEleg = c;
                gusto = c.getGusto();
            }
        }
        blvs.getbEstadoActividad().setCuentoActual(cuentoEleg);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cuento ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cuento ---");
    }
    
}
