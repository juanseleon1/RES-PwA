/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besatest;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import RobotAgentBDI.Agent.RobotAgentBDI;
import RobotAgentBDI.Guardas.RunnerGuard;
import RobotAgentBDI.Metas.ABCyMultiplicar;
import RobotAgentBDI.Metas.ContaryListar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class BESATest {

    public static String aliasRobotAgent = "RobotAgent";
    private static int PLANID = 0;
    public static int PERIODIC_TIME = 500;
    public static int DELAY_TIME = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            AdmBESA.getInstance();
            System.out.println("Iniciando RES-PwA");
            RobotAgentBDI RABDI = new RobotAgentBDI(aliasRobotAgent, createRobotAgentGoals(), null);
            RABDI.start();        
            RABDI.startTimers();
            begin();
        } catch (ExceptionBESA ex) {
            Logger.getLogger(BESATest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static int getPlanID() {
        return ++PLANID;
    }

    private static List<GoalBDI> createRobotAgentGoals() {
        List<GoalBDI> RAGoals = new ArrayList<>();
        ContaryListar cl = ContaryListar.buildGoal();
        ABCyMultiplicar abcm = ABCyMultiplicar.buildGoal();
        RAGoals.add(cl);
        RAGoals.add(abcm);
        return RAGoals;
    }
    
    
        public static void  begin() throws ExceptionBESA{
        
        try{
            PeriodicDataBESA data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            EventBESA eventBESA = new EventBESA(RunnerGuard.class.getName(), data);
            AgHandlerBESA agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(aliasRobotAgent);
            agHandlerBESA.sendEvent(eventBESA);
        }catch (ExceptionBESA ex){
           Logger.getLogger(BESATest.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
}
