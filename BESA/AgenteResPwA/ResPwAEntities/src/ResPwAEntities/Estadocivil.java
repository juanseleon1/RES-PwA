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
@Table(name = "ESTADOCIVIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e"),
    @NamedQuery(name = "Estadocivil.findByTipoec", query = "SELECT e FROM Estadocivil e WHERE e.tipoec = :tipoec")})
public class Estadocivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPOEC")
    private String tipoec;
    @OneToMany(mappedBy = "estadocivilTipoec", fetch = FetchType.EAGER)
    private List<Perfilpwa> perfilpwaList;

    public Estadocivil() {
    }

    public Estadocivil(String tipoec) {
        this.tipoec = tipoec;
    }

    public String getTipoec() {
        return tipoec;
    }

    public void setTipoec(String tipoec) {
        this.tipoec = tipoec;
    }

    @XmlTransient
    public List<Perfilpwa> getPerfilpwaList() {
        return perfilpwaList;
    }

    public void setPerfilpwaList(List<Perfilpwa> perfilpwaList) {
        this.perfilpwaList = perfilpwaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoec != null ? tipoec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.tipoec == null && other.tipoec != null) || (this.tipoec != null && !this.tipoec.equals(other.tipoec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Estadocivil[ tipoec=" + tipoec + " ]";
    }
    
}
