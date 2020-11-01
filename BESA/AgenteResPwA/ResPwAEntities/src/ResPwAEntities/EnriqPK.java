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
public class EnriqPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FRASES_ORDEN")
    private BigInteger frasesOrden;
    @Basic(optional = false)
    @Column(name = "FRASES_CUENTO_NOMBRE")
    private String frasesCuentoNombre;
    @Basic(optional = false)
    @Column(name = "PARAMS")
    private String params;

    public EnriqPK() {
    }

    public EnriqPK(BigInteger frasesOrden, String frasesCuentoNombre, String params) {
        this.frasesOrden = frasesOrden;
        this.frasesCuentoNombre = frasesCuentoNombre;
        this.params = params;
    }

    public BigInteger getFrasesOrden() {
        return frasesOrden;
    }

    public void setFrasesOrden(BigInteger frasesOrden) {
        this.frasesOrden = frasesOrden;
    }

    public String getFrasesCuentoNombre() {
        return frasesCuentoNombre;
    }

    public void setFrasesCuentoNombre(String frasesCuentoNombre) {
        this.frasesCuentoNombre = frasesCuentoNombre;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frasesOrden != null ? frasesOrden.hashCode() : 0);
        hash += (frasesCuentoNombre != null ? frasesCuentoNombre.hashCode() : 0);
        hash += (params != null ? params.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnriqPK)) {
            return false;
        }
        EnriqPK other = (EnriqPK) object;
        if ((this.frasesOrden == null && other.frasesOrden != null) || (this.frasesOrden != null && !this.frasesOrden.equals(other.frasesOrden))) {
            return false;
        }
        if ((this.frasesCuentoNombre == null && other.frasesCuentoNombre != null) || (this.frasesCuentoNombre != null && !this.frasesCuentoNombre.equals(other.frasesCuentoNombre))) {
            return false;
        }
        if ((this.params == null && other.params != null) || (this.params != null && !this.params.equals(other.params))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.EnriqPK[ frasesOrden=" + frasesOrden + ", frasesCuentoNombre=" + frasesCuentoNombre + ", params=" + params + " ]";
    }
    
}
