/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import BDInterface.RESPwABDInterface;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import EmotionalAnalyzerAgent.Utils.EmotionalEventType;
import EmotionalAnalyzerAgent.Utils.EmotionalObjectType;
import EmotionalAnalyzerAgent.Utils.EmotionalSubjectType;

import Init.InitRESPwA;
import ResPwAEntities.EmotionalEntities.EmotionAxisConfig;
import ResPwAEntities.EmotionalEntities.EventInfluence;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionAxis;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalConfig;
import RobotAgentBDI.Believes.ModeloEmocional.EmotionalModel;
import RobotAgentBDI.Believes.ModeloEmocional.Personality;
import RobotAgentBDI.Believes.ModeloEmocional.SemanticDictionary;
import RobotAgentBDI.Believes.ModeloEmocional.SemanticValue;
import Utils.ResPwaUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public abstract class ResPwaEmotionalModel extends EmotionalModel {

    public ResPwaEmotionalModel() {
        super();

    }

    @Override
    public void loadSemanticDictionary() {

        SemanticDictionary sd = SemanticDictionary.getInstance();
        for (EmotionalConfig.People who : EmotionalConfig.People.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Person, new SemanticValue(who.toString(), who.getValue()));
        }

        for (EmotionalConfig.Events evt : EmotionalConfig.Events.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Event, new SemanticValue(evt.toString(), evt.getValue()));
        }

        for (EmotionalConfig.Objects obj : EmotionalConfig.Objects.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Object, new SemanticValue(obj.toString(), obj.getValue()));
        }

    }

    @Override
    public void loadCharacterDescriptor() {

        for (EmotionalSubjectType who : EmotionalSubjectType.values()) {
            setPersonRelationship(who.toString(), who.getConfig());
        }

        for (EmotionalEventType evt : EmotionalEventType.values()) {
            setEventDesirability(evt.toString(), evt.getConfig());
        }

        for (EmotionalObjectType obj : EmotionalObjectType.values()) {
            if(!obj.equals(EmotionalObjectType.NULL)){
                setObjectRelationship(obj.toString(), obj.getConfig());
            }
        }

    }

    @Override
    public void loadEmotionalAxes() {
        List<EmotionAxis> emoax = new ArrayList<>();
        EmotionAxis emoAxis;
        List<EmotionAxisConfig> aux = RESPwABDInterface.getEmotionalAxisConfig();
        List<EventInfluence> evtinf;
        for (EmotionAxisConfig emotionAxisConfig : aux) {
            emoAxis = new EmotionAxis(emotionAxisConfig.getPositiveName(), emotionAxisConfig.getNegativeName(), emotionAxisConfig.getBaseValue(), emotionAxisConfig.getBaseValue(), emotionAxisConfig.getForgetFactor());
            evtinf = emotionAxisConfig.getEventInfluence();
            for (EventInfluence eventInfluence : evtinf) {
                emoAxis.setEventInfluence(eventInfluence.getEventName(), (float) eventInfluence.getEventInfluence());
            }
            this.addEmotionAxis(emoAxis);
        }
    }

}
