package RobotAgentBDI.Believes.ModeloEmocional;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import EmotionalAnalyzerAgent.Guards.EmotionalData;
import Init.InitRESPwA;
import RobotAgentBDI.Believes.ModeloEmocional.Personality.EmotionElementType;
import Utils.ResPwaUtils;

import ServiceAgentResPwA.Guard.ServiceDataRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;
import rational.services.ActivateAsynchronousServiceGuard;

public abstract class EmotionalModel {

    protected final EmotionalState emotionalState;
    protected final Personality personality;

    public EmotionalModel() {
        this.emotionalState = new EmotionalState();
        this.personality = new Personality();
        this.configureEmotionalModel();
    }

    public void addEmotionAxis(EmotionAxis ea) {
        try {
            emotionalState.addEmotionAxis(ea.clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(EmotionalModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPersonRelationship(String person, String relationship) {
        checkItemInSemanticDictionary(EmotionElementType.Person, relationship);
        personality.setPersonRelationship(person, relationship);
    }

    public void setObjectRelationship(String object, String relationship) {
        checkItemInSemanticDictionary(EmotionElementType.Object, relationship);
        personality.setObjectRelationship(object, relationship);
    }

    public void setEventDesirability(String event, String desirability) {
        checkItemInSemanticDictionary(EmotionElementType.Event, desirability);
        personality.setEventDesirability(event, desirability);
    }

    public void processEmotionalEvent(EmotionalEvent ev) {
        float i = estimateEmotionIntensity(ev);
        if(ev.getPerson()!=null){
          System.out.println("XEREVENTO: "+ev + " Valencia"+i);
        }
        emotionalState.updateEmotions(ev.getEvent(), i);
        System.out.println(ev.toString());
        emotionalStateChanged();
    }

    private float estimateEmotionIntensity(EmotionalEvent ev) {
        Float person = personality.getElementSemanticValue(EmotionElementType.Person, ev.getPerson());
        Float event = personality.getElementSemanticValue(EmotionElementType.Event, ev.getEvent());
        Float object = personality.getElementSemanticValue(EmotionElementType.Object, ev.getObject());

        person = (person == null) ? 0 : person;
        event = (event == null) ? 0 : event;
        object = (object == null) ? 0 : object;

        float intensity = Utils.Config.PersonWeight * Math.abs(person)
                + Utils.Config.EventWeight * Math.abs(event)
                + Utils.Config.ObjectWeight * Math.abs(object);
        boolean valence = estimateValence(person, event, object);
//        System.out.println("P:" + person + " E:" + event + " O:" + object);
//        System.out.println("Val: " + valence);
        intensity = (valence ? 1 : -1) * intensity;
        return intensity;
    }

    private boolean estimateValence(Float person, Float event, Float object) {
        boolean v = false;

        person = (person == null || person == 0) ? 1 : person;
        event = (event == null || event == 0) ? 1 : event;
        object = (object == null || object == 0) ? 1 : object;

        person = person / Math.abs(person);
        event = event / Math.abs(event);
        object = object / Math.abs(object);

        //System.out.println("Valence P:" + person + " E:" + event + " O:" + object);
        if ((person.equals(event) && event.equals(object))
                || (person.equals(1f) && event.equals(-1f) && object.equals(-1f))) {
            v = true;
        }

        return v;
    }

    public abstract void emotionalStateChanged();
    
    public EmotionAxis getMostActivatedEmotion() throws CloneNotSupportedException {
        return this.emotionalState.getMostActivatedEmotion();
    }

    public List<EmotionAxis> getEmotionsListCopy() throws CloneNotSupportedException {
        return this.emotionalState.getEmotionsListCopy();
    }

    @Override
    public String toString() {
        return personality.toString()
                + "\r\n" + emotionalState.toString();
    }

    private void checkItemInSemanticDictionary(EmotionElementType type, String key) {
        Float v = SemanticDictionary.getInstance().getSemanticValue(type, key);
        if (v == null) {
            String typeName = "Personas";
            if (type == EmotionElementType.Event) {
                typeName = "Eventos";
            } else if (type == EmotionElementType.Object) {
                typeName = "Objetos";
            }
            String msg = "El diccionario semántico de " + typeName + " no contiene un item con el nombre " + key;
            System.out.println("ERROR: "+msg);
            Logger.getLogger(EmotionalModel.class.getName()).log(Level.WARNING, msg);
        }
    }
    
    
        protected void sendAct(EmotionalData ed) throws ExceptionBESA{
        AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(InitRESPwA.aliasRobotAgent);
        EventBESA sensorEvtA= new EventBESA(InformationFlowGuard.class.getName(),ed);
        handler.sendEvent(sensorEvtA);
    }
    
    private void configureEmotionalModel(){
        loadSemanticDictionary();
        loadCharacterDescriptor();
        loadEmotionalAxes();
    }
    
        protected EmotionAxis getTopEmotionAxis() throws CloneNotSupportedException {
            EmotionAxis maxAx=null;
            double val=Double.NEGATIVE_INFINITY;
        List<EmotionAxis> emoList = emotionalState.getEmotionsListCopy();
//            System.out.println("Ejes de la lista: " + emoList.size());
        for (EmotionAxis e : emoList) {
                if(e.getCurrentValue()> val){
                    maxAx=e;
                    val=e.getCurrentValue();
                }
        }
        return maxAx;
    }
    
    public abstract void loadSemanticDictionary();
    public abstract void loadCharacterDescriptor();
    public abstract void loadEmotionalAxes();




}
