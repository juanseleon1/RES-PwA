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
public class ActxpreferenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ACTIVIDADPWA_ID")
    private BigDecimal actividadpwaId;
    @Basic(optional = false)
    @Column(name = "PERFIL_PREFERENCIA_CEDULA")
    private String perfilPreferenciaCedula;

    public ActxpreferenciaPK() {
    }

    public ActxpreferenciaPK(BigDecimal actividadpwaId, String perfilPreferenciaCedula) {
        this.actividadpwaId = actividadpwaId;
        this.perfilPreferenciaCedula = perfilPreferenciaCedula;
    }

    public BigDecimal getActividadpwaId() {
        return actividadpwaId;
    }

    public void setActividadpwaId(BigDecimal actividadpwaId) {
        this.actividadpwaId = actividadpwaId;
    }

    public String getPerfilPreferenciaCedula() {
        return perfilPreferenciaCedula;
    }

    public void setPerfilPreferenciaCedula(String perfilPreferenciaCedula) {
        this.perfilPreferenciaCedula = perfilPreferenciaCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadpwaId != null ? actividadpwaId.hashCode() : 0);
        hash += (perfilPreferenciaCedula != null ? perfilPreferenciaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActxpreferenciaPK)) {
            return false;
        }
        ActxpreferenciaPK other = (ActxpreferenciaPK) object;
        if ((this.actividadpwaId == null && other.actividadpwaId != null) || (this.actividadpwaId != null && !this.actividadpwaId.equals(other.actividadpwaId))) {
            return false;
        }
        if ((this.perfilPreferenciaCedula == null && other.perfilPreferenciaCedula != null) || (this.perfilPreferenciaCedula != null && !this.perfilPreferenciaCedula.equals(other.perfilPreferenciaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.ActxpreferenciaPK[ actividadpwaId=" + actividadpwaId + ", perfilPreferenciaCedula=" + perfilPreferenciaCedula + " ]";
    }
    
}
