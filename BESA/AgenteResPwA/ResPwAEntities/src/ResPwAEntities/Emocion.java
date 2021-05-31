/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "EMOCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emocion.findAll", query = "SELECT e FROM Emocion e"),
    @NamedQuery(name = "Emocion.findById", query = "SELECT e FROM Emocion e WHERE e.id = :id"),
    @NamedQuery(name = "Emocion.findByEmotionaltag", query = "SELECT e FROM Emocion e WHERE e.emotionaltag = :emotionaltag"),
    @NamedQuery(name = "Emocion.findByImagen", query = "SELECT e FROM Emocion e WHERE e.imagen = :imagen")})
public class Emocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @Column(name = "EMOTIONALTAG")
    private String emotionaltag;
    @Basic(optional = false)
    @Column(name = "IMAGEN")
    private String imagen;
    @JoinColumn(name = "ROBOT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Robot robotId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emocionId", fetch = FetchType.EAGER)
    private List<Accion> accionList;

    public Emocion() {
    }

    public Emocion(String id) {
        this.id = id;
    }

    public Emocion(String id, String emotionaltag, String imagen) {
        this.id = id;
        this.emotionaltag = emotionaltag;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmotionaltag() {
        return emotionaltag;
    }

    public void setEmotionaltag(String emotionaltag) {
        this.emotionaltag = emotionaltag;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Robot getRobotId() {
        return robotId;
    }

    public void setRobotId(Robot robotId) {
        this.robotId = robotId;
    }

    @XmlTransient
    public List<Accion> getAccionList() {
        return accionList;
    }

    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
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
        if (!(object instanceof Emocion)) {
            return false;
        }
        Emocion other = (Emocion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Emocion[ id=" + id + " ]";
    }
    
}
