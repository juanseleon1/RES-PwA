/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import RobotAgentBDI.Believes.ModulationStrategy;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.Map;

/**
 *
 * @author jsleon
 */
public class PepperModulationStrategy extends ModulationStrategy{
    
    private final double speechBase = 1.1;
    private final double speechVBase = 100;
    private final double mvtBase = 1;
    private final double ledsIntBase = 0.5;
    private double velf = 1;
    private double velh = 100;
    private double pitch = 1.1;
    private double ledInt = 1;
    private final double ledRotVel = 2;
    private static final double CHANGE_FACT = 0.3;
    private static final double CHANGEEMO_FACT = 0.01;

    @Override
    public Map<String, Object> modulateAction(ServiceDataRequest sdr) {
        return null;
    }

    @Override
    protected Map<String, Object> calculateModulateValues() {
        return null;
    }
    
    
    
    
}