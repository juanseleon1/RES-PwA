/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

/**
 *
 * @author juans
 */
public enum PepperEMParams {
    LEDS("LEDS"),FVel("factorVelocidad"),HVel("velHabla"),TONOH("tonoHabla"),LEDINT("ledIntens"),ANIMSTATE("animacion"),LEDROT("rotacion");
    private String tipo;
    private PepperEMParams(String s){
        tipo=s;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
