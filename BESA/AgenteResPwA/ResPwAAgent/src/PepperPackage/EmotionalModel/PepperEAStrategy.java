/*
 * To change this license header, choose License Headers in Project Proretrties.
 * To change this template file, choose Tools | Templates
 * and oretn the template in the editor.
 */
package PepperPackage.EmotionalModel;

import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalAnalyzerStrategy;
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalEventType;
import EmotionalAnalyzerAgent.WHO;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juans
 */
public class PepperEAStrategy implements EmotionalAnalyzerStrategy {

    @Override
    public List<EmotionalEvent> processEmotion(EmotionalData sd) {
        List<EmotionalEvent> emoList = new ArrayList<>();
        Map<String, Object> ret = sd.getInfo(), aux, auxEmo, map = new HashMap<>();
        System.out.println("EMO: " + ret.toString());
        EmotionalEvent evt = null;
        if (ret.get("bodyLanguageState") != null) {
            aux = (Map<String, Object>) ret.get("bodyLanguageState");
            aux = (Map<String, Object>) aux.get("ease");
            float relval = (float) aux.get("level") * (float) aux.get("confidence");
            aux = (Map<String, Object>) ret.get("expressions");
            auxEmo = (Map<String, Object>) aux.get("joy");
            float joyval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("sorrow");
            float sowval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("anger");
            float angval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            aux = (Map<String, Object>) ret.get("attention");
            float atval = (float) aux.get("confidence") * (float) aux.get("value");
            /* auxEmo = (Map<String, Object>) aux.get("excitement");
            float excval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("calm");
            float calval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");

            auxEmo = (Map<String, Object>) aux.get("surprise");
            float surval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("laughter");
            float lauval = (float) auxEmo.get("confidence") * (float) auxEmo.get("value");
            aux = (Map<String, Object>) ret.get("valence");
            float valval = (float) aux.get("confidence") * (float) aux.get("value");
             */

            Map<EmotionPwA, Float> emo = new HashMap<>();
            emo.put(EmotionPwA.ANGER, angval);
            emo.put(EmotionPwA.HAPPINESS, joyval);
            emo.put(EmotionPwA.SADNESS, sowval);
            float val = ((angval + sowval) / 2);
            EmotionalEventType emoT = val > joyval ? EmotionalEventType.NEGEMOSTATE : EmotionalEventType.POSEMOSTATE;
            evt = new EmotionalEvent(WHO.PWA.toString(), emoT.toString(), null);
            emoList.add(evt);
            map.put("relajacion", relval);
            map.put("atencion", atval);
            map.put("emotions", emo);

        } else if (ret.get("voiceEmotionRecognized") != null) {
            float joyval = 0, angval = 0, sowval = 0;
            aux = (Map<String, Object>) ret.get("voiceEmotionRecognized");
            float val = ((angval + sowval) / 2);
            EmotionalEventType emoT = val > joyval ? EmotionalEventType.NEGEMOSTATE : EmotionalEventType.POSEMOSTATE;
            //TO DO.  [ [4,3], [0,2,1,3,0], 1 ]

        } else {
            String paramsString;
            paramsString = ret.keySet().iterator().next();
            boolean isOk = (boolean) ret.get(paramsString);
            if (isOk) {
                EmotionalEventType emoT = EmotionalEventType.getFromId(paramsString);
                evt = new EmotionalEvent(WHO.PWA.toString(), emoT.toString(), null);
                emoList.add(evt);
            }
        }
        return emoList;
    }
}
