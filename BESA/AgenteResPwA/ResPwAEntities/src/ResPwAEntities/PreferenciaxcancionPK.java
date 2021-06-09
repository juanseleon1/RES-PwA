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
public class PreferenciaxcancionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA")
    private String perfilPreferenciaPerfilpwaCedula;
    @Basic(optional = false)
    @Column(name = "CANCION_NOMBRE")
    private String cancionNombre;

    public PreferenciaxcancionPK() {
    }

    public PreferenciaxcancionPK(String perfilPreferenciaPerfilpwaCedula, String cancionNombre) {
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
        this.cancionNombre = cancionNombre;
    }

    public String getPerfilPreferenciaPerfilpwaCedula() {
        return perfilPreferenciaPerfilpwaCedula;
    }

    public void setPerfilPreferenciaPerfilpwaCedula(String perfilPreferenciaPerfilpwaCedula) {
        this.perfilPreferenciaPerfilpwaCedula = perfilPreferenciaPerfilpwaCedula;
    }

    public String getCancionNombre() {
        return cancionNombre;
    }

    public void setCancionNombre(String cancionNombre) {
        this.cancionNombre = cancionNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilPreferenciaPerfilpwaCedula != null ? perfilPreferenciaPerfilpwaCedula.hashCode() : 0);
        hash += (cancionNombre != null ? cancionNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenciaxcancionPK)) {
            return false;
        }
        PreferenciaxcancionPK other = (PreferenciaxcancionPK) object;
        if ((this.perfilPreferenciaPerfilpwaCedula == null && other.perfilPreferenciaPerfilpwaCedula != null) || (this.perfilPreferenciaPerfilpwaCedula != null && !this.perfilPreferenciaPerfilpwaCedula.equals(other.perfilPreferenciaPerfilpwaCedula))) {
            return false;
        }
        if ((this.cancionNombre == null && other.cancionNombre != null) || (this.cancionNombre != null && !this.cancionNombre.equals(other.cancionNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.PreferenciaxcancionPK[ perfilPreferenciaPerfilpwaCedula=" + perfilPreferenciaPerfilpwaCedula + ", cancionNombre=" + cancionNombre + " ]";
    }
    
}
