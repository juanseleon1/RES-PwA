/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

/**
 *
 * @author jsleon
 */
public enum PepperEmoResponses {

        VOICEEMOTION("voiceEmotionRecognized"), GETEMOSTATE("PersonData");
        private final String emoType;

        private PepperEmoResponses(String emoType) {
            this.emoType = emoType;
        }

        public static PepperEmoResponses getFromId(String ident) {
            PepperEmoResponses ret = null;
            for (PepperEmoResponses sdt : values()) {
                if (sdt.emoType.equalsIgnoreCase(ident)) {
                    ret = sdt;
                    break;
                }
            }
            return ret;
        }
        
        public String getEmoType(){
            return emoType;
        }

    }