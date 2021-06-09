/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent.Guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import EmotionalAnalyzerAgent.Guards.EmotionalData;
import EmotionalAnalyzerAgent.Utils.EmotionalEventType;
import EmotionalAnalyzerAgent.Guards.ProcessEmotionGuard;
import Init.InitRESPwA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author juans
 */
public class GetInfoGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            SensorData infoRecibida = (SensorData) ebesa.getData();
//            System.out.println("GetInfoGuard Event Received: " + infoRecibida);
            AgHandlerBESA handler = null;
            EventBESA sensorEvtA = null;
            if (infoRecibida.getDataType().equals(SensorDataType.EMOTIONS) || infoRecibida.isHasEmo()) {
                handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasEAAgent);
                EmotionalData emData = EmotionalData.fromSensorData(infoRecibida);
                sensorEvtA = new EventBESA(ProcessEmotionGuard.class.getName(), emData);
            }
            if (!infoRecibida.getDataType().equals(SensorDataType.EMOTIONS)) {
                handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
                sensorEvtA = new EventBESA(InformationFlowGuard.class.getName(), infoRecibida);
            }
            handler.sendEvent(sensorEvtA);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GetInfoGuard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
