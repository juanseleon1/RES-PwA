/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.Kernel.Agent.StateBESA;

/**
 *
 * @author juans
 */
public class EmotionalAnalyzerState extends StateBESA{
    private EmotionalAnalyzerStrategy eaStrategy;
    private EmotionalModel eaModel;
    
    
    public EmotionalAnalyzerState(EmotionalAnalyzerStrategy eas, EmotionalModel em){
        eaStrategy=eas;
        eaModel=em;
    }
    public EmotionalAnalyzerStrategy getEaStrategy() {
        return eaStrategy;
    }

    public void setEaStrategy(EmotionalAnalyzerStrategy eaStrategy) {
        this.eaStrategy = eaStrategy;
    }

    public EmotionalModel getEaModel() {
        return eaModel;
    }

    public void setEaModel(EmotionalModel eaModel) {
        this.eaModel = eaModel;
    }
    
    
}
