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
    private String actividadActual;
    private boolean finalizoActividad;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoActividad update Received: "+si);
        return true;
    }
    
    public long tiempoActividad() {
        return 0;
    }

    public String getActividadActual() {
        return actividadActual;
    }

    public boolean isFinalizoActividad() {
        return finalizoActividad;
    }
    
    
    
}
