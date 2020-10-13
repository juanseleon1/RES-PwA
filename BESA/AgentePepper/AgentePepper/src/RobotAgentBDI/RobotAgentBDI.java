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
        System.out.println("RobotAgentBDI Iniciado");
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
