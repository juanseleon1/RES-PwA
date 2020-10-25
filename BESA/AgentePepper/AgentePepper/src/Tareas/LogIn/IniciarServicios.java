/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.AutonomyServices.AutonomyServiceRequestType;
import ServiceAgentResPwA.EnergyServices.EnergyService;
import ServiceAgentResPwA.EnergyServices.EnergyServiceRequestType;
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
public class IniciarServicios extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public IniciarServicios() {
        System.out.println("--- Task Iniciar Servicios Iniciada ---");
    }
    
    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Iniciar Servicios ---");
        
        infoServicio.put("ACTIVATEBLINKING", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATE, infoServicio);
        infoServicio.put("ACTIVATELIFESIGNALS", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATELIFESIGNALS, infoServicio);
        infoServicio.put("ACTIVATELIFESIGNALSINT", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATELIFESGINALSINT, infoServicio);
        infoServicio.put("ACTIVATEACTIVEHEARING", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEACTIVEHEARING, infoServicio);
        infoServicio.put("ACTIVATESPEAKMOVEMENTS", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATESPEAKMOVEMENTS, infoServicio);
        infoServicio.put("ACTIVATEPUSHREFLEXES", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEPUSHREFLEXES, infoServicio);
        infoServicio.put("ACTIVATEBREATHMOV", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACIVATEBREATHMOV, infoServicio);
        infoServicio.put("ACTIVATEMOVDETECTION", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEMOVDETECTION, infoServicio);
        infoServicio.put("ACTIVATEFACEDETEC", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEFACEDETEC, infoServicio);
        infoServicio.put("ACTIVATECOsLISSIONDETECT", true);
        ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATECOLISSIONDETECT, infoServicio);
        infoServicio.clear();
        
        infoServicio.put("ACTIVATEMONITORINGCHARGESERV", true);
        ServiceRequestBuilder.buildRequest(EnergyServiceRequestType.ACTIVATEMONITORINGCHARGESERV, infoServicio);
        infoServicio.clear();
        
        infoServicio.put("ACTIVATESTIFFNESS", true);
        ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.ACTIVATESTIFFNESS, infoServicio);
        infoServicio.clear();
        
        infoServicio.put("TABLETON", true);
        ServiceRequestBuilder.buildRequest(TabletServiceRequestType.TABLETON, infoServicio);
        infoServicio.put("SETTABLETBRIGHT", 1);
        ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETBRIGHT, infoServicio);
        infoServicio.put("SETTABLETVOL", 10);//depende perfil del usuario 1-15
        ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETVOL, infoServicio);
        infoServicio.clear();
        
        infoServicio.put("SETSAYVOLUMEN", 0.5);//depende perfil del usuario 0-1.0
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SETSAYVOLUMN, infoServicio);
        infoServicio.put("ACTIVATEVOICEEMOANAL", "EmotionAnalysis"); //revisar nombre
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTIVATEVOICEEMOANAL, infoServicio);
        infoServicio.put("ACTVOICERECOG", "SpeechRecognition"); //revisar nombre
        ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTVOICERECOG, infoServicio);
        
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
