package ResPwAEntities.EmotionalEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class EmotionAxisConfig implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(nullable= false, unique=false)
    private String positiveName;
    @Basic
    @Column(nullable= false, unique=false)
    private String negativeName;
    @Basic
    @Column(nullable= false, unique=false)
    private float baseValue;
    @JoinColumn(name = "EVTINF_ID", referencedColumnName = "ID")
    @OneToMany(fetch = FetchType.EAGER)
    private List<EventInfluence> eventInfluence;
    @Basic
    @Column(nullable= false, unique=false)
    private float forgetFactor;



    public EmotionAxisConfig() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositiveName() {
        return positiveName;
    }

    public void setPositiveName(String positiveName) {
        this.positiveName = positiveName;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public void setNegativeName(String negativeName) {
        this.negativeName = negativeName;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public List<EventInfluence> getEventInfluence() {
        return eventInfluence;
    }

    public void setEventInfluence(List<EventInfluence> eventInfluence) {
        this.eventInfluence = eventInfluence;
    }

    public Float getForgetFactor() {
        return forgetFactor;
    }

    public void setForgetFactor(Float forgetFactor) {
        this.forgetFactor = forgetFactor;
    }

}