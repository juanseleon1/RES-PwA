/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
import SensorHandlerAgent.SensorData;
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
    private HashMap<EmotionPwA,Float> estadoEmocional;
    private long tiempoAtencion;
    private long tiempoSinAtencion;
    private long tiempoRelajacion;
    private long tiempoSinRelajacion;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: "+si);
        SensorData infoRecibida= (SensorData)si;
         if(infoRecibida.getDataPE().containsKey("EmoMap"))
        {
            estadoEmocional=(HashMap<EmotionPwA,Float>) infoRecibida.getDataPE().get("EmoMap");
            calcularPredominante();
        }
        if(infoRecibida.getDataPE().containsKey("atencion"))
        {
           if((float)infoRecibida.getDataPE().get("atencion")<0.5 && tiempoSinAtencion==0)
           {
               tiempoSinAtencion=System.currentTimeMillis();
               tiempoAtencion=0;
           }else if((float)infoRecibida.getDataPE().get("atencion")>=0.5 && tiempoAtencion==0)
           {
               tiempoAtencion=System.currentTimeMillis();
               tiempoSinAtencion=0;
           }
        }
        if(infoRecibida.getDataPE().containsKey("relajacion"))
        {
           if((float)infoRecibida.getDataPE().get("relajacion")<0.5 && tiempoSinRelajacion==0)
           {
               tiempoSinRelajacion=System.currentTimeMillis();
               tiempoRelajacion=0;
           }else if((float)infoRecibida.getDataPE().get("relajacion")>=0.5 && tiempoRelajacion==0)
           {
               tiempoRelajacion=System.currentTimeMillis();
               tiempoSinRelajacion=0;
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
        return System.currentTimeMillis()-tiempoEmocionPredominante;
    }

    public void setTiempoEmocionPredominante(long tiempoEmocionPredominante) {
        this.tiempoEmocionPredominante = tiempoEmocionPredominante;
    }

    public HashMap<EmotionPwA,Float> getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(HashMap<EmotionPwA,Float> estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public long getTiempoAtencion() {
        return System.currentTimeMillis()-tiempoAtencion;
    }

    public void setTiempoAtencion(long tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public long getTiempoRelajacion() {
        return System.currentTimeMillis()-tiempoRelajacion;
    }

    public void setTiempoRelajacion(long tiempoRelajacion) {
        this.tiempoRelajacion = tiempoRelajacion;
    }

    public long getTiempoSinAtencion() {
        return System.currentTimeMillis()-tiempoSinAtencion;
    }

    public void setTiempoSinAtencion(long tiempoSinAtencion) {
        this.tiempoSinAtencion = tiempoSinAtencion;
    }

    public long getTiempoSinRelajacion() {
        return System.currentTimeMillis()-tiempoSinRelajacion;
    }

    public void setTiempoSinRelajacion(long tiempoSinRelajacion) {
        this.tiempoSinRelajacion = tiempoSinRelajacion;
    }

    private void calcularPredominante() {

        emocionPredominante=estadoEmocional.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        tiempoEmocionPredominante=System.currentTimeMillis();
    }

          @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }
}
