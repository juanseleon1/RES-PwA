/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
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
public class PreferenciaxcuentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA")
    private String perfilPreferenciaPerfilpwaCedula;
    @Basic(optional = false)
    @Column(name = "CUENTO_NOMBRE")
    private String cuentoNombre;

    public PreferenciaxcuentoPK() {
    }

    public PreferenciaxcuentoPK(String perfilPreferenciaPerfilpwaCedula, String cuentoNombre) {
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
        this.cuentoNombre = cuentoNombre;
    }

    public String getPerfilPreferenciaPerfilpwaCedula() {
        return perfilPreferenciaPerfilpwaCedula;
    }

    public void setPerfilPreferenciaPerfilpwaCedula(String perfilPreferenciaPerfilpwaCedula) {
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
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
        hash += (perfilPreferenciaPerfilpwaCedula != null ? perfilPreferenciaPerfilpwaCedula.hashCode() : 0);
        hash += (cuentoNombre != null ? cuentoNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenciaxcuentoPK)) {
            return false;
        }
        PreferenciaxcuentoPK other = (PreferenciaxcuentoPK) object;
        if ((this.perfilPreferenciaPerfilpwaCedula == null && other.perfilPreferenciaPerfilpwaCedula != null) || (this.perfilPreferenciaPerfilpwaCedula != null && !this.perfilPreferenciaPerfilpwaCedula.equals(other.perfilPreferenciaPerfilpwaCedula))) {
            return false;
        }
        if ((this.cuentoNombre == null && other.cuentoNombre != null) || (this.cuentoNombre != null && !this.cuentoNombre.equals(other.cuentoNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.PreferenciaxcuentoPK[ perfilPreferenciaPerfilpwaCedula=" + perfilPreferenciaPerfilpwaCedula + ", cuentoNombre=" + cuentoNombre + " ]";
    }
    
}
