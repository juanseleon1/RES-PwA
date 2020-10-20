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
public class BEstadoRobot implements Believes{
    
    private int bateria;
    //private long velocidad;
    private boolean activadoParpadear=false;
    private boolean activadoAutoColision=false;
    private boolean activadoColisionExterna=false;
    private boolean activadoRecuperacionEmpuje=false;
    private int rigidezExtremidades;
    private String postura;
    private boolean activadoMovEscucha=false;
    private boolean activadoConsciente=false;
    private boolean activadoSeñalesDeVida=false;
    private boolean activadoMovHabla=false;

    @Override
    public boolean update(InfoData si) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
