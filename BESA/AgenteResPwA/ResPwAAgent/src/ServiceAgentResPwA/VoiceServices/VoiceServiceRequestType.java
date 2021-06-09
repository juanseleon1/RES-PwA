/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.VoiceServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum VoiceServiceRequestType implements ServiceRequestType{
    SAY("SAY"),SETSAYVOLUME("SETSAYVOLUME"),SAYWITHMOVEMENT("SAYWITHMOVEMENT"),SETSYSTEMVOLUME("SETSYSTEMVOLUME"),PLAYSOUND("PLAYSOUND"),
    PAUSESOUND("PAUSESOUND"),ACTIVATEVOICEEMOANAL("ACTIVATEVOICEEMOANAL"),DESACTIVVOICEEMOANAL("DESACTIVVOICEEMOANAL"),ACTVOICERECOG("ACTVOICERECOG"),
    DESACTVOICERECOG("DESACTVOICERECOG"),ACTIVATECONVTOPIC("ACTIVATECONVTOPIC"),LOADCONVTOPIC("LOADCONVTOPIC"),UNLOADCONVTOPIC("UNLOADCONVTOPIC"),DEACTCONVTOPIC("DEACTCONVTOPIC"),SAYUNDERTOPICCONTEXT("SAYUNDERTOPICCONTEXT"),
    SETTOPICFOCUS("SETTOPICFOCUS"),STOPALL("STOPALL"),FORCEOUT("FORCEOUT");
    
    private String serviceType;
    
    private VoiceServiceRequestType(String serv)
    {
        serviceType=serv;
    }
    @Override
    public String getServiceType() {
        return serviceType;
    }

    @Override
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
}
