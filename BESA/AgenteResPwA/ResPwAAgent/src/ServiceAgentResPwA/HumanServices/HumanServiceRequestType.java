/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.HumanServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum HumanServiceRequestType implements ServiceRequestType{
    GETEMOTIONSTATE("GETEMOTIONSTATE"),LOGIN("LOGIN"),WAVINGDETECTION("WAVINGDETECTION");
    
    private String serviceType;
    
    private HumanServiceRequestType(String serv)
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
