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
import ServiceAgentResPwA.RobotSPAgent;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.ServiceRequestType;
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
            serv=RobotSPAgent.servActividades;
        }else if( e instanceof AutonomyServiceRequestType)
        {
            serv=RobotSPAgent.servAutonomia;
        }else if( e instanceof EnergyServiceRequestType)
        {
            serv=RobotSPAgent.servEnergia;
        }else if( e instanceof HumanServiceRequestType)
        {
            serv=RobotSPAgent.servHumanos;
        }else if( e instanceof LocationServiceRequestType)
        {
            serv=RobotSPAgent.servLocation;
        }else if( e instanceof MovementServiceRequestType)
        {
            serv=RobotSPAgent.servMovimiento;
        }else if( e instanceof RobotStateServiceRequestType)
        {
            serv=RobotSPAgent.servEstadoRobot;
        }else if( e instanceof TabletServiceRequestType)
        {
            serv=RobotSPAgent.servTablet;
        }else            
        {
            serv=RobotSPAgent.servVoz;
        } 
        ServiceDataRequest sdr = new ServiceDataRequest(serv, e.getServiceType(), params);
//        System.out.println("SDR Builded "+serv+"  "+e.getServiceType());
        return sdr;
    }
}
