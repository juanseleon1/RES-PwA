/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent.Agent;

import BESA.Kernel.Agent.StateBESA;

/**
 *
 * @author juans
 */
public class EmotionalAnalyzerState extends StateBESA{
    private EmotionalAnalyzerStrategy eaStrategy;
    
    
    public EmotionalAnalyzerState(EmotionalAnalyzerStrategy eas){
        eaStrategy=eas;
    }
    public EmotionalAnalyzerStrategy getEaStrategy() {
        return eaStrategy;
    }

    public void setEaStrategy(EmotionalAnalyzerStrategy eaStrategy) {
        this.eaStrategy = eaStrategy;
    }

}
