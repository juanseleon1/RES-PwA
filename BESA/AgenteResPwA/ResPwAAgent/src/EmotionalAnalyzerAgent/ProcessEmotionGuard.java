/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.InitRESPwA;
import SensorHandlerAgent.SensorData;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author juans
 */
public class ProcessEmotionGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
         try {
            SensorData infoRecibida = (SensorData)ebesa.getData();
            System.out.println("ProcessEmotionGuard Event Received: "+infoRecibida);
            EmotionalAnalyzerState eaState = (EmotionalAnalyzerState)this.agent.getState();
            eaState.getEaModel().updtModelFromEvt(infoRecibida);
            infoRecibida.setDataPE(eaState.getEaStrategy().processEmotion(infoRecibida));
            infoRecibida.setDataP(null);
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
            EventBESA sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),infoRecibida);
            handler.sendEvent(sensorEvtA);  

        } catch (ExceptionBESA ex) {
            Logger.getLogger(ProcessEmotionGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
