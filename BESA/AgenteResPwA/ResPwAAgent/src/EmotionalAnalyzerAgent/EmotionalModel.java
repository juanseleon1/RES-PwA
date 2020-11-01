/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.InitRESPwA;
import SensorHandlerAgent.SensorData;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author juans
 */
public abstract class EmotionalModel {
    public abstract void updateModel();
    public abstract void updtModelFromEvt(SensorData sd);
    protected void sendAct(EmotionalData ed) throws ExceptionBESA{
        AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
        EventBESA sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),ed);
        handler.sendEvent(sensorEvtA);
    }
}
