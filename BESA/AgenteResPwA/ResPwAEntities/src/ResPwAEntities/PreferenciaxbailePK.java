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
<<<<<<< HEAD
 * @author maria.f.garces.cala
=======
 * @author juans
>>>>>>> master
 */
@Embeddable
public class PreferenciaxbailePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "BAILE_ID")
    private BigDecimal baileId;
    @Basic(optional = false)
    @Column(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA")
    private String perfilPreferenciaPerfilpwaCedula;

    public PreferenciaxbailePK() {
    }

    public PreferenciaxbailePK(BigDecimal baileId, String perfilPreferenciaPerfilpwaCedula) {
        this.baileId = baileId;
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
    }

    public BigDecimal getBaileId() {
        return baileId;
    }

    public void setBaileId(BigDecimal baileId) {
        this.baileId = baileId;
    }

    public String getPerfilPreferenciaPerfilpwaCedula() {
        return perfilPreferenciaPerfilpwaCedula;
    }

    public void setPerfilPreferenciaPerfilpwaCedula(String perfilPreferenciaPerfilpwaCedula) {
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baileId != null ? baileId.hashCode() : 0);
        hash += (perfilPreferenciaPerfilpwaCedula != null ? perfilPreferenciaPerfilpwaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenciaxbailePK)) {
            return false;
        }
        PreferenciaxbailePK other = (PreferenciaxbailePK) object;
        if ((this.baileId == null && other.baileId != null) || (this.baileId != null && !this.baileId.equals(other.baileId))) {
            return false;
        }
        if ((this.perfilPreferenciaPerfilpwaCedula == null && other.perfilPreferenciaPerfilpwaCedula != null) || (this.perfilPreferenciaPerfilpwaCedula != null && !this.perfilPreferenciaPerfilpwaCedula.equals(other.perfilPreferenciaPerfilpwaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.PreferenciaxbailePK[ baileId=" + baileId + ", perfilPreferenciaPerfilpwaCedula=" + perfilPreferenciaPerfilpwaCedula + " ]";
    }
    
}
