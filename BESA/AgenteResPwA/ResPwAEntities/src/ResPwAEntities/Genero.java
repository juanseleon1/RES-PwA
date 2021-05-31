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
@Table(name = "GENERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
    @NamedQuery(name = "Genero.findByGenero", query = "SELECT g FROM Genero g WHERE g.genero = :genero")})
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GENERO")
    private String genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoGenero", fetch = FetchType.EAGER)
    private List<Cancion> cancionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoGenero", fetch = FetchType.EAGER)
    private List<Baile> baileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoGenero", fetch = FetchType.EAGER)
    private List<Cuento> cuentoList;

    public Genero() {
    }

    public Genero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @XmlTransient
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    @XmlTransient
    public List<Baile> getBaileList() {
        return baileList;
    }

    public void setBaileList(List<Baile> baileList) {
        this.baileList = baileList;
    }

    @XmlTransient
    public List<Cuento> getCuentoList() {
        return cuentoList;
    }

    public void setCuentoList(List<Cuento> cuentoList) {
        this.cuentoList = cuentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genero != null ? genero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.genero == null && other.genero != null) || (this.genero != null && !this.genero.equals(other.genero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Genero[ genero=" + genero + " ]";
    }
    
}
