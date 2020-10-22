/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.RobotStateServices;

import ServiceAgentResPwA.ServiceEnum;

/**
 *
 * @author juans
 */
public enum RobotStateServiceRequestType implements ServiceEnum{
    WAKEUP("WAKEUP"),SUSPEND("SUSPEND"),SETREFRESHTIMESENSORS("SETREFRESHTIMESENSORS"),ACTIVATERASTA("ACTIVATERASTA"),RANDOMEYES("RANDOMEYES"),
    SETLEDSINTENSITY("SETLEDSINTENSITY"),CHANGELEDCOLOR("CHANGELEDCOLOR"),ACTIVATESTIFFNESS("ACTIVATESTIFFNESS");
    
    private String serviceType;
    
    private RobotStateServiceRequestType(String serv)
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
