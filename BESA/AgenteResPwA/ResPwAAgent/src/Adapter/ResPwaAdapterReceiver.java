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
import Init.InitRESPwA;
import SensorHandlerAgent.Guards.GetInfoGuard;
import SensorHandlerAgent.Guards.SensorData;

/**
 *
 * @author juans
 * @param <T>
 */
public abstract class  ResPwaAdapterReceiver<T>{
    
    protected abstract SensorData toSensorData(T buf);
    
    protected void updateBlvs(SensorData sd) throws ExceptionBESA {
        AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasSHAAgent);
        EventBESA evSP = new EventBESA(GetInfoGuard.class.getName(),sd);
        agH.sendEvent(evSP);
    }
    
}
