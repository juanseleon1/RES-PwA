/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent.Utils;

import static EmotionalAnalyzerAgent.Utils.EmotionalObjectType.values;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalConfig;

/**
 *
 * @author jsleon
 */
public enum EmotionalSubjectType {

    ROBOT("ROBOT",EmotionalConfig.People.Amigo), PWA("PWA",EmotionalConfig.People.Cercano), ABUELA("ABUELA",EmotionalConfig.People.Cercano), CAPERUCITA("CAPERUCITA",EmotionalConfig.People.Cercano), 
    LOBO("LOBO",EmotionalConfig.People.Enemigo),
    CERDO("CERDO",EmotionalConfig.People.Cercano), FAMILIA("FAMILIA",EmotionalConfig.People.Amigo);
   
    private final String emoType;
    private EmotionalConfig.People config;

    private EmotionalSubjectType(String emoType,EmotionalConfig.People config) {
        this.emoType = emoType;
        this.config = config;
    }

    public EmotionalConfig.People getConfigEnum() {
        return config;
    }

    public String getConfig() {
        return config.toString();
    }
    
     public static EmotionalSubjectType getFromId(String ident) {
        EmotionalSubjectType ret = null;
        for (EmotionalSubjectType sdt : values()) {
            if (sdt.emoType.equalsIgnoreCase(ident)) {
                ret = sdt;
                break;
            }
        }
        return ret;
    }
    
    public String getEmoType() {
        return emoType;
    }
}
