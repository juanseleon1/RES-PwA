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
@Table(name = "CAUSADEMENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Causademencia.findAll", query = "SELECT c FROM Causademencia c"),
    @NamedQuery(name = "Causademencia.findByEnfermedad", query = "SELECT c FROM Causademencia c WHERE c.enfermedad = :enfermedad")})
public class Causademencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ENFERMEDAD")
    private String enfermedad;
    @OneToMany(mappedBy = "causademenciaEnfermedad")
    private List<PerfilMedico> perfilMedicoList;

    public Causademencia() {
    }

    public Causademencia(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
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
        hash += (enfermedad != null ? enfermedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Causademencia)) {
            return false;
        }
        Causademencia other = (Causademencia) object;
        if ((this.enfermedad == null && other.enfermedad != null) || (this.enfermedad != null && !this.enfermedad.equals(other.enfermedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RobotAgentBDI.Believes.PerfilPwA.Causademencia[ enfermedad=" + enfermedad + " ]";
    }
    
}
