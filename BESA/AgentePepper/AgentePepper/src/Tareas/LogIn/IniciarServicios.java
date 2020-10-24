/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.AutonomyServices.AutonomyServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.ServiceEnum;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class IniciarServicios extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public IniciarServicios() {
        System.out.println("--- Task Iniciar Servicios Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Iniciar Servicios ---");
        
        ServiceRequestBuilder srb = null;
        
        infoServicio.put("ACTIVATEBLINKING", true);
        infoServicio.put("ACTIVATELIFESIGNALS", true);
        infoServicio.put("ACTIVATELIFESIGNALSINT", true);
        infoServicio.put("ACTIVATEACTIVEHEARING", true);
        infoServicio.put("ACTIVATESPEAKMOVEMENTS", true);
        infoServicio.put("ACTIVATEPUSHREFLEXES", true);
        infoServicio.put("ACTIVATEBREATHMOV", true);
        infoServicio.put("ACTIVATEMOVDETECTION", true);
        infoServicio.put("ACTIVATEFACEDETEC", true);
        infoServicio.put("ACTIVATECOsLISSIONDETECT", true);
        
        
        infoServicio.put("ACTIVATEMONITORINGCHARGESERV", true);
        infoServicio.put("ACTIVATESTIFFNESS", true);
        infoServicio.put("TABLETON", true);
        infoServicio.put("SETTABLETBRIGHT", 1);
        infoServicio.put("SETTABLETVOL", 10);//depende perfil del usuario 1-15
        infoServicio.put("SETSAYVOLUMEN", 0.5);//depende perfil del usuario 0-1.0
        infoServicio.put("ACTIVATEVOICEEMOANAL", "EmotionAnalysis"); //revisar nombre
        infoServicio.put("ACTVOICERECOG", "SpeechRecognition"); //revisar nombre
        
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Iniciar Servicios ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Iniciar Servicios ---");
    }
    
}
