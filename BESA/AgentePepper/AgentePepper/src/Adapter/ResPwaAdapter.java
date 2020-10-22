/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import ServiceAgentResPwA.RobotSPAgent;
import ServiceAgentResPwA.ServiceDataRequest;

/**
 *
 * @author juans
 */
public abstract class ResPwaAdapter extends AdapterBESA{
    protected RobotSPAgent rpa;
    protected ResPwaAdapterReceiver receiver;
    protected Thread recvThread;
    protected static int numPackage=0;
    protected  ResPwaAdapter()
    {
        super(null,null);
    }
    
    public RobotSPAgent getRpa() {
        return rpa;
    }

    public void setRpa(RobotSPAgent rpa) {
        this.rpa = rpa;
    }
    public static int sendNewSendable(){
        return numPackage++;
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
     public abstract void sendRequest(ServiceDataRequest data);
    
}
