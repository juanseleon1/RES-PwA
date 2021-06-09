/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
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
<<<<<<< HEAD
 * @author maria.f.garces.cala
=======
 * @author juans
>>>>>>> master
 */
@Entity
@Table(name = "PREFERENCIAXBAILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferenciaxbaile.findAll", query = "SELECT p FROM Preferenciaxbaile p"),
    @NamedQuery(name = "Preferenciaxbaile.findByBaileId", query = "SELECT p FROM Preferenciaxbaile p WHERE p.preferenciaxbailePK.baileId = :baileId"),
    @NamedQuery(name = "Preferenciaxbaile.findByPerfilPreferenciaPerfilpwaCedula", query = "SELECT p FROM Preferenciaxbaile p WHERE p.preferenciaxbailePK.perfilPreferenciaPerfilpwaCedula = :perfilPreferenciaPerfilpwaCedula"),
    @NamedQuery(name = "Preferenciaxbaile.findByGusto", query = "SELECT p FROM Preferenciaxbaile p WHERE p.gusto = :gusto")})
public class Preferenciaxbaile implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreferenciaxbailePK preferenciaxbailePK;
    @Column(name = "GUSTO")
    private double gusto;
    @JoinColumn(name = "BAILE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Baile baile;
    @JoinColumn(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilPreferencia perfilPreferencia;

    public Preferenciaxbaile() {
    }

    public Preferenciaxbaile(PreferenciaxbailePK preferenciaxbailePK) {
        this.preferenciaxbailePK = preferenciaxbailePK;
    }

    public Preferenciaxbaile(BigDecimal baileId, String perfilPreferenciaPerfilpwaCedula) {
        this.preferenciaxbailePK = new PreferenciaxbailePK(baileId, perfilPreferenciaPerfilpwaCedula);
    }

    public PreferenciaxbailePK getPreferenciaxbailePK() {
        return preferenciaxbailePK;
    }

    public void setPreferenciaxbailePK(PreferenciaxbailePK preferenciaxbailePK) {
        this.preferenciaxbailePK = preferenciaxbailePK;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {

        this.gusto = gusto;
    }

    public Baile getBaile() {
        return baile;
    }

    public void setBaile(Baile baile) {
        this.baile = baile;
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
        hash += (preferenciaxbailePK != null ? preferenciaxbailePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferenciaxbaile)) {
            return false;
        }
        Preferenciaxbaile other = (Preferenciaxbaile) object;
        if ((this.preferenciaxbailePK == null && other.preferenciaxbailePK != null) || (this.preferenciaxbailePK != null && !this.preferenciaxbailePK.equals(other.preferenciaxbailePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Preferenciaxbaile[ preferenciaxbailePK=" + preferenciaxbailePK + " ]";
    }
    
}
