/*
 * To change this license header, choose License Headers in Project Proretrties.
 * To change this template file, choose Tools | Templates
 * and oretn the template in the editor.
 */
package PepperPackage.EmotionalModel;

import EmotionalAnalyzerAgent.Utils.EmotionPwA;
import EmotionalAnalyzerAgent.Agent.EmotionalAnalyzerStrategy;
import EmotionalAnalyzerAgent.Guards.EmotionalData;
import EmotionalAnalyzerAgent.Utils.EmotionalEventType;
import EmotionalAnalyzerAgent.Utils.EmotionalSubjectType;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalEvent;
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
//        System.out.println("PepperEAStrategy: " + ret.toString());
        EmotionalEvent evt = null;

        if (ret.get("PersonData") != null) {
            System.out.println("Evento Reconocido PersonData");
            ret = (Map<String, Object>) ret.get("PersonData");
            aux = (Map<String, Object>) ret.get("bodyLanguageState");
            aux = (Map<String, Object>) aux.get("ease");
            double relval = (double)  aux.get("level") * (double) aux.get("confidence");
            aux = (Map<String, Object>) ret.get("expressions");
            auxEmo = (Map<String, Object>) aux.get("joy");
            double joyval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("sorrow");
            double sowval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("anger");
            double angval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            aux = (Map<String, Object>) ret.get("attention");
            double atval = (double) aux.get("confidence") * (double) aux.get("value");
            /* auxEmo = (Map<String, Object>) aux.get("excitement");
            double excval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("calm");
            double calval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");

            auxEmo = (Map<String, Object>) aux.get("surprise");
            double surval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            auxEmo = (Map<String, Object>) aux.get("laughter");
            double lauval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
            aux = (Map<String, Object>) ret.get("valence");
            double valval = (double) aux.get("confidence") * (double) aux.get("value");
             */

            Map<EmotionPwA, Double> emo = new HashMap<>();
            emo.put(EmotionPwA.ANGER, angval);
            emo.put(EmotionPwA.HAPPINESS, joyval);
            emo.put(EmotionPwA.SADNESS, sowval);
            double val = ((angval + sowval) / 2);
            EmotionalEventType emoT = val > joyval ? EmotionalEventType.NEGEMOSTATE : EmotionalEventType.POSEMOSTATE;
            evt = new EmotionalEvent(EmotionalSubjectType.PWA.toString(), emoT.toString(), null);
            emoList.add(evt);
            map.put("relajacion", relval);
            map.put("atencion", atval);
            map.put("emotions", emo);

        } else if (ret.get("voiceEmotionRecognized") != null) {
            System.out.println("Evento Reconocido voiceEmotionRecognized");
            double joyval, angval, sowval, calmval;
            List<Object> auxL = (List<Object>) ret.get("voiceEmotionRecognized");
            List<Integer> emotList = (List<Integer>) auxL.get(1);
            calmval = emotList.get(1).intValue();
            angval = emotList.get(2).intValue();
            joyval = emotList.get(3).intValue();
            sowval = emotList.get(4).intValue();
            double val = ((angval + sowval) / 2);
            double lval = ((joyval + calmval) / 2);
            EmotionalEventType emoT = val > lval ? EmotionalEventType.NEGVOICEEMOTION : EmotionalEventType.POSVOICEEMOTION;
            evt = new EmotionalEvent(EmotionalSubjectType.PWA.toString(), emoT.toString(), null);
            emoList.add(evt);

            //TO DO.  [ [4,3], [0,2,1,3,0], 1 ]
        } else {
            System.out.println("Evento Reconocido else");
            String paramsString;
            paramsString = ret.keySet().iterator().next();
//            System.out.println("paramsString: " + paramsString);
            boolean isOk = (boolean) ret.get(paramsString);
//            System.out.println("IsOk: " + isOk);
            if (isOk) {
                EmotionalEventType emoT = EmotionalEventType.getFromId(paramsString);
                evt = new EmotionalEvent(EmotionalSubjectType.PWA.toString(), emoT.toString(), null);
                emoList.add(evt);
            }
        }

        for (EmotionalEvent emotionalEvent : emoList) {
//            System.out.println("Evt:" + emotionalEvent.toString());
        }
        return emoList;
    }
}
