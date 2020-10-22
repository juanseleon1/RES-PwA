/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoActividad implements Believes{
    
    private long inicioActividad;
    private Actividad actividadActual;
    private boolean finalizoActividad;
    private boolean mejoraEmocional;
    private Estrategia estrategia;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoActividad update Received: "+si);
        return true;
    }

    public long getInicioActividad() {
        return inicioActividad;
    }

    public void setInicioActividad(long inicioActividad) {
        this.inicioActividad = inicioActividad;
    }

    public Actividad getActividadActual() {
        return actividadActual;
    }

    public void setActividadActual(Actividad actividadActual) {
        this.actividadActual = actividadActual;
    }

    public boolean isFinalizoActividad() {
        return finalizoActividad;
    }

    public void setFinalizoActividad(boolean finalizoActividad) {
        this.finalizoActividad = finalizoActividad;
    }

    public boolean isMejoraEmocional() {
        return mejoraEmocional;
    }

    public void setMejoraEmocional(boolean mejoraEmocional) {
        this.mejoraEmocional = mejoraEmocional;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public long calcTiempoActividad() {
        return System.currentTimeMillis()-inicioActividad;
    }
    

    
    
    
}
