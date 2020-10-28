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
import java.util.List;

/**
 *
 * @author juans
 */
public class RobotAgentBDI extends AgentBDI{
    
    public RobotAgentBDI(String alias, List<GoalBDI> RAGoals, String cedula) throws ExceptionBESA {
        super(alias, new RobotAgentBelieves(cedula), RAGoals, 0.96, 0);
        System.out.println("RobotAgentBDI Iniciado");
    }
           
}
