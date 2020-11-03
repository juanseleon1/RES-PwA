/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

/**
 *
 * @author mafegarces
 */
public enum LedsColor {
    BLUE(72,185,246),
    YELLOW(250,247,67);
    
    private Integer r;
    private Integer g;
    private Integer b;
    
    private LedsColor(Integer r, Integer g, Integer b) {
        this.r=r;
        this.g=g;
        this.b=b;
    }
    
    
}
