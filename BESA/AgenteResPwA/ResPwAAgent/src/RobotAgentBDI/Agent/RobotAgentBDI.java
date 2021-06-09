/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Agent;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.List;

/**
 *
 * @author juans
 */
public class RobotAgentBDI extends AgentBDI{
    
    private static final double TH=0.5;
    public RobotAgentBDI(String alias, List<GoalBDI> RAGoals, String cedula) throws ExceptionBESA {
        super(alias, new RobotAgentBelieves(cedula), RAGoals,TH, new StructBESA());
        System.out.println("RobotAgentBDI Iniciado");
    }

    @Override
    public void setupAgentBDI() {
    }

    @Override
    public void shutdownAgentBDI() {
    
    }
           
}
