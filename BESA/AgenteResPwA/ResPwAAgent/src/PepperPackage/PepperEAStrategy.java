/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage;

import EmotionalAnalyzerAgent.EmotionalAnalyzerStrategy;
import SensorHandlerAgent.SensorData;
import java.util.Map;

/**
 *
 * @author juans
 */
public class PepperEAStrategy implements EmotionalAnalyzerStrategy{

    @Override
    public Map<String, Object> processEmotion(SensorData sd) {
//        System.out.println("Emotion Processing");
        Map<String, Object> ret = sd.getDataP();
        return ret;
    }
}
