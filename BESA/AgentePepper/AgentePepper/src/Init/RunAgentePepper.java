package Init;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import EmotionalAnalyzerAgent.EmotionalAnalyzerAgent;
import EmotionalAnalyzerAgent.EmotionalAnalyzerState;
import PwAProfileManagerAgent.*;
import RobotAgentBDI.RobotAgentBDI;
import RobotAgentBDI.RobotAgentBelieves;
import SensorHandlerAgent.SensorHandlerAgent;
import SensorHandlerAgent.SensorHandlerState;
import ServiceProviderAgent.ServiceProviderAgent;
import ServiceProviderAgent.ServiceProviderState;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class RunAgentePepper {
        /**
     * @param args the command line arguments
     */
    
    public static String aliasRobotAgent= "RobotAgent";
    public static String aliasEAAgent= "EAAgent";
    public static String aliasPwAPMAgentt= "PwAPMAgentt";
    public static String aliasSHAAgent= "SHAAgent";
    public static String aliasSPAgent= "SPAgent";
    
    public static void main(String[] args) {
        try {
            AdmBESA.getInstance();
            RobotAgentBDI RABDI= new RobotAgentBDI(aliasRobotAgent,createRobotAgentGoals());
            EmotionalAnalyzerAgent EAA= new EmotionalAnalyzerAgent(aliasEAAgent);
            PwAProfileManagerAgent PWAPM= new PwAProfileManagerAgent(aliasPwAPMAgentt);
            SensorHandlerAgent SHA= new SensorHandlerAgent(aliasSHAAgent);
            ServiceProviderAgent SPA= new ServiceProviderAgent(aliasSPAgent);
            startAllAgents(RABDI,EAA,PWAPM,SHA,SPA);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RunAgentePepper.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    private static List<GoalBDI> createRobotAgentGoals()
    {
        List<GoalBDI> RAGoals= new ArrayList<>();
        return RAGoals;
    }


    private static void startAllAgents(RobotAgentBDI RABDI, EmotionalAnalyzerAgent EAA, PwAProfileManagerAgent PWAPM, SensorHandlerAgent SHA, ServiceProviderAgent SPA) {
        RABDI.start();
        EAA.start();
        PWAPM.start();
        SHA.start();
        SPA.start();
    }

}