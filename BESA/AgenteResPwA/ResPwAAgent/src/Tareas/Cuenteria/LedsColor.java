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
    BLUE(0x8BCCEC),RED(0xFA3421),GREEN(0x7FF764),WHITE(0xFFFFFF),PURPLE(0xDAA2F8),YELLOW(0xF8FE2E);
    
    private int hexa;
    
    private LedsColor(int hexa) {
        this.hexa=hexa;
    }

    public int getHexa() {
        return hexa;
    }
}
