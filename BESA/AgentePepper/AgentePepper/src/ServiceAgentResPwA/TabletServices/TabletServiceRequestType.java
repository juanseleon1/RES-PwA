/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.TabletServices;

import ServiceAgentResPwA.ServiceEnum;

/**
 *
 * @author juans
 */
public enum TabletServiceRequestType implements ServiceEnum{
    HOLA(" ");
    
    private String serviceType;
    
    private TabletServiceRequestType(String serv)
    {
        serviceType=serv;
    }
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
}
