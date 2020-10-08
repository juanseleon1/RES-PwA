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
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import java.util.HashMap;
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
            infoRecibida.setDataPE(procesarEmociones(infoRecibida));
            infoRecibida.setDataP(null);
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasRobotAgent);
            EventBESA sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),infoRecibida);
            handler.sendEvent(sensorEvtA);  

        } catch (ExceptionBESA ex) {
            Logger.getLogger(GetInfoGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private HashMap<String, Object> procesarEmociones(SensorData infoRecibida) {
        System.out.println("procesarEmociones Activa");
        return null;
    }
    
}
