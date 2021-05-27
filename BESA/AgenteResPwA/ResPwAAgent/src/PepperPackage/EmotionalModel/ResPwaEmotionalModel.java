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
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalEventType;
import EmotionalAnalyzerAgent.WHO;
import Init.InitRESPwA;
import ResPwAEntities.EmotionalEntities.EmotionAxisConfig;
import ResPwAEntities.EmotionalEntities.EventInfluence;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionAxis;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalConfig;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalModel;
import RobotAgentBDI.Believes.EstadoEmocional.Personality;
import RobotAgentBDI.Believes.EstadoEmocional.SemanticDictionary;
import RobotAgentBDI.Believes.EstadoEmocional.SemanticValue;
import RobotAgentBDI.ResPwaUtils;
import ServiceAgentResPwA.ServiceDataRequest;
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
         for(EmotionalConfig.People who:EmotionalConfig.People.values()){
            sd.addSemanticItem(Personality.EmotionElementType.Person, new SemanticValue(who.toString(), who.getValue()));
         }
         
         for(EmotionalConfig.Events evt: EmotionalConfig.Events.values()){
            sd.addSemanticItem(Personality.EmotionElementType.Event, new SemanticValue(evt.toString(),evt.getValue()));
         }
         
        for(EmotionalConfig.Objects obj: EmotionalConfig.Objects.values()){
            sd.addSemanticItem(Personality.EmotionElementType.Object, new SemanticValue(obj.toString(),obj.getValue()));
         }

    }

    @Override
    public void loadCharacterDescriptor() {
        
         for(WHO who:WHO.values()){
             setPersonRelationship(who.toString(), who.getConfig());
         }
         
         for(EmotionalEventType evt: EmotionalEventType.values()){
             setEventDesirability(evt.toString(), evt.getConfig());
         }
         
    }

    
    @Override
    public void loadEmotionalAxes() {
        List <EmotionAxis> emoax= new ArrayList<>();
        EmotionAxis emoAxis;
        List <EmotionAxisConfig> aux= RESPwABDInterface.getEmotionalAxisConfig();
        List<EventInfluence> evtinf;
        for (EmotionAxisConfig emotionAxisConfig : aux) {
            emoAxis= new EmotionAxis(emotionAxisConfig.getPositiveName(), emotionAxisConfig.getNegativeName(), emotionAxisConfig.getBaseValue(), emotionAxisConfig.getBaseValue(), emotionAxisConfig.getForgetFactor());
            evtinf=emotionAxisConfig.getEventInfluence();
            for (EventInfluence eventInfluence : evtinf) {
                emoAxis.setEventInfluence(eventInfluence.getEventName(), (float) eventInfluence.getEventInfluence());
            }
//            System.out.println("Adding emotional axis: "+ emoAxis.toString());
            this.addEmotionAxis(emoAxis);
        }
//        emotionalState.getEmotions().get(0).printEventInfluences();
//        System.out.println("Emotions "+emotionalState.getEmotions().get(0).getEventInfluences().size() );
}
    
    
}