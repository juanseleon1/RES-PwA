/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.MovementServices;

import ServiceAgentResPwA.ServiceEnum;

/**
 *
 * @author juans
 */
public enum MovementServiceRequestType implements ServiceEnum{
    MOVE("MOVE"),MOVEFORWARD("MOVEFORWARD"),MOVETO("MOVETO"),MOVETOPOSITION("MOVETOPOSITION");
    
    private String serviceType;
    
    private MovementServiceRequestType(String serv)
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
