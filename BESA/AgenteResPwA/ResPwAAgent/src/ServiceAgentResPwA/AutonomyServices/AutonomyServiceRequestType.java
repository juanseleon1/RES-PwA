/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.AutonomyServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum AutonomyServiceRequestType implements ServiceRequestType{
    ACTIVATELIFESGINALSINT("ACTIVATELIFESGINALSINT"),ACTIVATE("ACTIVATE"),ACTIVATELIFESIGNALS("ACTIVATELIFESIGNALS"),DEFENGAGEMENTTYPE("DEFENGAGEMENTTYPE"),
    ACTIVATEPUSHREFLEXES("ACTIVATEPUSHREFLEXES"),ACTIVATEBREATHMOV("ACTIVATEBREATHMOV"),ACTIVATEMOVDETECTION("ACTIVATEMOVDETECTION"),
    ACTIVATEFACEDETEC("ACTIVATEFACEDETEC"),ACTIVATECOLISSIONDETECT("ACTIVATECOLISSIONDETECT"),ACTIVATEACTIVEHEARING("ACTIVATEACTIVEHEARING"),ACTIVATESPEAKMOVEMENTS("ACTIVATESPEAKMOVEMENTS");
    
    private String serviceType;
    
    private AutonomyServiceRequestType(String serv)
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
