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
import javax.persistence.Embeddable;

/**
 *
 * @author maria.f.garces.cala
 */
@Embeddable
public class FrasesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ORDEN")
    private BigDecimal orden;
    @Basic(optional = false)
    @Column(name = "CUENTO_NOMBRE")
    private String cuentoNombre;

    public FrasesPK() {
    }

    public FrasesPK(BigDecimal orden, String cuentoNombre) {
        this.orden = orden;
        this.cuentoNombre = cuentoNombre;
    }

    public BigDecimal getOrden() {
        return orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

    public String getCuentoNombre() {
        return cuentoNombre;
    }

    public void setCuentoNombre(String cuentoNombre) {
        this.cuentoNombre = cuentoNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orden != null ? orden.hashCode() : 0);
        hash += (cuentoNombre != null ? cuentoNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrasesPK)) {
            return false;
        }
        FrasesPK other = (FrasesPK) object;
        if ((this.orden == null && other.orden != null) || (this.orden != null && !this.orden.equals(other.orden))) {
            return false;
        }
        if ((this.cuentoNombre == null && other.cuentoNombre != null) || (this.cuentoNombre != null && !this.cuentoNombre.equals(other.cuentoNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.FrasesPK[ orden=" + orden + ", cuentoNombre=" + cuentoNombre + " ]";
    }
    
}
