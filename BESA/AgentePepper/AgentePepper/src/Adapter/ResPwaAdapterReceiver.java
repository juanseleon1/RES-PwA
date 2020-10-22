/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author juans
 */
public abstract class  ResPwaAdapterReceiver {
    
    protected AtomicBoolean ready;
    protected ServerSocket ss;
    protected abstract SensorData byteToSensorData(byte[] buf);
    
    protected void actualizarBelieves(SensorData sd) throws ExceptionBESA {
        AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasSHAAgent);
        EventBESA evSP = new EventBESA(GetInfoGuard.class.getName(),sd);
        agH.sendEvent(evSP);
    }
    
}
