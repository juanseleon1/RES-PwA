/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import SensorHandlerAgent.SensorData;

/**
 *
 * @author juans
 */
public class updateEmotionalState  extends PeriodicGuardBESA{

   @Override
    public void funcPeriodicExecGuard(EventBESA event) {
       SensorData infoRecibida = (SensorData)event.getData();
            System.out.println("ProcessEmotionGuard Event Received: "+infoRecibida);
            EmotionalAnalyzerState eaState = (EmotionalAnalyzerState)this.agent.getState();
            eaState.getEaModel().updateModel();
    }

    
}