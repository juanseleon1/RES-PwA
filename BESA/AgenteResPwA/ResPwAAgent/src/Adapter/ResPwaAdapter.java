/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import ServiceAgentResPwA.Agent.ServiceAgentRESPwA;

/**
 *
 * @author juans
 */
public abstract class ResPwaAdapter extends AdapterBESA{
    protected ServiceAgentRESPwA rpa;
    protected final int robotPort=7896;
    protected final String IP= "127.0.0.1"; 
    protected ResPwaAdapterReceiver receiver;
    protected static int numPackage=0;
    protected static ServiceMapper serviceMapper;
    protected  ResPwaAdapter()
    {
        super(null,null);
    }
    
    public ServiceAgentRESPwA getRpa() {
        return rpa;
    }

    public void setRpa(ServiceAgentRESPwA rpa) {
        this.rpa = rpa;
    }
    public synchronized static int sendNewSendable(){
        return numPackage++;
    }

    public ResPwaAdapterReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(ResPwaAdapterReceiver receiver) {
        this.receiver = receiver;
    }

    public static ServiceMapper getServiceMapper() {
        return serviceMapper;
    }

    public static void setServiceMapper(ServiceMapper serviceMapper) {
        ResPwaAdapter.serviceMapper = serviceMapper;
    }
    
    
     public abstract void ActivityServiceReqAsync(SPServiceDataRequest data);
     public abstract void AutonomyServiceReqAsync(SPServiceDataRequest data);
     public abstract void EnergyServiceReqAsync(SPServiceDataRequest data);
     public abstract void HumanServiceReqAsync(SPServiceDataRequest data);
     public abstract void LocationServiceReqAsync(SPServiceDataRequest data);
     public abstract void RobotStateServiceReqAsync(SPServiceDataRequest data);
     public abstract void VoiceServiceReqAsync(SPServiceDataRequest data);
     public abstract void MovementServiceReqAsync(SPServiceDataRequest data);
     public abstract void TabletServiceReqAsync(SPServiceDataRequest data);
    
}
