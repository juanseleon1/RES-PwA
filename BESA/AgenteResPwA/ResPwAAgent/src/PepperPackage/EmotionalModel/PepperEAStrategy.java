/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import EmotionalAnalyzerAgent.EmotionalAnalyzerStrategy;
import SensorHandlerAgent.SensorData;
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
    public Map<String, Object> processEmotion(SensorData sd) {
        Map<String, Object> ret = sd.getDataP();
//        Map<String, Object> params = (Map<String, Object>) ret.get(paramsString);
//        PepperEmoResponses resp = PepperEmoResponses.getFromId(params.keySet().iterator().next());
//        switch (resp) {
//            case VOICEEMOTION:
//                Map<String, Object> map = new HashMap<>();
//                List<Object> list = (List<Object>) params.get(PepperEmoResponses.VOICEEMOTION.getEmoType());
//                List<Integer> auxList = (List<Integer>) list.get(1);
//                for (int i = 0; i < auxList.size(); i++) {
//                    map.put(PepperPersonEmotion.getFromId(i), auxList.get(i));
//                }
//                auxList = (List<Integer>) list.get(0);
//                map.put(PepperPersonEmotion.getFromId(auxList.get(0)), auxList.get(1));
//                int auxInt = (int) list.get(2);
//                map.put(PepperPersonEmotion.EXCITEMENT.getId(), auxInt);
//                ret = map;
//                break;
//            case GETEMOSTATE:
//            default:
//                break;
//
//        }
        return ret;
    }
}