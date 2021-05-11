package ResPwAEntities.EmotionalEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class EmotionAxisConfig implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "POSITIVENAME")
    private String positivename;
    @Basic(optional = false)
    @Column(name = "NEGATIVENAME")
    private String negativename;
    @Basic(optional = false)
    @Column(name = "BASEVALUE")
    private float basevalue;
    @Basic(optional = false)
    @Column(name = "FORGETFACTOR")
    private float forgetfactor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emotionaxisconfigId", fetch = FetchType.EAGER)
    private List<EventInfluence> eventInfluenceList;



    public EmotionAxisConfig() {

    }

    public EmotionAxisConfig(Long id) {
        this.id = id;
    }

    public EmotionAxisConfig(Long id, String positivename, String negativename, float basevalue, float forgetfactor) {
        this.id = id;
        this.positivename = positivename;
        this.negativename = negativename;
        this.basevalue = basevalue;
        this.forgetfactor = forgetfactor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositivename() {
        return positivename;
    }

    public void setPositivename(String positivename) {
        this.positivename = positivename;
    }

    public String getNegativename() {
        return negativename;
    }

    public void setNegativename(String negativename) {
        this.negativename = negativename;
    }

    public float getBasevalue() {
        return basevalue;
    }

    public void setBasevalue(float basevalue) {
        this.basevalue = basevalue;
    }

    public float getForgetfactor() {
        return forgetfactor;
    }

    public void setForgetfactor(float forgetfactor) {
        this.forgetfactor = forgetfactor;
    }

    @XmlTransient
    public List<EventInfluence> getEventInfluenceList() {
        return eventInfluenceList;
    }

    public void setEventInfluenceList(List<EventInfluence> eventInfluenceList) {
        this.eventInfluenceList = eventInfluenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmotionAxisConfig)) {
            return false;
        }
        EmotionAxisConfig other = (EmotionAxisConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.EmotionalEntities.EmotionAxisConfig[ id=" + id + " ]";
    }

}