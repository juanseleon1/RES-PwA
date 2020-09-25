/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import SensorHandlerAgent.GetEmotionalInfoPeriodicGuard;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBDI extends AgentBDI{
    
    public static int PERIODIC_TIME = 500; //revisar


    public RobotAgentBDI(String alias, Believes believes, List<GoalBDI> goals, double passwd, double threshold) throws ExceptionBESA {
        super(alias, believes, goals, passwd,threshold);
    }
    
    public void begin() throws ExceptionBESA{
        
        try{
            this.start();
            this.startTimers();
            PeriodicDataBESA data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            EventBESA eventBESA = new EventBESA(GetEmotionalInfoPeriodicGuard.class.getName(), data);
            AgHandlerBESA agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            agHandlerBESA.sendEvent(eventBESA);
            
            data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            eventBESA = new EventBESA(GetOtherInfoPeriodicGuard.class.getName(), data);
            agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            agHandlerBESA.sendEvent(eventBESA);
        }
        catch (ExceptionBESA ex)
        {
           Logger.getLogger(RobotAgentBDI.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

}
