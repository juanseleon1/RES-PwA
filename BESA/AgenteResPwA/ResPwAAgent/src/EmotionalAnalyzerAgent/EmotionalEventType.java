package EmotionalAnalyzerAgent;

import RobotAgentBDI.Believes.EstadoEmocional.EmotionalConfig;

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
    SPEECHDETECTED("speechDetected", EmotionalConfig.Events.AlgoDeseable);

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
