/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper.HumanServices;

/**
 *
 * @author juans
 */
public enum HumanServiceRequestType {
    HOLA(" ");
    
    private String serviceType;
    
    private HumanServiceRequestType(String serv)
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
