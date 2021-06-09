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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "ACTXPREFERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actxpreferencia.findAll", query = "SELECT a FROM Actxpreferencia a"),
    @NamedQuery(name = "Actxpreferencia.findByActividadpwaId", query = "SELECT a FROM Actxpreferencia a WHERE a.actxpreferenciaPK.actividadpwaId = :actividadpwaId"),
    @NamedQuery(name = "Actxpreferencia.findByPerfilPreferenciaCedula", query = "SELECT a FROM Actxpreferencia a WHERE a.actxpreferenciaPK.perfilPreferenciaCedula = :perfilPreferenciaCedula"),
    @NamedQuery(name = "Actxpreferencia.findByActiva", query = "SELECT a FROM Actxpreferencia a WHERE a.activa = :activa"),
    @NamedQuery(name = "Actxpreferencia.findByGusto", query = "SELECT a FROM Actxpreferencia a WHERE a.gusto = :gusto"),
    @NamedQuery(name = "Actxpreferencia.findByEnriq", query = "SELECT a FROM Actxpreferencia a WHERE a.enriq = :enriq")})
public class Actxpreferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActxpreferenciaPK actxpreferenciaPK;
    @Basic(optional = false)
    @Column(name = "ACTIVA")
    private BigDecimal activa;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Basic(optional = false)
    @Column(name = "ENRIQ")
    private BigDecimal enriq;
    @JoinColumn(name = "ACTIVIDADPWA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Actividadpwa actividadpwa;
    @JoinColumn(name = "PERFIL_PREFERENCIA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilPreferencia perfilPreferencia;

    public Actxpreferencia() {
    }

    public Actxpreferencia(ActxpreferenciaPK actxpreferenciaPK) {
        this.actxpreferenciaPK = actxpreferenciaPK;
    }

    public Actxpreferencia(ActxpreferenciaPK actxpreferenciaPK, BigDecimal activa, double gusto, BigDecimal enriq) {
        this.actxpreferenciaPK = actxpreferenciaPK;
        this.activa = activa;
        this.gusto = gusto;
        this.enriq = enriq;
    }

    public Actxpreferencia(BigDecimal actividadpwaId, String perfilPreferenciaCedula) {
        this.actxpreferenciaPK = new ActxpreferenciaPK(actividadpwaId, perfilPreferenciaCedula);
    }

    public ActxpreferenciaPK getActxpreferenciaPK() {
        return actxpreferenciaPK;
    }

    public void setActxpreferenciaPK(ActxpreferenciaPK actxpreferenciaPK) {
        this.actxpreferenciaPK = actxpreferenciaPK;
    }

    public BigDecimal getActiva() {
        return activa;
    }

    public void setActiva(BigDecimal activa) {
        this.activa = activa;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public BigDecimal getEnriq() {
        return enriq;
    }

    public void setEnriq(BigDecimal enriq) {
        this.enriq = enriq;
    }

    public Actividadpwa getActividadpwa() {
        return actividadpwa;
    }

    public void setActividadpwa(Actividadpwa actividadpwa) {
        this.actividadpwa = actividadpwa;
    }

    public PerfilPreferencia getPerfilPreferencia() {
        return perfilPreferencia;
    }

    public void setPerfilPreferencia(PerfilPreferencia perfilPreferencia) {
        this.perfilPreferencia = perfilPreferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actxpreferenciaPK != null ? actxpreferenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actxpreferencia)) {
            return false;
        }
        Actxpreferencia other = (Actxpreferencia) object;
        if ((this.actxpreferenciaPK == null && other.actxpreferenciaPK != null) || (this.actxpreferenciaPK != null && !this.actxpreferenciaPK.equals(other.actxpreferenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Actxpreferencia[ actxpreferenciaPK=" + actxpreferenciaPK + " ]";
    }
    
}
