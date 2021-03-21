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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "ACTXPREFERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actxpreferencia.findAll", query = "SELECT a FROM Actxpreferencia a")
    , @NamedQuery(name = "Actxpreferencia.findByActividadpwaId", query = "SELECT a FROM Actxpreferencia a WHERE a.actxpreferenciaPK.actividadpwaId = :actividadpwaId")
    , @NamedQuery(name = "Actxpreferencia.findByPerfilPreferenciaCedula", query = "SELECT a FROM Actxpreferencia a WHERE a.actxpreferenciaPK.perfilPreferenciaCedula = :perfilPreferenciaCedula")
    , @NamedQuery(name = "Actxpreferencia.findByActiva", query = "SELECT a FROM Actxpreferencia a WHERE a.activa = :activa")
    , @NamedQuery(name = "Actxpreferencia.findByGusto", query = "SELECT a FROM Actxpreferencia a WHERE a.gusto = :gusto")
    , @NamedQuery(name = "Actxpreferencia.findByEnriq", query = "SELECT a FROM Actxpreferencia a WHERE a.enriq = :enriq")})
public class Actxpreferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActxpreferenciaPK actxpreferenciaPK;
    @Basic(optional = false)
    @Column(name = "ACTIVA")
    private BigInteger activa;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Basic(optional = false)
    @Column(name = "ENRIQ")
    private BigInteger enriq;
    @JoinColumn(name = "ACTIVIDADPWA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividadpwa actividadpwa;
    @JoinColumn(name = "DIFICULTAD_DIFICULTAD", referencedColumnName = "DIFICULTAD")
    @ManyToOne
    private Dificultad dificultadDificultad;
    @JoinColumn(name = "PERFIL_PREFERENCIA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PerfilPreferencia perfilPreferencia;

    public Actxpreferencia() {
    }

    public Actxpreferencia(ActxpreferenciaPK actxpreferenciaPK) {
        this.actxpreferenciaPK = actxpreferenciaPK;
    }

    public Actxpreferencia(ActxpreferenciaPK actxpreferenciaPK, BigInteger activa, double gusto, BigInteger enriq) {
        this.actxpreferenciaPK = actxpreferenciaPK;
        this.activa = activa;
        this.gusto = gusto;
        this.enriq = enriq;
    }

    public Actxpreferencia(BigInteger actividadpwaId, String perfilPreferenciaCedula) {
        this.actxpreferenciaPK = new ActxpreferenciaPK(actividadpwaId, perfilPreferenciaCedula);
    }

    public ActxpreferenciaPK getActxpreferenciaPK() {
        return actxpreferenciaPK;
    }

    public void setActxpreferenciaPK(ActxpreferenciaPK actxpreferenciaPK) {
        this.actxpreferenciaPK = actxpreferenciaPK;
    }

    public BigInteger getActiva() {
        return activa;
    }

    public void setActiva(BigInteger activa) {
        this.activa = activa;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public BigInteger getEnriq() {
        return enriq;
    }

    public void setEnriq(BigInteger enriq) {
        this.enriq = enriq;
    }

    public Actividadpwa getActividadpwa() {
        return actividadpwa;
    }

    public void setActividadpwa(Actividadpwa actividadpwa) {
        this.actividadpwa = actividadpwa;
    }

    public Dificultad getDificultadDificultad() {
        return dificultadDificultad;
    }

    public void setDificultadDificultad(Dificultad dificultadDificultad) {
        this.dificultadDificultad = dificultadDificultad;
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
