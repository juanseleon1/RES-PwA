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
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class AtencionStrategy implements ResPwAStrategy{
    
    private OpcionesAtencion opcion;
    
    @Override
    public ServiceDataRequest execStrategy() {
        HashMap<String,Object> infoServicio = new HashMap<>(); 
        ServiceDataRequest srb = null;
        switch (opcion)
        {
            case SILBAR:
                infoServicio.put("SAY", opcion);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
            case LLAMARPWA:
                infoServicio.put("SAY", opcion);
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                break;
        }
        return srb;
    }

    public OpcionesAtencion getOpcion() {
        return opcion;
    }

    public void setNombre(OpcionesAtencion opcion) {
        this.opcion = opcion;
    }

    @Override
    public ServiceDataRequest execStrategy(Believes b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
