package EmotionalAnalyzerAgent.Utils;

import RobotAgentBDI.Believes.ModeloEmocional.EmotionalConfig;

public enum EmotionalEventType {

    INTERNALPOSPLUS("INTERNALPOSPLUS",  EmotionalConfig.Events.Deseable),INTERNALPOS("INTERNALPOS",  EmotionalConfig.Events.AlgoDeseable),
    INTERNALNEG("INTERNALNEG",  EmotionalConfig.Events.AlgoIndeseable),INTERNALNEGPLUS("INTERNALNEGPLUS",  EmotionalConfig.Events.Indeseable),
    SPOKE("DialogInput", EmotionalConfig.Events.AlgoDeseable),
    NOTLOOKING("personStopsLookingAtRobot", EmotionalConfig.Events.AlgoIndeseable),NOTDETECTED("peopleDetected", EmotionalConfig.Events.AlgoDeseable),
    NORESPONSE("NORESPONSE", EmotionalConfig.Events.Indeseable), MOVEDAWAY("personMovedAway", EmotionalConfig.Events.AlgoIndeseable),
    APPROACHED("personApproached", EmotionalConfig.Events.AlgoDeseable),SMILING("personSmiling", EmotionalConfig.Events.Deseable),
    VOICEEMOTION("voiceEmotionRecognized", EmotionalConfig.Events.Indiferente), POSVOICEEMOTION("POSVOICEEMOTION", EmotionalConfig.Events.AlgoDeseable),
    NEGVOICEEMOTION("NEGVOICEEMOTION", EmotionalConfig.Events.AlgoIndeseable),  GETEMOSTATE("PersonData", EmotionalConfig.Events.Indiferente),
    POSEMOSTATE("PersonDataPos", EmotionalConfig.Events.Deseable), NEGEMOSTATE("PersonDataNeg", EmotionalConfig.Events.Indeseable),
    SPEECHDETECTED("speechDetected", EmotionalConfig.Events.AlgoDeseable),
    //CAPERUCITA
    
   ENFERMAR("ENFERMAR",EmotionalConfig.Events.Indeseable),ALEGRARSE("ALEGRARSE",EmotionalConfig.Events.Deseable),ASUSTADA("ASUSTADA",EmotionalConfig.Events.Indeseable),
   VER("VER",EmotionalConfig.Events.Indiferente),
   EMOCIONAR("EMOCIONAR",EmotionalConfig.Events.AlgoDeseable),DEVORAR("DEVORAR",EmotionalConfig.Events.Indeseable),ESCAPAR("ESCAPAR",EmotionalConfig.Events.Deseable), 
   HUIR("HUIR",EmotionalConfig.Events.AlgoDeseable),
   ABRAZAR("ABRAZAR",EmotionalConfig.Events.Deseable),LLEVAR("LLEVAR",EmotionalConfig.Events.Indiferente),OBSERVAR("OBSERVAR",EmotionalConfig.Events.Indiferente),
   HABLA("HABLA",EmotionalConfig.Events.Indiferente),
   AMENAZAR("AMENAZAR",EmotionalConfig.Events.AlgoIndeseable),
   APRECIAR("APRECIAR",EmotionalConfig.Events.Deseable),ENGANAR("ENGANAR",EmotionalConfig.Events.Indeseable),TRAICIONAR("TRAICIONAR",EmotionalConfig.Events.Indeseable),
   SOSPECHAR("SOSPECHAR",EmotionalConfig.Events.Indeseable),SORPRENDER("SORPRENDER",EmotionalConfig.Events.AlgoDeseable),
   GRITAR("GRITAR",EmotionalConfig.Events.Deseable),

   //cerditos
   PERSEGUIR("PERSEGUIR",EmotionalConfig.Events.Indeseable),SONAR("SONAR",EmotionalConfig.Events.Indiferente),CONSTRUIR("CONSTRUIR",EmotionalConfig.Events.AlgoDeseable),APARECER("APARECER",EmotionalConfig.Events.Indiferente),
   DESTRUIR("DESTRUIR",EmotionalConfig.Events.Deseable),REFUGIARSE("REFUGIARSE",EmotionalConfig.Events.AlgoDeseable),CELEBRAR("CELEBRAR",EmotionalConfig.Events.Deseable),VITOREAR("VITOREAR",EmotionalConfig.Events.Deseable),
   QUEMAR("QUEMAR",EmotionalConfig.Events.Indeseable);
   
   
    private final String emoType;
    private final EmotionalConfig.Events config;

    private EmotionalEventType(String emoType, EmotionalConfig.Events config) {
        this.emoType = emoType;
        this.config=config;
    }

    public static EmotionalEventType getFromId(String ident) {
        EmotionalEventType ret = null;
        for (EmotionalEventType sdt : values()) {
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

    public EmotionalConfig.Events getConfigEnum(){
        return config;
    }
    
    public String getConfig(){
        return config.toString();
    }
    

}
