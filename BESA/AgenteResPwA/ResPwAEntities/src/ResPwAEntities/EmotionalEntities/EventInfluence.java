/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.EmotionalEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author juans
 */
@Entity
public class EventInfluence implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EVENTNAME")
    private String eventname;
    @Basic(optional = false)
    @Column(name = "EVENTINFLUENCE")
    private double eventinfluence;
    @JoinColumn(name = "EMOTIONAXISCONFIG_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EmotionAxisConfig emotionaxisconfigId;

    public EventInfluence() {
    }

    public EventInfluence(Long id) {
        this.id = id;
    }

    public EventInfluence(Long id, String eventname, double eventinfluence) {
        this.id = id;
        this.eventname = eventname;
        this.eventinfluence = eventinfluence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public double getEventinfluence() {
        return eventinfluence;
    }

    public void setEventinfluence(double eventinfluence) {
        this.eventinfluence = eventinfluence;
    }

    public EmotionAxisConfig getEmotionaxisconfigId() {
        return emotionaxisconfigId;
    }

    public void setEmotionaxisconfigId(EmotionAxisConfig emotionaxisconfigId) {
        this.emotionaxisconfigId = emotionaxisconfigId;
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
        if (!(object instanceof EventInfluence)) {
            return false;
        }
        EventInfluence other = (EventInfluence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.EmotionalEntities.EventInfluence[ id=" + id + " ]";
    }
    
}
