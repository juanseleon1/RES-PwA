/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.LocationServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum LocationServiceRequestType implements ServiceRequestType{
    SEARCHFREEZONE("SEARCHFREEZONE"),GETFREEZONES("GETFREEZONES"),GETROBOTPOSITION("GETROBOTPOSITION");
    
    private String serviceType;
    
    private LocationServiceRequestType(String serv)
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
