/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

/**
 *
 * @author mafegarces
 */
public enum LedsColor {
    BLUE(0.28,0.72,0.96),
    BLGRE(0.46,0.79,0.79),
    GREEN(0.63,0.84,0.49),
    GREYEL(0.80,0.91,0.44),
    YELLOW(0.98,0.97,0.26);
    
    private double r;
    private double g;
    private double b;
    
    private LedsColor(double r, double g, double b) {
        this.r=r;
        this.g=g;
        this.b=b;
    }
    
    
}
