/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

/**
 *
 * @author mafegarces
 */
public enum LedsColor {
    BLUE(0x8BCCEC, -1, -0.6),RED(0xFA3421, -0.6, -0.2),WHITE(0xFFFFFF, -0-2, 0.2),GREEN(0x7FF764, 0.2, 0.6),YELLOW(0xF8FE2E, 0.6, 1);
    
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
