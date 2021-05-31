package EmotionalAnalyzerAgent;

import RobotAgentBDI.Believes.EstadoEmocional.EmotionalConfig;

public enum EmotionalObjectType {

    //CAPERUCITA    
   CERDO("",EmotionalConfig.Objects.Valioso),CASA("",EmotionalConfig.Objects.Valioso),EXITO("",EmotionalConfig.Objects.Importante),COLA("",EmotionalConfig.Objects.Importante),
   REGALO("",EmotionalConfig.Objects.Importante),NATURALEZA("",EmotionalConfig.Objects.Valioso),CAPERUCITA("",EmotionalConfig.Objects.Importante), IDEA_BUENA("",EmotionalConfig.Objects.Valioso),
   ABUELA("",EmotionalConfig.Objects.Importante),LOBO("",EmotionalConfig.Objects.Importante);

    private final String emoType;
    private final EmotionalConfig.Objects config;

    private EmotionalObjectType(String emoType, EmotionalConfig.Objects config) {
        this.emoType = emoType;
        this.config=config;
    }

    public static EmotionalObjectType getFromId(String ident) {
        EmotionalObjectType ret = null;
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

    public EmotionalConfig.Objects getConfigEnum(){
        return config;
    }
    
    public String getConfig(){
        return config.toString();
    }

}
