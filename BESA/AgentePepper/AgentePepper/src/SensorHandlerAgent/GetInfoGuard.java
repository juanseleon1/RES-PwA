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
            
            infoRecibida.setDataP(procesarDatos(infoRecibida));
            infoRecibida.setInfoReceived(null);
            
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAid(RunAgentePepper.aliasRobotAgent);
            EventBESA sensorEvt= new EventBESA(InformationFlowGuard.class.getName(),infoRecibida);
            handler.sendEvent(ebesa);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GetInfoGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private HashMap<String, Object> procesarDatos(SensorData infoRecibida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
