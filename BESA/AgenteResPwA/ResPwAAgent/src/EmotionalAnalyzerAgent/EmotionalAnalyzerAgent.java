/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import SensorHandlerAgent.SensorHandlerAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class EmotionalAnalyzerAgent extends AgentBESA {

    public static String ProcessEmotionGuard= "ProcessEmotionGuard";
    public static String updtEmotionGuard= "updtEmotionGuard";
    private static long PERIODIC_TIME=15000;
    public EmotionalAnalyzerAgent(String alias, EmotionalAnalyzerStrategy eas,EmotionalModel em) throws KernelAgentExceptionBESA {
        super(alias, new EmotionalAnalyzerState(eas,em), buildEAStruct(), 0.96);
        System.out.println("EmotionalAnalyzerAgent Iniciado");
    }

    @Override
    public void setupAgent() {

    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
     private static StructBESA buildEAStruct()
    {
        StructBESA struct=new StructBESA();
        try {
            struct.addBehavior(ProcessEmotionGuard);
            struct.bindGuard(ProcessEmotionGuard, ProcessEmotionGuard.class);
            struct.addBehavior(updtEmotionGuard);
            struct.bindGuard(updtEmotionGuard, updateEmotionalState.class);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SensorHandlerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return struct;
    }
     
    public void startEmotionalModel() throws ExceptionBESA{
        PeriodicDataBESA data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            EventBESA eventBESA = new EventBESA(updateEmotionalState.class.getName(), data);
            AgHandlerBESA agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            agHandlerBESA.sendEvent(eventBESA);
//            System.out.println("Entra");
    }
}
