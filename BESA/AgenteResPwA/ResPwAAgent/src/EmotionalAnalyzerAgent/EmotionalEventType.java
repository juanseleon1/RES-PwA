package EmotionalAnalyzerAgent;

public enum EmotionalEventType {

    NOTLOOKING("NOTLOOKING"), NORESPONSE("NORESPONSE"), VOICEEMOTION("voiceEmotionRecognized"), GETEMOSTATE("PersonData");
    private final String emoType;

    private EmotionalEventType(String emoType) {
        this.emoType = emoType;
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

}
