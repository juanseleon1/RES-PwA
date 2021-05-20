///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Tareas.Retroalimentacion;
//
//import EmotionalAnalyzerAgent.EmotionPwA;
//import PepperPackage.EmotionalModel.Emotion;
//import ResPwAEntities.Cancion;
//import ResPwAEntities.Cuento;
//import RobotAgentBDI.Believes.BEstadoEmocionalPwA;
//import RobotAgentBDI.Believes.RobotAgentBelieves;
//import RobotAgentBDI.ResPwAActivity;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import rational.data.InfoData;
//
///**
// *
// * @author ASUS
// */
//public class RetroalimentacionReceptionTesting {
//    public static void main(String[] args){
//        
//        RobotAgentBelieves robotAgentBelieves = new RobotAgentBelieves("123");
//        Map<EmotionPwA, List<Emotion>> emoMap = null;
//        emoMap = llenarEmoMap(emoMap);
//        robotAgentBelieves.getbEstadoEmocionalPwA().setEmoMap(emoMap);
//        robotAgentBelieves.getbEstadoActividad().setActividadActual(ResPwAActivity.CUENTERIA);
//        Cuento cuento0 = new Cuento("awita0", "Pombo", 0.2);
//        robotAgentBelieves.getbEstadoActividad().setCuentoActual(cuento0);
//        robotAgentBelieves.feedbackActivity(1.0);
//       
//    }
//    
//    public static Map<EmotionPwA, List<Emotion>> llenarEmoMap(Map<EmotionPwA, List<Emotion>> emoMap){
//        emoMap = new HashMap<>();
//        for (EmotionPwA epwa : EmotionPwA.values()) {
//            emoMap.put(epwa, new ArrayList<>());
//            for (int i = 0; i < 3; i++) {
//                Emotion e = new Emotion((float) Math.random());
//                emoMap.get(epwa).add(e);
//            }
//        }
//        return emoMap;
//    }
//}
