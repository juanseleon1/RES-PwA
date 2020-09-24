/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.DataBESA;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBelieves implements Believes{

    @Override
    public boolean update(DataBESA dbesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Believes clone() throws Exception, CloneNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     protected RobotAgentBelieves getBelieves() {
        return (RobotAgentBelieves) ((StateBDI) this.getState()).getBelieves();
    }
     
     public void debug() {
        StateBDI state = (StateBDI) this.getState();
        GoalBDI goal = state.getMachineBDIParams().getIntention();
        if (goal != null) {
            System.out.println("Current intention: " + goal.getDescription() + "  -- Charge: " + getBelieves());
        }
    }


  
}
