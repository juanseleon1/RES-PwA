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
<<<<<<< HEAD
 * @author maria.f.garces.cala
=======
 * @author juans
>>>>>>> master
 */
@Entity
@Table(name = "PREFERENCIAXCANCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferenciaxcancion.findAll", query = "SELECT p FROM Preferenciaxcancion p"),
    @NamedQuery(name = "Preferenciaxcancion.findByPerfilPreferenciaPerfilpwaCedula", query = "SELECT p FROM Preferenciaxcancion p WHERE p.preferenciaxcancionPK.perfilPreferenciaPerfilpwaCedula = :perfilPreferenciaPerfilpwaCedula"),
    @NamedQuery(name = "Preferenciaxcancion.findByCancionNombre", query = "SELECT p FROM Preferenciaxcancion p WHERE p.preferenciaxcancionPK.cancionNombre = :cancionNombre"),
    @NamedQuery(name = "Preferenciaxcancion.findByGusto", query = "SELECT p FROM Preferenciaxcancion p WHERE p.gusto = :gusto"),
    @NamedQuery(name = "Preferenciaxcancion.findByReminiscencia", query = "SELECT p FROM Preferenciaxcancion p WHERE p.reminiscencia = :reminiscencia")})
public class Preferenciaxcancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreferenciaxcancionPK preferenciaxcancionPK;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Basic(optional = false)
    @Column(name = "REMINISCENCIA")
    private BigDecimal reminiscencia;
    @JoinColumn(name = "CANCION_NOMBRE", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cancion cancion;
    @JoinColumn(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilPreferencia perfilPreferencia;

    public Preferenciaxcancion() {
    }

    public Preferenciaxcancion(PreferenciaxcancionPK preferenciaxcancionPK) {
        this.preferenciaxcancionPK = preferenciaxcancionPK;
    }

    public Preferenciaxcancion(PreferenciaxcancionPK preferenciaxcancionPK, double gusto, BigDecimal reminiscencia) {
        this.preferenciaxcancionPK = preferenciaxcancionPK;
        this.gusto = gusto;
        this.reminiscencia = reminiscencia;

    }

    public Preferenciaxcancion(String perfilPreferenciaPerfilpwaCedula, String cancionNombre) {
        this.preferenciaxcancionPK = new PreferenciaxcancionPK(perfilPreferenciaPerfilpwaCedula, cancionNombre);
    }

    public PreferenciaxcancionPK getPreferenciaxcancionPK() {
        return preferenciaxcancionPK;
    }

    public void setPreferenciaxcancionPK(PreferenciaxcancionPK preferenciaxcancionPK) {
        this.preferenciaxcancionPK = preferenciaxcancionPK;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public BigDecimal getReminiscencia() {
        return reminiscencia;
    }

    public void setReminiscencia(BigDecimal reminiscencia) {
        this.reminiscencia = reminiscencia;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
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
        hash += (preferenciaxcancionPK != null ? preferenciaxcancionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferenciaxcancion)) {
            return false;
        }
        Preferenciaxcancion other = (Preferenciaxcancion) object;
        if ((this.preferenciaxcancionPK == null && other.preferenciaxcancionPK != null) || (this.preferenciaxcancionPK != null && !this.preferenciaxcancionPK.equals(other.preferenciaxcancionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Preferenciaxcancion[ preferenciaxcancionPK=" + preferenciaxcancionPK + " ]";
    }
    
}
