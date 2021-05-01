/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

/**
 *
 * @author jsleon
 */
public enum PepperPersonEmotion {
    UNKWONN(0,"UNKWONN"), CALM(1,"CALM"), ANGER(2,"ANGER"), JOY(3,"JOY"), SORROW(4,"SORROW"),LAUGHTER(5,"LAUGHTER"),EXCITEMENT(6,"EXCITEMENT");
    private final int num;
    private final String id;

    private PepperPersonEmotion(int num, String id) {
        this.num = num;
        this.id=id;
    }
    
    public String getId(){
        return id;
    }
    public static String getFromId(int ident) {
            String ret = null;
            for (PepperPersonEmotion sdt : values()) {
                if (sdt.num == ident) {
                    ret = sdt.id;
                    break;
                }
            }
            return ret;
        }
    
    
}