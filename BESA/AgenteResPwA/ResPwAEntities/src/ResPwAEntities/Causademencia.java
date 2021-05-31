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
@Table(name = "CAUSADEMENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Causademencia.findAll", query = "SELECT c FROM Causademencia c"),
    @NamedQuery(name = "Causademencia.findByCondicion", query = "SELECT c FROM Causademencia c WHERE c.condicion = :condicion")})
public class Causademencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONDICION")
    private String condicion;
    @OneToMany(mappedBy = "causademenciaCondicion", fetch = FetchType.EAGER)
    private List<PerfilMedico> perfilMedicoList;

    public Causademencia() {
    }

    public Causademencia(String condicion) {
        this.condicion = condicion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @XmlTransient
    public List<PerfilMedico> getPerfilMedicoList() {
        return perfilMedicoList;
    }

    public void setPerfilMedicoList(List<PerfilMedico> perfilMedicoList) {
        this.perfilMedicoList = perfilMedicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (condicion != null ? condicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Causademencia)) {
            return false;
        }
        Causademencia other = (Causademencia) object;
        if ((this.condicion == null && other.condicion != null) || (this.condicion != null && !this.condicion.equals(other.condicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Causademencia[ condicion=" + condicion + " ]";
    }
    
}
