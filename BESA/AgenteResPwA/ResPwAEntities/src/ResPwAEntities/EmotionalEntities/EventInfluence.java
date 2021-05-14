/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.EmotionalEntities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author juans
 */
@Entity
public class EventInfluence implements Serializable {

    @Id
    private Long id;    
    @Basic
    @Column(nullable= false, unique=false)
    String eventName;
    @Basic
    @Column(nullable= false, unique=false)
    Float eventInfluence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Float getEventInfluence() {
        return eventInfluence;
    }

    public void setEventInfluence(Float eventInfluence) {
        this.eventInfluence = eventInfluence;
    }
    
}   