/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import SensorHandlerAgent.SensorData;

/**
 *
 * @author juans
 */
public class ProcessEmotionGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        SensorData infoRecibida = (SensorData)ebesa.getData();
        System.out.println("ProcessEmotionGuard Event Received: "+infoRecibida);
        EmotionalAnalyzerState eaState = (EmotionalAnalyzerState)this.agent.getState();
        infoRecibida.setDataPE(eaState.getEaStrategy().processEmotion(infoRecibida));
        infoRecibida.setDataP(null);
        eaState.getEaModel().updtModelFromEvt(infoRecibida);
        
    }

    
}
