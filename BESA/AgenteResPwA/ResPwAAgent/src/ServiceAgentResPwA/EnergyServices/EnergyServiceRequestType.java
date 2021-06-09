/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.EnergyServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum EnergyServiceRequestType implements ServiceRequestType{
    ACTIVATEMONITORINGCHARGESERV("ACTIVATEMONITORINGCHARGESERV"),GETBATTERY("GETBATTERY"),GETTEMP("GETTEMP");
    
    private String serviceType;
    
    private EnergyServiceRequestType(String serv)
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
