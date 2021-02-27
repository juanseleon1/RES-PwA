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
    BLUE(0x8BCCEC, 1, 1.4),RED(0xFA3421, 2.3, 2.6),GREEN(0x7FF764, 1.8, 2.4),WHITE(0xFFFFFF, 0, 0),PURPLE(0xDAA2F8, 1.4, 1.8),YELLOW(0xF8FE2E, 2.6, 3);
    
    private int hexa;
    private double min;
    private double max;
    
    private LedsColor(int hexa, double min, double max) {
        this.hexa = hexa;
        this.min = min;
        this.max = max;
    }

    public int getHexa() {
        return hexa;
    }
    
    public double getMin() {
        return min;
    }
    
    public double getMax() {
        return max;
    }
}
