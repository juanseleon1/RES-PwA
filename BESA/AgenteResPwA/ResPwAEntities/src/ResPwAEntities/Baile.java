/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

/**
 *
 * @author ASUS
 */
public class Baile {
    
    private String nombre;
    private double gusto;

    public Baile(String nombre, double gusto) {
        this.nombre = nombre;
        this.gusto = gusto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }
    
}
