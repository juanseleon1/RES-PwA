/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
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
@Table(name = "PREFERENCIAXCUENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferenciaxcuento.findAll", query = "SELECT p FROM Preferenciaxcuento p"),
    @NamedQuery(name = "Preferenciaxcuento.findByPerfilPreferenciaPerfilpwaCedula", query = "SELECT p FROM Preferenciaxcuento p WHERE p.preferenciaxcuentoPK.perfilPreferenciaPerfilpwaCedula = :perfilPreferenciaPerfilpwaCedula"),
    @NamedQuery(name = "Preferenciaxcuento.findByCuentoNombre", query = "SELECT p FROM Preferenciaxcuento p WHERE p.preferenciaxcuentoPK.cuentoNombre = :cuentoNombre"),
    @NamedQuery(name = "Preferenciaxcuento.findByGusto", query = "SELECT p FROM Preferenciaxcuento p WHERE p.gusto = :gusto")})
public class Preferenciaxcuento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreferenciaxcuentoPK preferenciaxcuentoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GUSTO")
    private Double gusto;
    @JoinColumn(name = "CUENTO_NOMBRE", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cuento cuento;
    @JoinColumn(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilPreferencia perfilPreferencia;

    public Preferenciaxcuento() {
    }

    public Preferenciaxcuento(PreferenciaxcuentoPK preferenciaxcuentoPK) {
        this.preferenciaxcuentoPK = preferenciaxcuentoPK;
    }

    public Preferenciaxcuento(String perfilPreferenciaPerfilpwaCedula, String cuentoNombre) {
        this.preferenciaxcuentoPK = new PreferenciaxcuentoPK(perfilPreferenciaPerfilpwaCedula, cuentoNombre);
    }

    public PreferenciaxcuentoPK getPreferenciaxcuentoPK() {
        return preferenciaxcuentoPK;
    }

    public void setPreferenciaxcuentoPK(PreferenciaxcuentoPK preferenciaxcuentoPK) {
        this.preferenciaxcuentoPK = preferenciaxcuentoPK;
    }

    public Double getGusto() {
        return gusto;
    }

    public void setGusto(Double gusto) {
        this.gusto = gusto;
    }

    public Cuento getCuento() {
        return cuento;
    }

    public void setCuento(Cuento cuento) {
        this.cuento = cuento;
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
        hash += (preferenciaxcuentoPK != null ? preferenciaxcuentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferenciaxcuento)) {
            return false;
        }
        Preferenciaxcuento other = (Preferenciaxcuento) object;
        if ((this.preferenciaxcuentoPK == null && other.preferenciaxcuentoPK != null) || (this.preferenciaxcuentoPK != null && !this.preferenciaxcuentoPK.equals(other.preferenciaxcuentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Preferenciaxcuento[ preferenciaxcuentoPK=" + preferenciaxcuentoPK + " ]";
    }
    
}
