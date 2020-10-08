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
public class BEstadoEmocionalPwA implements Believes{
    private long tiempoTriste;
    private long tiempoIra;
    private Object estadoEmocional;
    private long concentracion;
    private long atencion;
    private long  tiemopNoAtencion;
    private long  tiemopNoConcentracion;
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: "+si);
        return true;
    }
    
    
}
