/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
import static EmotionalAnalyzerAgent.EmotionPwA.ANGER;
import static EmotionalAnalyzerAgent.EmotionPwA.SADNESS;
import EmotionalAnalyzerAgent.EmotionalData;
import PepperPackage.EmotionalModel.Emotion;
import SensorHandlerAgent.SensorData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoEmocionalPwA implements Believes {

    private EmotionPwA emocionPredominante;
    private Map<EmotionPwA, List<Emotion>> emoMap;
    private long tiempoEmocionPredominante;
    private long tiempoAtencion;
    private long tiempoSinAtencion;
    private long tiempoRelajacion;
    private long tiempoSinRelajacion;
    private double atencion;
    private double relajacion;


    public BEstadoEmocionalPwA() {
        emoMap = new HashMap<>();
        for (EmotionPwA epwa : EmotionPwA.values()) {
            emoMap.put(epwa, new ArrayList<>());
        }
    }

    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: " + si);
        EmotionalData infoRecibida = (EmotionalData) si;
        if (infoRecibida.getInfo().containsKey("atencion")) {
            if ((double) infoRecibida.getInfo().get("atencion") < 0.5) {
                atencion = (double) infoRecibida.getInfo().get("atencion") ;
         
            }
        }
        if (infoRecibida.getInfo().containsKey("relajacion")) {
            atencion = (double) infoRecibida.getInfo().get("relajacion") ;
        }
        if (infoRecibida.getInfo().containsKey("emotions")) {
            Map<EmotionPwA, Float> emo = (Map<EmotionPwA, Float>) infoRecibida.getInfo().get("emotions");
            Emotion e;
            float val;
            if (emo != null) {
                for (EmotionPwA epwa : emo.keySet()) {
                    val = emo.get(epwa);
                    e = new Emotion(val);
                    emoMap.get(epwa).add(e);
                }
            }
        }
        return true;
    }

    public Map<EmotionPwA, List<Emotion>> getEmoMap() {
        return emoMap;
    }

    public void setEmoMap(Map<EmotionPwA, List<Emotion>> emoMap) {
        this.emoMap = emoMap;
    }
    
//    Return if the emotion is pleasant or unpleasant
    public double getFeedbackEmotion(){
        double emotionFeedback = 0.0;
        double auxEmotionAverage = 0.0;
        
        for (EmotionPwA entry : emoMap.keySet()) {
            
            auxEmotionAverage = getEmotionAverage(emoMap.get(entry));
            if(entry == ANGER || entry == SADNESS){
                auxEmotionAverage *= -1;
            }
            if(Math.abs(auxEmotionAverage) > Math.abs(emotionFeedback)){
                emotionFeedback = auxEmotionAverage;
            }
        }
        
//        if Pleasant return 1.0
        if(emotionFeedback > 0){
            emotionFeedback = 1.0;
        }
        else{
//            if Unpleasant return 0.0
            emotionFeedback = 0.0;
        }
        return emotionFeedback;
    }
    
    private float getEmotionAverage(List<Emotion> historyEmotionsInActivity){
        float emotionAverage = 0.0f;
        
        for (Emotion emotion : historyEmotionsInActivity) {
            emotionAverage += emotion.getValence();
        }
        
        return emotionAverage / historyEmotionsInActivity.size();
    }
    
    public EmotionPwA getEmocionPredominante() {
        return emocionPredominante;
    }

    public void setEmocionPredominante(EmotionPwA emocionPredominante) {
        this.emocionPredominante = emocionPredominante;
    }

    public long getTiempoEmocionPredominante() {
        return System.currentTimeMillis() - tiempoEmocionPredominante;
    }

    public void setTiempoEmocionPredominante(long tiempoEmocionPredominante) {
        this.tiempoEmocionPredominante = tiempoEmocionPredominante;
    }

    public long getTiempoAtencion() {
        return System.currentTimeMillis() - tiempoAtencion;
    }

    public void setTiempoAtencion(long tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public long getTiempoRelajacion() {
        return System.currentTimeMillis() - tiempoRelajacion;
    }

    public void setTiempoRelajacion(long tiempoRelajacion) {
        this.tiempoRelajacion = tiempoRelajacion;
    }

    public long getTiempoSinAtencion() {
        return System.currentTimeMillis() - tiempoSinAtencion;
    }

    public void setTiempoSinAtencion(long tiempoSinAtencion) {
        this.tiempoSinAtencion = tiempoSinAtencion;
    }

    public long getTiempoSinRelajacion() {
        return System.currentTimeMillis() - tiempoSinRelajacion;
    }

    public void setTiempoSinRelajacion(long tiempoSinRelajacion) {
        this.tiempoSinRelajacion = tiempoSinRelajacion;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    public double getAtencion() {
        return atencion;
    }

    public void setAtencion(double atencion) {
        this.atencion = atencion;
    }

    public double getRelajacion() {
        return relajacion;
    }

    public void setRelajacion(double relajacion) {
        this.relajacion = relajacion;
    }

}
