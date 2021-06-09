/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "CDR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cdr.findAll", query = "SELECT c FROM Cdr c"),
    @NamedQuery(name = "Cdr.findByMemoria", query = "SELECT c FROM Cdr c WHERE c.memoria = :memoria"),
    @NamedQuery(name = "Cdr.findByOrientacion", query = "SELECT c FROM Cdr c WHERE c.orientacion = :orientacion"),
    @NamedQuery(name = "Cdr.findByJuicio", query = "SELECT c FROM Cdr c WHERE c.juicio = :juicio"),
    @NamedQuery(name = "Cdr.findByVidaSocial", query = "SELECT c FROM Cdr c WHERE c.vidaSocial = :vidaSocial"),
    @NamedQuery(name = "Cdr.findByHogar", query = "SELECT c FROM Cdr c WHERE c.hogar = :hogar"),
    @NamedQuery(name = "Cdr.findByCuidadopersonal", query = "SELECT c FROM Cdr c WHERE c.cuidadopersonal = :cuidadopersonal"),
    @NamedQuery(name = "Cdr.findByPerfilMedicoCedula", query = "SELECT c FROM Cdr c WHERE c.perfilMedicoCedula = :perfilMedicoCedula")})
public class Cdr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "MEMORIA")
    private BigDecimal memoria;
    @Basic(optional = false)
    @Column(name = "ORIENTACION")
    private BigDecimal orientacion;
    @Basic(optional = false)
    @Column(name = "JUICIO")
    private BigDecimal juicio;
    @Basic(optional = false)
    @Column(name = "VIDA_SOCIAL")
    private BigDecimal vidaSocial;
    @Basic(optional = false)
    @Column(name = "HOGAR")
    private BigDecimal hogar;
    @Basic(optional = false)
    @Column(name = "CUIDADOPERSONAL")
    private BigDecimal cuidadopersonal;
    @Id
    @Basic(optional = false)
    @Column(name = "PERFIL_MEDICO_CEDULA")
    private String perfilMedicoCedula;
    @JoinColumn(name = "PERFIL_MEDICO_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilMedico perfilMedico;

    public Cdr() {
    }

    public Cdr(String perfilMedicoCedula) {
        this.perfilMedicoCedula = perfilMedicoCedula;
    }

    public Cdr(String perfilMedicoCedula, BigDecimal memoria, BigDecimal orientacion, BigDecimal juicio, BigDecimal vidaSocial, BigDecimal hogar, BigDecimal cuidadopersonal) {
        this.perfilMedicoCedula = perfilMedicoCedula;
        this.memoria = memoria;
        this.orientacion = orientacion;
        this.juicio = juicio;
        this.vidaSocial = vidaSocial;
        this.hogar = hogar;
        this.cuidadopersonal = cuidadopersonal;
    }

    public BigDecimal getMemoria() {
        return memoria;
    }

    public void setMemoria(BigDecimal memoria) {
        this.memoria = memoria;
    }

    public BigDecimal getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(BigDecimal orientacion) {
        this.orientacion = orientacion;
    }

    public BigDecimal getJuicio() {
        return juicio;
    }

    public void setJuicio(BigDecimal juicio) {
        this.juicio = juicio;
    }

    public BigDecimal getVidaSocial() {
        return vidaSocial;
    }

    public void setVidaSocial(BigDecimal vidaSocial) {
        this.vidaSocial = vidaSocial;
    }

    public BigDecimal getHogar() {
        return hogar;
    }

    public void setHogar(BigDecimal hogar) {
        this.hogar = hogar;
    }

    public BigDecimal getCuidadopersonal() {
        return cuidadopersonal;
    }

    public void setCuidadopersonal(BigDecimal cuidadopersonal) {
        this.cuidadopersonal = cuidadopersonal;
    }

    public String getPerfilMedicoCedula() {
        return perfilMedicoCedula;
    }

    public void setPerfilMedicoCedula(String perfilMedicoCedula) {
        this.perfilMedicoCedula = perfilMedicoCedula;
    }

    public PerfilMedico getPerfilMedico() {
        return perfilMedico;
    }

    public void setPerfilMedico(PerfilMedico perfilMedico) {
        this.perfilMedico = perfilMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilMedicoCedula != null ? perfilMedicoCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cdr)) {
            return false;
        }
        Cdr other = (Cdr) object;
        if ((this.perfilMedicoCedula == null && other.perfilMedicoCedula != null) || (this.perfilMedicoCedula != null && !this.perfilMedicoCedula.equals(other.perfilMedicoCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Cdr[ perfilMedicoCedula=" + perfilMedicoCedula + " ]";
    }
    
}
