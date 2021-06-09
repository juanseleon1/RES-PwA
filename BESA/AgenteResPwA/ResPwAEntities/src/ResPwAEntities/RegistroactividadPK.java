/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author maria.f.garces.cala
 */
@Embeddable
public class RegistroactividadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "PERFILPWA_CEDULA")
    private String perfilpwaCedula;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "ACTIVIDADPWA_ID")
    private BigDecimal actividadpwaId;

    public RegistroactividadPK() {
    }

    public RegistroactividadPK(Date fecha, String perfilpwaCedula, String tipo, BigDecimal actividadpwaId) {
        this.fecha = fecha;
        this.tipo = tipo;
    }
    public RegistroactividadPK(Date fecha, String tipo) {

        this.fecha = fecha;
        this.perfilpwaCedula = perfilpwaCedula;
        this.tipo = tipo;
        this.actividadpwaId = actividadpwaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPerfilpwaCedula() {
        return perfilpwaCedula;
    }

    public void setPerfilpwaCedula(String perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getActividadpwaId() {
        return actividadpwaId;
    }

    public void setActividadpwaId(BigDecimal actividadpwaId) {
        this.actividadpwaId = actividadpwaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (perfilpwaCedula != null ? perfilpwaCedula.hashCode() : 0);
        hash += (tipo != null ? tipo.hashCode() : 0);
        hash += (actividadpwaId != null ? actividadpwaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroactividadPK)) {
            return false;
        }
        RegistroactividadPK other = (RegistroactividadPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.perfilpwaCedula == null && other.perfilpwaCedula != null) || (this.perfilpwaCedula != null && !this.perfilpwaCedula.equals(other.perfilpwaCedula))) {
            return false;
        }
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        if ((this.actividadpwaId == null && other.actividadpwaId != null) || (this.actividadpwaId != null && !this.actividadpwaId.equals(other.actividadpwaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.RegistroactividadPK[ fecha=" + fecha + ", perfilpwaCedula=" + perfilpwaCedula + ", tipo=" + tipo + ", actividadpwaId=" + actividadpwaId + " ]";
    }
    
}
