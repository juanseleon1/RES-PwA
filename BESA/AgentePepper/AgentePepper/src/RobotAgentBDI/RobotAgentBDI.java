/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import java.util.ArrayList;
import java.util.List;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBDI extends AgentBDI{


    public RobotAgentBDI(String alias, List<GoalBDI> RAGoals) throws ExceptionBESA {
        super(alias, new RobotAgentBelieves(), RAGoals, 0.96, 0);
    }
    



}
