/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;


import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
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
public class SeleccionarCancion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public SeleccionarCancion() {
//        System.out.println("--- Task Seleccionar Cancion Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");
        
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now()); 
        blvs.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        
        float gusto = -1;
        Cancion cancionEleg = null;
        List<Cancion> canciones = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getCancionList();
        for(Cancion c: canciones) {
            
            if( c.getGusto()*0.7 + c.getGeneroGenero().getGusto()*0.3 <= gusto){
                cancionEleg = c;
                gusto = (float) (c.getGusto()*0.7 + c.getGeneroGenero().getGusto()*0.3);
            }
        }
        blvs.getbEstadoActividad().setCancionActual(cancionEleg);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setCancionActual(null);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoActividad().getCancionActual()!=null) {
            return true;
        }
        return false;
    }
    
}
