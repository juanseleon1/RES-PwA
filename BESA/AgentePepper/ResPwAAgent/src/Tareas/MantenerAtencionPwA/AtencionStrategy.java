/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MantenerAtencionPwA;

import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author mafegarces
 */
public class AtencionStrategy implements ResPwAStrategy{
    
    private String nombre;
    
    @Override
    public ServiceDataRequest execStrategy() {
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        switch (nombre)
        {
            case "Adivinanza":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "Dato Curioso":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "Cambiar Actividad":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "Cambiar Cancion":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "Cambiar Cuento":
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
