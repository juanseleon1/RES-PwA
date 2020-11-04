/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityService;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EnriquecerStrategy implements ResPwAStrategy{
    
    private int nombre;
    
    @Override
    public ServiceDataRequest execStrategy(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        switch (nombre)
        {
            case 0: //hablar
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case 1: //movimiento
                blvs.getbEstadoActividad().getCuentoActual().getFrasesList();
                infoServicio.put("RUNANIMATION", nombre);
                infoServicio.put("FACTOR", blvs.getbEstadoRobot().getVelocidad());
                srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
                break;
            case 2:
                //falta escoger imagenes
                infoServicio.put("SHOWIMG", nombre);
                srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
                break;
            case 3: //luces
                infoServicio.put("CHANGELEDCOLOR", nombre);
                srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.CHANGELEDCOLOR, infoServicio);
                break;
        }
        return srb;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int num) {
        this.nombre = num;
    }

    @Override
    public ServiceDataRequest execStrategy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}