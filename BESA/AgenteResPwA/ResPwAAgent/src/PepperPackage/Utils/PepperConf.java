/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.Utils;

/**
 *
 * @author juans
 */
public enum PepperConf {
    SPEED(1.6f,0.9f),PITCH(1.4f,1.2f),LEDINTENSITY(1,0.1f),TALKSPEED(125,50),DURATION(0.1f,1);

    private final float max,min;

    private PepperConf(float max,float min){
        this.max=max;
        this.min=min;
    }
    
    public float getMax(){
        return max;
    }
 
    
    public float getMin(){
        return min;
    }
}
