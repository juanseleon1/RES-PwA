/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent.Agent;

import EmotionalAnalyzerAgent.Guards.EmotionalData;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalEvent;
import SensorHandlerAgent.Guards.SensorData;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juans
 */
public interface EmotionalAnalyzerStrategy {
    
    public List<EmotionalEvent> processEmotion(EmotionalData sd);
    
}
