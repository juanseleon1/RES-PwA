/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import EmotionalAnalyzerAgent.Utils.EmotionPwA;
import java.time.LocalTime;

/**
 *
 * @author juans
 */
public class Emotion {
    
    private LocalTime time;
    private float valence;

    public Emotion(float valence) {
        this.valence = valence;
        this.time= LocalTime.now();
    }
    

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        this.valence = valence;
    }

}
