package RobotAgentBDI.Believes.EstadoEmocional;

import java.util.HashMap;
import java.util.Map;

public class Personality {

    public enum EmotionElementType {

        Object, Person, Event
    };

    private final Map<String, String> objectRelationships;
    private final Map<String, String> personRelationships;
    private final Map<String, String> eventDesirability;

    public Personality() {
        personRelationships = new HashMap<>();
        objectRelationships = new HashMap<>();
        eventDesirability = new HashMap<>();
    }

    private Map getList(EmotionElementType t) {
        if (t.equals(EmotionElementType.Object)) {
            return this.objectRelationships;
        } else if (t.equals(EmotionElementType.Person)) {
            return this.personRelationships;
        } else if (t.equals(EmotionElementType.Event)) {
            return this.eventDesirability;
        }
        return null;
    }

    public void setPersonRelationship(String person, String relationship) {
        personRelationships.put(person, relationship);
    }

    public void setObjectRelationship(String object, String relationship) {
        objectRelationships.put(object, relationship);
    }

    public void setEventDesirability(String event, String desirability) {
        eventDesirability.put(event, desirability);
    }

    public Float getElementSemanticValue(EmotionElementType t, String name) {
        String val = (String)getList(t).get(name);
        if (val != null) {
            return SemanticDictionary.getInstance().getSemanticValue(t, val);
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "Objects" + " -> " + objectRelationships.toString()
                + "\r\nPersons" + " -> " + personRelationships.toString()
                + "\r\nEvents" + " -> " + eventDesirability.toString();
        return str;
    }

}
