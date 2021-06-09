/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent.Agent;

import EmotionalAnalyzerAgent.Guards.ProcessEmotionGuard;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import SensorHandlerAgent.Agent.SensorHandlerAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class EmotionalAnalyzerAgent extends AgentBESA {

    public static String ProcessEmotionGuard= "ProcessEmotionGuard";
    public EmotionalAnalyzerAgent(String alias, EmotionalAnalyzerStrategy eas) throws KernelAgentExceptionBESA {
        super(alias, new EmotionalAnalyzerState(eas), buildEAStruct(), 0.96);
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
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SensorHandlerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return struct;
    }
}
