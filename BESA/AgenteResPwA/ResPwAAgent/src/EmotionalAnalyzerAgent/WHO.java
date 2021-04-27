/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import RobotAgentBDI.Believes.EstadoEmocional.EmotionalConfig;

/**
 *
 * @author jsleon
 */
public enum WHO {
    
    ROBOT(EmotionalConfig.People.Amigo),PWA(EmotionalConfig.People.Cercano);
    
    private EmotionalConfig.People config;
    
    private WHO(EmotionalConfig.People config){
        this.config=config;
    }
    
    public EmotionalConfig.People getConfigEnum(){
        return config;
    }
    
    public String getConfig(){
        return config.toString();
    }
}
