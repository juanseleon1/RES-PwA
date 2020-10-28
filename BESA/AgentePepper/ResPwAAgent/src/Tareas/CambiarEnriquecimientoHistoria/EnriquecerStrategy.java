/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityService;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class EnriquecerStrategy implements ResPwAStrategy{
    
    private String nombre;
    
    @Override
    public ServiceDataRequest execStrategy() {
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        switch (nombre)
        {
            case "Luces":
                infoServicio.put("CHANGELEDCOLOR", nombre);
                srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.CHANGELEDCOLOR, infoServicio);
                break;
            case "Pantalla":
                //enviar imagen
                infoServicio.put("SHOWIMGENRIQUECER", nombre);
                srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
                break;
            case "Bailar":
                infoServicio.put("RUNANIMATION", nombre);
                srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.RUNANIMATION, infoServicio);
                break;
            case "Frase":
                //enviar frase
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
        }
        return srb;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
