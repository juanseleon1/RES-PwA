/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Cdr.findByPerfilMedicoPerfilpwaCedula", query = "SELECT c FROM Cdr c WHERE c.perfilMedicoPerfilpwaCedula = :perfilMedicoPerfilpwaCedula")})
public class Cdr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "MEMORIA")
    private BigInteger memoria;
    @Basic(optional = false)
    @Column(name = "ORIENTACION")
    private BigInteger orientacion;
    @Basic(optional = false)
    @Column(name = "JUICIO")
    private BigInteger juicio;
    @Basic(optional = false)
    @Column(name = "VIDA_SOCIAL")
    private BigInteger vidaSocial;
    @Basic(optional = false)
    @Column(name = "HOGAR")
    private BigInteger hogar;
    @Basic(optional = false)
    @Column(name = "CUIDADOPERSONAL")
    private BigInteger cuidadopersonal;
    @Id
    @Basic(optional = false)
    @Column(name = "PERFIL_MEDICO_PERFILPWA_CEDULA")
    private String perfilMedicoPerfilpwaCedula;
    @JoinColumn(name = "PERFIL_MEDICO_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilMedico perfilMedico;

    public Cdr() {
    }

    public Cdr(String perfilMedicoPerfilpwaCedula) {
        this.perfilMedicoPerfilpwaCedula = perfilMedicoPerfilpwaCedula;
    }

    public Cdr(String perfilMedicoPerfilpwaCedula, BigInteger memoria, BigInteger orientacion, BigInteger juicio, BigInteger vidaSocial, BigInteger hogar, BigInteger cuidadopersonal) {
        this.perfilMedicoPerfilpwaCedula = perfilMedicoPerfilpwaCedula;
        this.memoria = memoria;
        this.orientacion = orientacion;
        this.juicio = juicio;
        this.vidaSocial = vidaSocial;
        this.hogar = hogar;
        this.cuidadopersonal = cuidadopersonal;
    }

    public BigInteger getMemoria() {
        return memoria;
    }

    public void setMemoria(BigInteger memoria) {
        this.memoria = memoria;
    }

    public BigInteger getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(BigInteger orientacion) {
        this.orientacion = orientacion;
    }

    public BigInteger getJuicio() {
        return juicio;
    }

    public void setJuicio(BigInteger juicio) {
        this.juicio = juicio;
    }

    public BigInteger getVidaSocial() {
        return vidaSocial;
    }

    public void setVidaSocial(BigInteger vidaSocial) {
        this.vidaSocial = vidaSocial;
    }

    public BigInteger getHogar() {
        return hogar;
    }

    public void setHogar(BigInteger hogar) {
        this.hogar = hogar;
    }

    public BigInteger getCuidadopersonal() {
        return cuidadopersonal;
    }

    public void setCuidadopersonal(BigInteger cuidadopersonal) {
        this.cuidadopersonal = cuidadopersonal;
    }

    public String getPerfilMedicoPerfilpwaCedula() {
        return perfilMedicoPerfilpwaCedula;
    }

    public void setPerfilMedicoPerfilpwaCedula(String perfilMedicoPerfilpwaCedula) {
        this.perfilMedicoPerfilpwaCedula = perfilMedicoPerfilpwaCedula;
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
        hash += (perfilMedicoPerfilpwaCedula != null ? perfilMedicoPerfilpwaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cdr)) {
            return false;
        }
        Cdr other = (Cdr) object;
        if ((this.perfilMedicoPerfilpwaCedula == null && other.perfilMedicoPerfilpwaCedula != null) || (this.perfilMedicoPerfilpwaCedula != null && !this.perfilMedicoPerfilpwaCedula.equals(other.perfilMedicoPerfilpwaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Cdr[ perfilMedicoPerfilpwaCedula=" + perfilMedicoPerfilpwaCedula + " ]";
    }
    
}
