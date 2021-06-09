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
@Table(name = "NIVEL_EDUCATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEducativo.findAll", query = "SELECT n FROM NivelEducativo n"),
    @NamedQuery(name = "NivelEducativo.findByTipone", query = "SELECT n FROM NivelEducativo n WHERE n.tipone = :tipone")})
public class NivelEducativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPONE")
    private String tipone;
    @OneToMany(mappedBy = "nivelEducativoTipone", fetch = FetchType.EAGER)
    private List<Perfilpwa> perfilpwaList;

    public NivelEducativo() {
    }

    public NivelEducativo(String tipone) {
        this.tipone = tipone;
    }

    public String getTipone() {
        return tipone;
    }

    public void setTipone(String tipone) {
        this.tipone = tipone;
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
        hash += (tipone != null ? tipone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEducativo)) {
            return false;
        }
        NivelEducativo other = (NivelEducativo) object;
        if ((this.tipone == null && other.tipone != null) || (this.tipone != null && !this.tipone.equals(other.tipone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.NivelEducativo[ tipone=" + tipone + " ]";
    }
    
}
