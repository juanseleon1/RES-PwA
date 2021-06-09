/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.ServiceRequestDataBuilder;

import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.AutonomyServices.AutonomyServiceRequestType;
import ServiceAgentResPwA.EnergyServices.EnergyServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.Agent.ServiceAgentRESPwA;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.Guard.ServiceRequestType;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import java.util.HashMap;

/**
 *
 * @author juans
 */
public class ServiceRequestBuilder {
    
    public static ServiceDataRequest buildRequest(ServiceRequestType e, HashMap<String,Object> params)
    {
        String serv=null;
        if( e instanceof ActivityServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servActividades;
        }else if( e instanceof AutonomyServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servAutonomia;
        }else if( e instanceof EnergyServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servEnergia;
        }else if( e instanceof HumanServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servHumanos;
        }else if( e instanceof LocationServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servLocation;
        }else if( e instanceof MovementServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servMovimiento;
        }else if( e instanceof RobotStateServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servEstadoRobot;
        }else if( e instanceof TabletServiceRequestType)
        {
            serv=ServiceAgentRESPwA.servTablet;
        }else            
        {
            serv=ServiceAgentRESPwA.servVoz;
        } 
        ServiceDataRequest sdr = new ServiceDataRequest(serv, e.getServiceType(), params);
//        System.out.println("SDR Builded "+serv+"  "+e.getServiceType());
        return sdr;
    }
}
