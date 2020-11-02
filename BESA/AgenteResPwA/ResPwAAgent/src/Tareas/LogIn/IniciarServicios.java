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
        ServiceDataRequest srb = null;
        
        infoServicio.put("ACTIVATEBLINKING", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATE, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATELIFESIGNALS", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATELIFESIGNALS, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATELIFESIGNALSINT", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATELIFESGINALSINT, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEACTIVEHEARING", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEACTIVEHEARING, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATESPEAKMOVEMENTS", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATESPEAKMOVEMENTS, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEPUSHREFLEXES", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEPUSHREFLEXES, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEBREATHMOV", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACIVATEBREATHMOV, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEMOVDETECTION", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEMOVDETECTION, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEFACEDETEC", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATEFACEDETEC, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATECOLISSIONDETECT", true);
        srb = ServiceRequestBuilder.buildRequest(AutonomyServiceRequestType.ACTIVATECOLISSIONDETECT, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        infoServicio.put("ACTIVATEMONITORINGCHARGESERV", true);
        srb = ServiceRequestBuilder.buildRequest(EnergyServiceRequestType.ACTIVATEMONITORINGCHARGESERV, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        infoServicio.put("ACTIVATESTIFFNESS", true);
        srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.ACTIVATESTIFFNESS, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("SETREFRESHTIMESENSORS", null);
        srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.SETREFRESHTIMESENSORS, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        infoServicio.put("TABLETON", true);
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.TABLETON, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("SETTABLETBRIGHT", 1);
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETBRIGHT, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("SETTABLETVOL", 10);//depende perfil del usuario 1-15
        srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SETTABLETVOL, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        
        infoServicio.put("SETSAYVOLUMEN", 0.5);//depende perfil del usuario 0-1.0
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SETSAYVOLUMN, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTIVATEVOICEEMOANAL", "EmotionAnalysis"); //revisar nombre
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTIVATEVOICEEMOANAL, infoServicio);
        requestService(srb);
        infoServicio = new HashMap<>();
        infoServicio.put("ACTVOICERECOG", "SpeechRecognition"); //revisar nombre
        srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.ACTVOICERECOG, infoServicio);
        requestService(srb);
        
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
