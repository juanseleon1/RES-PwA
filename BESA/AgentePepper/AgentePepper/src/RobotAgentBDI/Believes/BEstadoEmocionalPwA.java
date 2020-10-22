/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
import RobotAgentBDI.Believes.PerfilPwA.Emocion;
import java.util.HashMap;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoEmocionalPwA implements Believes{

    private EmotionPwA emocionPredominante;
    private long tiempoEmocionPredominante;
    private HashMap<String,Integer> estadoEmocional;
    private long tiempoAtencion;
    private long tiempoSinAtencion;
    private long tiempoRelajacion;
    private long tiempoSinRelajacion;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: "+si);
        return true;
    }

    public EmotionPwA getEmocionPredominante() {
        return emocionPredominante;
    }

    public void setEmocionPredominante(EmotionPwA emocionPredominante) {
        this.emocionPredominante = emocionPredominante;
    }

    public long getTiempoEmocionPredominante() {
        return System.currentTimeMillis()-tiempoEmocionPredominante;
    }

    public void setTiempoEmocionPredominante(long tiempoEmocionPredominante) {
        this.tiempoEmocionPredominante = tiempoEmocionPredominante;
    }

    public HashMap<String,Integer> getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(HashMap<String,Integer> estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public long getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(long tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public long getTiempoRelajacion() {
        return tiempoRelajacion;
    }

    public void setTiempoRelajacion(long tiempoRelajacion) {
        this.tiempoRelajacion = tiempoRelajacion;
    }

    public long getTiempoSinAtencion() {
        return tiempoSinAtencion;
    }

    public void setTiempoSinAtencion(long tiempoSinAtencion) {
        this.tiempoSinAtencion = tiempoSinAtencion;
    }

    public long getTiempoSinRelajacion() {
        return tiempoSinRelajacion;
    }

    public void setTiempoSinRelajacion(long tiempoSinRelajacion) {
        this.tiempoSinRelajacion = tiempoSinRelajacion;
    }

      
}
