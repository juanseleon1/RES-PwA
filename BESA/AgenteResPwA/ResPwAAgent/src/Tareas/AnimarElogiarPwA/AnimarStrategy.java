/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AnimarElogiarPwA;

import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class AnimarStrategy implements ResPwAStrategy{
    
    private String nombre;
    
    @Override
    public ServiceDataRequest execStrategy() {
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        switch (nombre)
        {
            case "fraseElogiante":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "chiste":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "adivinanza":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "datoCurioso":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "preguntaEmpatica":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "consejo":
                infoServicio.put("SAY", nombre);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case "llamarPwa":
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

    @Override
    public ServiceDataRequest execStrategy(Believes b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
