/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "REGISTROACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroactividad.findAll", query = "SELECT r FROM Registroactividad r")
    , @NamedQuery(name = "Registroactividad.findByFecha", query = "SELECT r FROM Registroactividad r WHERE r.registroactividadPK.fecha = :fecha")
    , @NamedQuery(name = "Registroactividad.findByEstadoinicial", query = "SELECT r FROM Registroactividad r WHERE r.estadoinicial = :estadoinicial")
    , @NamedQuery(name = "Registroactividad.findByEstadofinal", query = "SELECT r FROM Registroactividad r WHERE r.estadofinal = :estadofinal")
    , @NamedQuery(name = "Registroactividad.findByTipo", query = "SELECT r FROM Registroactividad r WHERE r.registroactividadPK.tipo = :tipo")})
public class Registroactividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroactividadPK registroactividadPK;
    @Basic(optional = false)
    @Column(name = "ESTADOINICIAL")
    private String estadoinicial;
    @Basic(optional = false)
    @Column(name = "ESTADOFINAL")
    private String estadofinal;
    @JoinColumn(name = "ACTIVIDADPWA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Actividadpwa actividadpwaId;
    @JoinColumn(name = "PERFILPWA_CEDULA", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Perfilpwa perfilpwaCedula;

    public Registroactividad() {
    }

    public Registroactividad(RegistroactividadPK registroactividadPK) {
        this.registroactividadPK = registroactividadPK;
    }

    public Registroactividad(RegistroactividadPK registroactividadPK, String estadoinicial, String estadofinal) {
        this.registroactividadPK = registroactividadPK;
        this.estadoinicial = estadoinicial;
        this.estadofinal = estadofinal;
    }

    public Registroactividad(Date fecha, String tipo) {
        this.registroactividadPK = new RegistroactividadPK(fecha, tipo);
    }

    public RegistroactividadPK getRegistroactividadPK() {
        return registroactividadPK;
    }

    public void setRegistroactividadPK(RegistroactividadPK registroactividadPK) {
        this.registroactividadPK = registroactividadPK;
    }

    public String getEstadoinicial() {
        return estadoinicial;
    }

    public void setEstadoinicial(String estadoinicial) {
        this.estadoinicial = estadoinicial;
    }

    public String getEstadofinal() {
        return estadofinal;
    }

    public void setEstadofinal(String estadofinal) {
        this.estadofinal = estadofinal;
    }

    public Actividadpwa getActividadpwaId() {
        return actividadpwaId;
    }

    public void setActividadpwaId(Actividadpwa actividadpwaId) {
        this.actividadpwaId = actividadpwaId;
    }

    public Perfilpwa getPerfilpwaCedula() {
        return perfilpwaCedula;
    }

    public void setPerfilpwaCedula(Perfilpwa perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroactividadPK != null ? registroactividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroactividad)) {
            return false;
        }
        Registroactividad other = (Registroactividad) object;
        if ((this.registroactividadPK == null && other.registroactividadPK != null) || (this.registroactividadPK != null && !this.registroactividadPK.equals(other.registroactividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Registroactividad[ registroactividadPK=" + registroactividadPK + " ]";
    }
    
}
