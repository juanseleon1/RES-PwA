/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.TabletServices;

import ServiceAgentResPwA.Guard.ServiceRequestType;

/**
 *
 * @author juans
 */
public enum TabletServiceRequestType implements ServiceRequestType{
    TABLETON("TABLETON"),WAKETABLET("WAKETABLET"),SUSPENDTABLET("SUSPENDTABLET"),TABLETOFF("TABLETOFF"),SHOWVIDEO("SHOWVIDEO"),QUITVIDEO("QUITVIDEO"),
    PAUSEVIDEO("PAUSEVIDEO"),RESUMEVIDEO("RESUMEVIDEO"),PRELOADIMG("PRELOADIMG"),SHOWIMG("SHOWIMG"),HIDEIMG("HIDEIMG"),SETTABLETBRIGHT("SETTABLETBRIGHT"),
    SETTABLETVOL("SETTABLETVOL"),LOADIMGLIST("LOADIMGLIST");
    
    private String serviceType;
    
    private TabletServiceRequestType(String serv)
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
