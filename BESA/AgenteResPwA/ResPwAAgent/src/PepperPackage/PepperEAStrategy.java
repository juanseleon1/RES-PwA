/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage;

import EmotionalAnalyzerAgent.EmotionalAnalyzerStrategy;
import SensorHandlerAgent.SensorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperEAStrategy implements EmotionalAnalyzerStrategy{

    @Override
    public Map<String, Object> processEmotion(SensorData sd) {
        System.out.println("Emotion Processing");
        Map<String, Object> ret = null;
        try {
            String dat = (String) sd.getDataP().get("PersonData");
            ret = new ObjectMapper().readValue("{"+dat+"}", new TypeReference<Map<String,Object>>(){});
            translateToResPwa(ret);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PepperEAStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    private void translateToResPwa(Map<String, Object> ret) {
     //Falta esto   
        System.out.println("AQUI:" +ret.toString());
     ret.remove("valence");
     ret.get("attention");
    }
}
