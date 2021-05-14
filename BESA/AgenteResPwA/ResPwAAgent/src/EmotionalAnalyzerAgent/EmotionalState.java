/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

/**
 * @author jsleon
 */

public class EmotionalState {
    
    private EmotionPwA dominantEmotion;
    private double influenceFactor;

    public EmotionPwA getDominantEmotion() {
        return dominantEmotion;
    }

    public void setDominantEmotion(EmotionPwA dominantEmotion) {
        this.dominantEmotion = dominantEmotion;
    }

    public double getInfluenceFactor() {
        return influenceFactor;
    }

    public void setInfluenceFactor(double influenceFactor) {
        this.influenceFactor = influenceFactor;
    }
    
}
