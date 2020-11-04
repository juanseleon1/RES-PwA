/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.SeleccionarCuentoGusto;

import ResPwAEntities.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCuento extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarCuento() {
//        System.out.println("--- Task Seleccionar Cuento Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cuento ---");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now()); 
        blvs.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        
        //escoge el cuento
        float gusto = -1;
        Cuento cuentoEleg = null;
        List<Cuento> cuentos = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getCuentoList();
        for(Cuento c: cuentos) {
            //escoger cuento
            
            if( c.getGusto()*0.7+c.getGeneroGenero().getGusto()*0.3 <= gusto){
                cuentoEleg = c;
                gusto = (float) (c.getGusto()*0.7 + c.getGeneroGenero().getGusto()*0.3);
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

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoActividad().getCuentoActual() != null) {
            return true;
        }
        return false;
    }
    
}
