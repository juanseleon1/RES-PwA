/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalData;
import SensorHandlerAgent.SensorData;
import Tareas.CambiarEnriquecimientoHistoria.LedsColor;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoRobot implements Believes {

    private int bateria;
    private int volumenVoz;
    private double velocidad = 0;
    private boolean activadoParpadear = false;
    private boolean activadoAutoColision = false;
    private boolean activadoColisionExterna = false;
    private boolean activadoRecuperacionEmpuje = false;
    private int rigidezExtremidades;
    private String postura;
    private boolean activadoMovEscucha = false;
    private boolean activadoConsciente = false;
    private boolean activadoSeñalesDeVida = false;
    private boolean activadoMovHabla = false;
    private boolean estaSuspendido = false;
    private double velHabla;
    private double tonoHabla;
    private double distanciaX;
    private double distanciaY;
    private double ledIntensity;
    private LedsColor leds=null;
    private int red;
    private int green;
    private int blue;
    @Override
    public boolean update(InfoData si) {

        if (si instanceof SensorData) {
            SensorData infoRecibida = (SensorData) si;
            if (infoRecibida.getDataP().containsKey("bateria")) {
                bateria = Integer.valueOf((String) infoRecibida.getDataP().get("bateria"));
            }
            if (infoRecibida.getDataP().containsKey("activadoParpadear")) {
                activadoParpadear = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoParpadear"));
            }
            if (infoRecibida.getDataP().containsKey("activadoAutoColision")) {
                activadoAutoColision = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoAutoColision"));
            }
            if (infoRecibida.getDataP().containsKey("activadoColisionExterna")) {
                activadoColisionExterna = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoColisionExterna"));
            }
            if (infoRecibida.getDataP().containsKey("activadoRecuperacionEmpuje")) {
                activadoRecuperacionEmpuje = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoRecuperacionEmpuje"));
            }
            if (infoRecibida.getDataP().containsKey("rigidezExtremidades")) {
                rigidezExtremidades = Integer.valueOf((String) infoRecibida.getDataP().get("rigidezExtremidades"));
            }
            if (infoRecibida.getDataP().containsKey("postura")) {
                postura = (String) infoRecibida.getDataP().get("postura");
            }
            if (infoRecibida.getDataP().containsKey("activadoMovEscucha")) {
                activadoMovEscucha = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoMovEscucha"));
            }
            if (infoRecibida.getDataP().containsKey("activadoConsciente")) {
                activadoConsciente = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoConsciente"));
            }
            if (infoRecibida.getDataP().containsKey("activadoSeñalesDeVida")) {
                activadoSeñalesDeVida = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoSeñalesDeVida"));
            }
            if (infoRecibida.getDataP().containsKey("activadoMovHabla")) {
                activadoMovHabla = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoMovHabla"));
            }
            if (infoRecibida.getDataP().containsKey("estaSuspendido")) {
                estaSuspendido = Boolean.valueOf((String) infoRecibida.getDataP().get("estaSuspendido"));
            }
        } else if (si instanceof EmotionalData) {
            EmotionalData infoRecibida = (EmotionalData) si;
            if (infoRecibida.getInfo().containsKey("LEDS")) {
                leds=LedsColor.valueOf((String)infoRecibida.getInfo().get("LEDS"));
            }
            if (infoRecibida.getInfo().containsKey("velocidad")) {
                velocidad = (double)infoRecibida.getInfo().get("velocidad");
            }
            if (infoRecibida.getInfo().containsKey("velHabla")) {
                velHabla=(double)infoRecibida.getInfo().get("velHabla");
            }
            if (infoRecibida.getInfo().containsKey("tonoHabla")) {
                tonoHabla=(double)infoRecibida.getInfo().get("tonoHabla");
            }
            if (infoRecibida.getInfo().containsKey("ledIntens")) {
                ledIntensity=(double)infoRecibida.getInfo().get("ledIntens");
            }
        }
        return true;
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

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    public int getVolumenVoz() {
        return volumenVoz;
    }

    public void setVolumenVoz(int volumenVoz) {
        this.volumenVoz = volumenVoz;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getVelHabla() {
        return velHabla;
    }

    public void setVelHabla(double velHabla) {
        this.velHabla = velHabla;
    }

    public double getTonoHabla() {
        return tonoHabla;
    }

    public void setTonoHabla(double tonoHabla) {
        this.tonoHabla = tonoHabla;
    }

    public double getDistanciaX() {
        return distanciaX;
    }

    public void setDistanciaX(double distanciaX) {
        this.distanciaX = distanciaX;
    }

    public double getDistanciaY() {
        return distanciaY;
    }

    public void setDistanciaY(double distanciaY) {
        this.distanciaY = distanciaY;
    }

    
}
