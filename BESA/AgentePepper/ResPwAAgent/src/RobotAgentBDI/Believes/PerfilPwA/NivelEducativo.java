/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA;

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
@Table(name = "NIVEL_EDUCATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEducativo.findAll", query = "SELECT n FROM NivelEducativo n"),
    @NamedQuery(name = "NivelEducativo.findByTiponiveleducativo", query = "SELECT n FROM NivelEducativo n WHERE n.tiponiveleducativo = :tiponiveleducativo")})
public class NivelEducativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPONIVELEDUCATIVO")
    private String tiponiveleducativo;
    @OneToMany(mappedBy = "nivelEducativoTiponiveleducativo")
    private List<Perfilpwa> perfilpwaList;

    public NivelEducativo() {
    }

    public NivelEducativo(String tiponiveleducativo) {
        this.tiponiveleducativo = tiponiveleducativo;
    }

    public String getTiponiveleducativo() {
        return tiponiveleducativo;
    }

    public void setTiponiveleducativo(String tiponiveleducativo) {
        this.tiponiveleducativo = tiponiveleducativo;
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
        hash += (tiponiveleducativo != null ? tiponiveleducativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEducativo)) {
            return false;
        }
        NivelEducativo other = (NivelEducativo) object;
        if ((this.tiponiveleducativo == null && other.tiponiveleducativo != null) || (this.tiponiveleducativo != null && !this.tiponiveleducativo.equals(other.tiponiveleducativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RobotAgentBDI.Believes.PerfilPwA.NivelEducativo[ tiponiveleducativo=" + tiponiveleducativo + " ]";
    }
    
}
