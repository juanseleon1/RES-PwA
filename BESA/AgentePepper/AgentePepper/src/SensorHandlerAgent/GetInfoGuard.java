/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import EmotionalAnalyzerAgent.ProcessEmotionGuard;
import Init.RunAgentePepper;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author juans
 */
public class GetInfoGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            SensorData infoRecibida = (SensorData)ebesa.getData();
            //ACTIVIDAD,EMOCIONES,INACTIVIDAD,INTHABLA,INTSENSORES,BATERIA,RETROALIM
            System.out.println("GetInfoGuard Event Received: "+infoRecibida);

            infoRecibida.setDataP(procesarDatos(infoRecibida));
            infoRecibida.setInfoReceived(null);
            AgHandlerBESA handler;
            EventBESA sensorEvtA;
            if(infoRecibida.getDataType().equals(SensorDataType.EMOCIONES))
            {
            handler = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasEAAgent);
            sensorEvtA= new EventBESA(ProcessEmotionGuard.class.getName(),infoRecibida);
            handler.sendEvent(sensorEvtA);  
            }else
            {
            handler = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasRobotAgent);
            sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),infoRecibida);
            handler.sendEvent(sensorEvtA);  
            }

        } catch (ExceptionBESA ex) {
            Logger.getLogger(GetInfoGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private HashMap<String, Object> procesarDatos(SensorData infoRecibida) {
        System.out.println("GetInfoGuard procesarDatos exec: "+infoRecibida);
        return null;
    }
    
}
