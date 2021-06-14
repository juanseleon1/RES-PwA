/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent.Guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import EmotionalAnalyzerAgent.Agent.EmotionalAnalyzerState;
import Init.InitRESPwA;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalEvent;
import java.util.List;
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
            EmotionalData infoRecibida = (EmotionalData)ebesa.getData();
//            System.out.println("ProcessEmotionGuard Event Received: "+infoRecibida);
            EmotionalAnalyzerState eaState = (EmotionalAnalyzerState)this.agent.getState();
            List<EmotionalEvent> emoList = eaState.getEaStrategy().processEmotion(infoRecibida);
            infoRecibida.setEmoEv(emoList);
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
            EventBESA sensorEvtA = new EventBESA(InformationFlowGuard.class.getName(), infoRecibida);
            handler.sendEvent(sensorEvtA);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ProcessEmotionGuard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
