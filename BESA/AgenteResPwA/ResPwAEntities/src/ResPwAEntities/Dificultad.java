/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "DIFICULTAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dificultad.findAll", query = "SELECT d FROM Dificultad d")
    , @NamedQuery(name = "Dificultad.findByDificultad", query = "SELECT d FROM Dificultad d WHERE d.dificultad = :dificultad")})
public class Dificultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DIFICULTAD")
    private String dificultad;
    @OneToMany(mappedBy = "dificultadDificultad")
    private List<Actividadpwa> actividadpwaList;

    public Dificultad() {
    }

    public Dificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    @XmlTransient
    public List<Actividadpwa> getActividadpwaList() {
        return actividadpwaList;
    }

    public void setActividadpwaList(List<Actividadpwa> actividadpwaList) {
        this.actividadpwaList = actividadpwaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dificultad != null ? dificultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dificultad)) {
            return false;
        }
        Dificultad other = (Dificultad) object;
        if ((this.dificultad == null && other.dificultad != null) || (this.dificultad != null && !this.dificultad.equals(other.dificultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Dificultad[ dificultad=" + dificultad + " ]";
    }
    
}
