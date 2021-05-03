/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage;

/**
 *
 * @author juans
 */
public enum PepperConf {
    
    SPEED(1.5f,0.5f),PITCH(200,100),LEDINTENSITY(1,0.1f),TALKSPEED(70,30);
    
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
