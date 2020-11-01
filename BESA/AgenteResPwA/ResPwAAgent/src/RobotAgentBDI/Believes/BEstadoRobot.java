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
    private int volumenVoz;
    private long velocidad;
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
    private boolean estaSuspendido=false;

    @Override
    public boolean update(InfoData si) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean isActivadoParpadear() {
        return activadoParpadear;
    }

    public void setActivadoParpadear(boolean activadoParpadear) {
        this.activadoParpadear = activadoParpadear;
    }

    public boolean isActivadoAutoColision() {
        return activadoAutoColision;
    }

    public void setActivadoAutoColision(boolean activadoAutoColision) {
        this.activadoAutoColision = activadoAutoColision;
    }

    public boolean isActivadoColisionExterna() {
        return activadoColisionExterna;
    }

    public void setActivadoColisionExterna(boolean activadoColisionExterna) {
        this.activadoColisionExterna = activadoColisionExterna;
    }

    public boolean isActivadoRecuperacionEmpuje() {
        return activadoRecuperacionEmpuje;
    }

    public void setActivadoRecuperacionEmpuje(boolean activadoRecuperacionEmpuje) {
        this.activadoRecuperacionEmpuje = activadoRecuperacionEmpuje;
    }

    public int getRigidezExtremidades() {
        return rigidezExtremidades;
    }

    public void setRigidezExtremidades(int rigidezExtremidades) {
        this.rigidezExtremidades = rigidezExtremidades;
    }

    public String getPostura() {
        return postura;
    }

    public void setPostura(String postura) {
        this.postura = postura;
    }

    public boolean isActivadoMovEscucha() {
        return activadoMovEscucha;
    }

    public void setActivadoMovEscucha(boolean activadoMovEscucha) {
        this.activadoMovEscucha = activadoMovEscucha;
    }

    public boolean isActivadoConsciente() {
        return activadoConsciente;
    }

    public void setActivadoConsciente(boolean activadoConsciente) {
        this.activadoConsciente = activadoConsciente;
    }

    public boolean isActivadoSeñalesDeVida() {
        return activadoSeñalesDeVida;
    }

    public void setActivadoSeñalesDeVida(boolean activadoSeñalesDeVida) {
        this.activadoSeñalesDeVida = activadoSeñalesDeVida;
    }

    public boolean isActivadoMovHabla() {
        return activadoMovHabla;
    }

    public void setActivadoMovHabla(boolean activadoMovHabla) {
        this.activadoMovHabla = activadoMovHabla;
    }

    public boolean isEstaSuspendido() {
        return estaSuspendido;
    }
    
    
    
}
