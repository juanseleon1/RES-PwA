/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "ACTIVIDADRUTINARIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadrutinaria.findAll", query = "SELECT a FROM Actividadrutinaria a"),
    @NamedQuery(name = "Actividadrutinaria.findByNombre", query = "SELECT a FROM Actividadrutinaria a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Actividadrutinaria.findById", query = "SELECT a FROM Actividadrutinaria a WHERE a.id = :id"),
    @NamedQuery(name = "Actividadrutinaria.findByDuracion", query = "SELECT a FROM Actividadrutinaria a WHERE a.duracion = :duracion"),
    @NamedQuery(name = "Actividadrutinaria.findByHora", query = "SELECT a FROM Actividadrutinaria a WHERE a.hora = :hora")})
public class Actividadrutinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "DURACION")
    private BigDecimal duracion;
    @Basic(optional = false)
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @JoinColumn(name = "PERFIL_MEDICO_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA")
    @ManyToOne(fetch = FetchType.EAGER)
    private PerfilMedico perfilMedicoPerfilpwaCedula;

    public Actividadrutinaria() {
    }

    public Actividadrutinaria(BigDecimal id) {
        this.id = id;
    }

    public Actividadrutinaria(BigDecimal id, String nombre, BigDecimal duracion, Date hora) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public PerfilMedico getPerfilMedicoPerfilpwaCedula() {
        return perfilMedicoPerfilpwaCedula;
    }

    public void setPerfilMedicoPerfilpwaCedula(PerfilMedico perfilMedicoPerfilpwaCedula) {
        this.perfilMedicoPerfilpwaCedula = perfilMedicoPerfilpwaCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadrutinaria)) {
            return false;
        }
        Actividadrutinaria other = (Actividadrutinaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Actividadrutinaria[ id=" + id + " ]";
    }
    
}
