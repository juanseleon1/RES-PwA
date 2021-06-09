/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "ACTIVIDADPWA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadpwa.findAll", query = "SELECT a FROM Actividadpwa a"),
    @NamedQuery(name = "Actividadpwa.findById", query = "SELECT a FROM Actividadpwa a WHERE a.id = :id"),
    @NamedQuery(name = "Actividadpwa.findByNombre", query = "SELECT a FROM Actividadpwa a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Actividadpwa.findByTipo", query = "SELECT a FROM Actividadpwa a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Actividadpwa.findByDuracion", query = "SELECT a FROM Actividadpwa a WHERE a.duracion = :duracion")})
public class Actividadpwa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "DURACION")
    private BigDecimal duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadpwa", fetch = FetchType.EAGER)
    private List<Registroactividad> registroactividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadpwa", fetch = FetchType.EAGER)
    private List<Actxpreferencia> actxpreferenciaList;

    public Actividadpwa() {
    }

    public Actividadpwa(BigDecimal id) {
        this.id = id;
    }

    public Actividadpwa(BigDecimal id, String nombre, String tipo, BigDecimal duracion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.duracion = duracion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public List<Registroactividad> getRegistroactividadList() {
        return registroactividadList;
    }

    public void setRegistroactividadList(List<Registroactividad> registroactividadList) {
        this.registroactividadList = registroactividadList;
    }

    @XmlTransient
    public List<Actxpreferencia> getActxpreferenciaList() {
        return actxpreferenciaList;
    }

    public void setActxpreferenciaList(List<Actxpreferencia> actxpreferenciaList) {
        this.actxpreferenciaList = actxpreferenciaList;
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
        if (!(object instanceof Actividadpwa)) {
            return false;
        }
        Actividadpwa other = (Actividadpwa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Actividadpwa[ id=" + id + " ]";
    }
    
}
