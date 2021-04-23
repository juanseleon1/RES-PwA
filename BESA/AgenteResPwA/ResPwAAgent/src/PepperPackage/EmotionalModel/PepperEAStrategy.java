/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import EmotionalAnalyzerAgent.EmotionalAnalyzerStrategy;
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalEventType;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juans
 */
public class PepperEAStrategy implements EmotionalAnalyzerStrategy {

    String paramsString = "params";

    @Override
    public EmotionalEvent processEmotion(EmotionalData sd) {
        Map<String, Object> ret = sd.getInfo();
        System.out.println("EMO: "+ret.toString());
        return null;
    }
}