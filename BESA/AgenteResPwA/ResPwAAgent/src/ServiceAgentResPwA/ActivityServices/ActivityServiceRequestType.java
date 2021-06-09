/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.ActivityServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */

public enum ActivityServiceRequestType implements ServiceRequestType{
    RUNANIMATION("RUNANIMATION"),GOTOPOSTURE("GOTOPOSTURE"),DETECTNEWFACE("DETECTNEWFACE"),GETFACELIST("GETFACELIST"), STOPANIMATION("STOPANIMATION"),CHECKANIMATIONFINISH("CHECKANIMATIONFINISH"),PLAYANIMATION("PLAYANIMATION")
    ,KILLALL("KILLALL");
    
    private String serviceType;
    
    private ActivityServiceRequestType(String serv)
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
