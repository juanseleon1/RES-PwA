package EmotionalAnalyzerAgent.Utils;

import RobotAgentBDI.Believes.ModeloEmocional.EmotionalConfig;

public enum EmotionalObjectType {

    //CAPERUCITA    
    CERDO("CERDO", EmotionalConfig.Objects.Valioso), CASA("CASA", EmotionalConfig.Objects.Valioso), EXITO("EXITO", EmotionalConfig.Objects.Importante), COLA("COLA", EmotionalConfig.Objects.Importante),
    REGALO("REGALO", EmotionalConfig.Objects.Importante), NATURALEZA("NATURALEZA", EmotionalConfig.Objects.Valioso), CAPERUCITA("CAPERUCITA", EmotionalConfig.Objects.Importante), IDEABUENA("IDEABUENA", EmotionalConfig.Objects.Valioso),
    ABUELA("ABUELA", EmotionalConfig.Objects.Importante), LOBO("LOBO", EmotionalConfig.Objects.Importante), NULL("",EmotionalConfig.Objects.Indiferente);

    private final String emoType;
    private final EmotionalConfig.Objects config;

    private EmotionalObjectType(String emoType, EmotionalConfig.Objects config) {
        this.emoType = emoType;
        this.config = config;
    }

    public static EmotionalObjectType getFromId(String ident) {
        EmotionalObjectType ret = NULL;
        for (EmotionalObjectType sdt : values()) {
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

    public EmotionalConfig.Objects getConfigEnum() {
        return config;
    }

    public String getConfig() {
        return config.toString();
    }

}
