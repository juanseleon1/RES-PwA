/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "PERFIL_MEDICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilMedico.findAll", query = "SELECT p FROM PerfilMedico p"),
    @NamedQuery(name = "PerfilMedico.findByPerfilpwaCedula", query = "SELECT p FROM PerfilMedico p WHERE p.perfilpwaCedula = :perfilpwaCedula"),
    @NamedQuery(name = "PerfilMedico.findByTomamedicamentos", query = "SELECT p FROM PerfilMedico p WHERE p.tomamedicamentos = :tomamedicamentos"),
    @NamedQuery(name = "PerfilMedico.findByDiscapacidadauditiva", query = "SELECT p FROM PerfilMedico p WHERE p.discapacidadauditiva = :discapacidadauditiva"),
    @NamedQuery(name = "PerfilMedico.findByDiscapacidadvisual", query = "SELECT p FROM PerfilMedico p WHERE p.discapacidadvisual = :discapacidadvisual"),
    @NamedQuery(name = "PerfilMedico.findByDiscapacidadmotora", query = "SELECT p FROM PerfilMedico p WHERE p.discapacidadmotora = :discapacidadmotora"),
    @NamedQuery(name = "PerfilMedico.findByEstadioenfermedad", query = "SELECT p FROM PerfilMedico p WHERE p.estadioenfermedad = :estadioenfermedad"),
    @NamedQuery(name = "PerfilMedico.findByPeriodovigilia", query = "SELECT p FROM PerfilMedico p WHERE p.periodovigilia = :periodovigilia")})
public class PerfilMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERFILPWA_CEDULA")
    private String perfilpwaCedula;
    @Basic(optional = false)
    @Column(name = "TOMAMEDICAMENTOS")
    private BigInteger tomamedicamentos;
    @Basic(optional = false)
    @Column(name = "DISCAPACIDADAUDITIVA")
    private BigInteger discapacidadauditiva;
    @Basic(optional = false)
    @Column(name = "DISCAPACIDADVISUAL")
    private BigInteger discapacidadvisual;
    @Basic(optional = false)
    @Column(name = "DISCAPACIDADMOTORA")
    private BigInteger discapacidadmotora;
    @Basic(optional = false)
    @Column(name = "ESTADIOENFERMEDAD")
    private BigInteger estadioenfermedad;
    @Basic(optional = false)
    @Column(name = "PERIODOVIGILIA")
    private BigInteger periodovigilia;
    @OneToMany(mappedBy = "perfilMedicoPerfilpwaCedula")
    private List<Actividadrutinaria> actividadrutinariaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perfilMedico")
    private Cdr cdr;
    @JoinColumn(name = "CAUSADEMENCIA_ENFERMEDAD", referencedColumnName = "ENFERMEDAD")
    @ManyToOne
    private Causademencia causademenciaEnfermedad;
    @JoinColumn(name = "PERFILPWA_CEDULA", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Perfilpwa perfilpwa;

    public PerfilMedico() {
    }

    public PerfilMedico(String perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    public PerfilMedico(String perfilpwaCedula, BigInteger tomamedicamentos, BigInteger discapacidadauditiva, BigInteger discapacidadvisual, BigInteger discapacidadmotora, BigInteger estadioenfermedad, BigInteger periodovigilia) {
        this.perfilpwaCedula = perfilpwaCedula;
        this.tomamedicamentos = tomamedicamentos;
        this.discapacidadauditiva = discapacidadauditiva;
        this.discapacidadvisual = discapacidadvisual;
        this.discapacidadmotora = discapacidadmotora;
        this.estadioenfermedad = estadioenfermedad;
        this.periodovigilia = periodovigilia;
    }

    public String getPerfilpwaCedula() {
        return perfilpwaCedula;
    }

    public void setPerfilpwaCedula(String perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    public BigInteger getTomamedicamentos() {
        return tomamedicamentos;
    }

    public void setTomamedicamentos(BigInteger tomamedicamentos) {
        this.tomamedicamentos = tomamedicamentos;
    }

    public BigInteger getDiscapacidadauditiva() {
        return discapacidadauditiva;
    }

    public void setDiscapacidadauditiva(BigInteger discapacidadauditiva) {
        this.discapacidadauditiva = discapacidadauditiva;
    }

    public BigInteger getDiscapacidadvisual() {
        return discapacidadvisual;
    }

    public void setDiscapacidadvisual(BigInteger discapacidadvisual) {
        this.discapacidadvisual = discapacidadvisual;
    }

    public BigInteger getDiscapacidadmotora() {
        return discapacidadmotora;
    }

    public void setDiscapacidadmotora(BigInteger discapacidadmotora) {
        this.discapacidadmotora = discapacidadmotora;
    }

    public BigInteger getEstadioenfermedad() {
        return estadioenfermedad;
    }

    public void setEstadioenfermedad(BigInteger estadioenfermedad) {
        this.estadioenfermedad = estadioenfermedad;
    }

    public BigInteger getPeriodovigilia() {
        return periodovigilia;
    }

    public void setPeriodovigilia(BigInteger periodovigilia) {
        this.periodovigilia = periodovigilia;
    }

    @XmlTransient
    public List<Actividadrutinaria> getActividadrutinariaList() {
        return actividadrutinariaList;
    }

    public void setActividadrutinariaList(List<Actividadrutinaria> actividadrutinariaList) {
        this.actividadrutinariaList = actividadrutinariaList;
    }

    public Cdr getCdr() {
        return cdr;
    }

    public void setCdr(Cdr cdr) {
        this.cdr = cdr;
    }

    public Causademencia getCausademenciaEnfermedad() {
        return causademenciaEnfermedad;
    }

    public void setCausademenciaEnfermedad(Causademencia causademenciaEnfermedad) {
        this.causademenciaEnfermedad = causademenciaEnfermedad;
    }

    public Perfilpwa getPerfilpwa() {
        return perfilpwa;
    }

    public void setPerfilpwa(Perfilpwa perfilpwa) {
        this.perfilpwa = perfilpwa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilpwaCedula != null ? perfilpwaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilMedico)) {
            return false;
        }
        PerfilMedico other = (PerfilMedico) object;
        if ((this.perfilpwaCedula == null && other.perfilpwaCedula != null) || (this.perfilpwaCedula != null && !this.perfilpwaCedula.equals(other.perfilpwaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RobotAgentBDI.Believes.PerfilPwA.PerfilMedico[ perfilpwaCedula=" + perfilpwaCedula + " ]";
    }
    
}
