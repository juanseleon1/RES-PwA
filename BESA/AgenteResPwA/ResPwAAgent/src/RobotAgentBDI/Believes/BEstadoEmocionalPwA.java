/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalData;
import SensorHandlerAgent.SensorData;
import java.util.HashMap;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoEmocionalPwA implements Believes{

    private long tiempoAtencion;
    private long tiempoSinAtencion;
    private long tiempoRelajacion;
    private long tiempoSinRelajacion;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: "+si);
        EmotionalData infoRecibida= (EmotionalData)si;
        if(infoRecibida.getInfo().containsKey("atencion"))
        {
           if((double)infoRecibida.getInfo().get("atencion")<0.5 && tiempoSinAtencion==0)
           {
               tiempoSinAtencion=System.currentTimeMillis();
               tiempoAtencion=0;
           }else if((double)infoRecibida.getInfo().get("atencion")>=0.5 && tiempoAtencion==0)
           {
               tiempoAtencion=System.currentTimeMillis();
               tiempoSinAtencion=0;
           }
        }
        if(infoRecibida.getInfo().containsKey("relajacion"))
        {
           if((double)infoRecibida.getInfo().get("relajacion")<0.5 && tiempoSinRelajacion==0)
           {
               tiempoSinRelajacion=System.currentTimeMillis();
               tiempoRelajacion=0;
           }else if((double)infoRecibida.getInfo().get("relajacion")>=0.5 && tiempoRelajacion==0)
           {
               tiempoRelajacion=System.currentTimeMillis();
               tiempoSinRelajacion=0;
           }
        }
        
        return true;
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
          @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }
}
