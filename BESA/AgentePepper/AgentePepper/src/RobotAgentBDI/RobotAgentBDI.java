/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import SensorHandlerAgent.RequestEmotionalInfoPeriodicGuard;
import SensorHandlerAgent.RequestOtherInfoPeriodicGuard;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.RationalAgent;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author juans
 */
public class RobotAgentBDI extends AgentBDI{
    
    public static int PERIODIC_TIME = 500; //revisar


    public RobotAgentBDI(String alias, List<GoalBDI> RAGoals) throws ExceptionBESA {
        super(alias, new RobotAgentBelieves(), RAGoals, 0.96, 0);
        this.startBDIsystem();
        
    }
    
    
    private void startBDIsystem()
    {
             try{
            this.start();
            this.startTimers();
            PeriodicDataBESA data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            EventBESA eventBESA = new EventBESA(RequestEmotionalInfoPeriodicGuard.class.getName(), data);
            AgHandlerBESA agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            agHandlerBESA.sendEvent(eventBESA);
            
            data = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
            eventBESA = new EventBESA(RequestOtherInfoPeriodicGuard.class.getName(), data);
            agHandlerBESA =AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            agHandlerBESA.sendEvent(eventBESA);
        }
        catch (ExceptionBESA ex)
        {
           Logger.getLogger(RobotAgentBDI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public boolean requestInformationFlowPerfil() {
        try {
            sendEvent(new EventBESA(InformationFlowGuard.class.getName()));
            return true;
        } catch (KernelAgentExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
        
}
