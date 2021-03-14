package EmotionalAnalyzerAgent;

import java.util.Map;

public enum EmotionalEventType {

    INTERNALUPDT("INTERNAL", 0.1),SPOKE("DialogInput",0.3),NOTLOOKING("personStopsLookingAtRobot",0.3),
    NOTDETECTED("peopleDetected",0.3),NORESPONSE("NORESPONSE",0.3), MOVEDAWAY("personMovedAway",0.3),
    APPROACHED("personApproached",0.3),SMILING("personSmiling",0.3),VOICEEMOTION("voiceEmotionRecognized",0.3),
    GETEMOSTATE("PersonData",0.3), SPEECHDETECTED("speechDetected",0.3);

    public static boolean isImpact(Map<String, Object> dataP) {
        String ident = dataP.keySet().iterator().next();
        System.out.println("PrevIsImpact "+ident);
        boolean ret=getFromId(ident)!=null;
        System.out.println("isImpact "+ ret);
        return ret;
    }
    
    private final String emoType;
    private final double value;

    private EmotionalEventType(String emoType,double value) {
        this.emoType = emoType;
        this.value=value;
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

    public double getValue() {
        return value;
    }

}
