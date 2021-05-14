/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
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
            if ((double) infoRecibida.getInfo().get("atencion") < 0.5 && tiempoSinAtencion == 0) {
                tiempoSinAtencion = System.currentTimeMillis();
                tiempoAtencion = 0;
            } else if ((double) infoRecibida.getInfo().get("atencion") >= 0.5 && tiempoAtencion == 0) {
                tiempoAtencion = System.currentTimeMillis();
                tiempoSinAtencion = 0;
            }
        }
        if (infoRecibida.getInfo().containsKey("relajacion")) {
            if ((double) infoRecibida.getInfo().get("relajacion") < 0.5 && tiempoSinRelajacion == 0) {
                tiempoSinRelajacion = System.currentTimeMillis();
                tiempoRelajacion = 0;
            } else if ((double) infoRecibida.getInfo().get("relajacion") >= 0.5 && tiempoRelajacion == 0) {
                tiempoRelajacion = System.currentTimeMillis();
                tiempoSinRelajacion = 0;
            }
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

}
