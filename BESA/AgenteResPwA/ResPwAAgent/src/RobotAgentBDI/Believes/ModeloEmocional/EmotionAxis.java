package RobotAgentBDI.Believes.ModeloEmocional;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.persistence.Entity;

public class EmotionAxis {

    private final String positiveName;
    private final String negativeName;
    private float currentValue;
    private float baseValue;
    private final Map<String, Float> eventInfluence;
    private Float forgetFactor = 0.0f;
    private Date lastForgetUpdateTime;

    public EmotionAxis(String positiveName, String negativeName, float currentValue, float baseValue, Float forgetFactor) {
        this.positiveName = positiveName;
        this.negativeName = negativeName;
        this.forgetFactor = forgetFactor;
        this.setCurrentValue(currentValue);
        this.setBaseValue(baseValue);
        eventInfluence = new HashMap<>();
    }

    public String getPositiveName() {
        return positiveName;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public float getCurrentValue() {
        float newValue = applyForgetFactor(getBaseValue(), currentValue, lastForgetUpdateTime, forgetFactor);
        this.setCurrentValue(newValue);
        return currentValue;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public float getActivationValue() {
        return Math.abs(baseValue - getCurrentValue());
    }

    public Float getForgetFactor() {
        return forgetFactor;
    }

    public void setForgetFactor(Float forgetFactor) {
        this.forgetFactor = forgetFactor;
    }

    public final void setCurrentValue(float value) {
        this.currentValue = Utils.checkNegativeOneToOneLimits(value);
        this.lastForgetUpdateTime = new Date();
    }

    public final void setBaseValue(float value) {
        this.baseValue = Utils.checkNegativeOneToOneLimits(value);
    }

    public void setEventInfluence(String eventName, float influence) {
        eventInfluence.put(eventName, Utils.checkZeroToOneLimits(influence));
    }

    public void setEventInfluences(Map<String, Float> evInfluences) {
        if (evInfluences != null) {
            Iterator itr = evInfluences.keySet().iterator();
            if (itr != null) {
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    Float value = evInfluences.get(key);
                    setEventInfluence(key, value);
                }
            }
        }
    }

    public Float getEventInfluence(String eventName) {
        return eventInfluence.get(eventName);
    }

    public Map<String, Float> getEventInfluences() {
        return eventInfluence;
    }
    public void printEventInfluences(){
        for (String object : eventInfluence.keySet()) {
//            System.out.println("Event: "+object+" Object: "+eventInfluence.get(object));
        }
    }

    public void updateIntensity(String event, float intensity) {
        Float influence = getEventInfluence(event);
        if (influence != null) {
            intensity = influence * intensity;
            setCurrentValue(getCurrentValue() + intensity);
        }
    }

    @Override
    public String toString() {
        String str = this.positiveName + "/" + this.negativeName
                + " {Value:" + this.getCurrentValue() + " Base:" + this.getBaseValue() + "}";
        str += " EvInf: " + eventInfluence.toString();
        return str;
    }

    @Override
    public EmotionAxis clone() throws CloneNotSupportedException {
        EmotionAxis e = new EmotionAxis(this.positiveName, this.negativeName, this.getCurrentValue(), this.baseValue, this.forgetFactor);
        Iterator itr = eventInfluence.keySet().iterator();
        if (itr != null) {
            while (itr.hasNext()) {
                String key = (String) itr.next();
                Float value = eventInfluence.get(key);
                e.setEventInfluence(key, value);
            }
        }
        return e;
    }

    private static float applyForgetFactor(float _baseValue, float _currentValue, Date lastUpdateTime, Float forgetFactor) {
        if (forgetFactor != null) {
            Date now = new Date();
            long tDif = now.getTime() - lastUpdateTime.getTime();
            float slope = ((_baseValue - _currentValue > 0) ? 1 : -1) * forgetFactor / 1000;
            float value = (slope * tDif) + _currentValue;

            if (slope > 0) {
                if (value > _baseValue) {
                    value = _baseValue;
                }
            } else if (slope < 0) {
                if (value <= _baseValue) {
                    value = _baseValue;
                }
            }
            return value;
        }
        return _currentValue;
    }

}
