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
import javax.persistence.Embeddable;

/**
 *
 * @author juans
 */
@Embeddable
public class FrasesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private BigInteger orden;
    @Basic(optional = false)
    @Column(name = "NOMBRECUENTO")
    private String nombrecuento;

    public FrasesPK() {
    }

    public FrasesPK(BigInteger orden, String nombrecuento) {
        this.orden = orden;
        this.nombrecuento = nombrecuento;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public String getNombrecuento() {
        return nombrecuento;
    }

    public void setNombrecuento(String nombrecuento) {
        this.nombrecuento = nombrecuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orden != null ? orden.hashCode() : 0);
        hash += (nombrecuento != null ? nombrecuento.hashCode() : 0);
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
        if ((this.nombrecuento == null && other.nombrecuento != null) || (this.nombrecuento != null && !this.nombrecuento.equals(other.nombrecuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.FrasesPK[ orden=" + orden + ", nombrecuento=" + nombrecuento + " ]";
    }
    
}
