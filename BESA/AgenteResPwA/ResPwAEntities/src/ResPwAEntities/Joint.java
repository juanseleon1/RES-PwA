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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "JOINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Joint.findAll", query = "SELECT j FROM Joint j"),
    @NamedQuery(name = "Joint.findById", query = "SELECT j FROM Joint j WHERE j.id = :id"),
    @NamedQuery(name = "Joint.findByNombre", query = "SELECT j FROM Joint j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Joint.findByAngulo", query = "SELECT j FROM Joint j WHERE j.angulo = :angulo"),
    @NamedQuery(name = "Joint.findByTiempo", query = "SELECT j FROM Joint j WHERE j.tiempo = :tiempo")})
public class Joint implements Serializable {

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
    @Column(name = "ANGULO")
    private double angulo;
    @Basic(optional = false)
    @Column(name = "TIEMPO")
    private BigDecimal tiempo;
    @ManyToMany(mappedBy = "jointList", fetch = FetchType.EAGER)
    private List<Accion> accionList;

    public Joint() {
    }

    public Joint(BigDecimal id) {
        this.id = id;
    }

    public Joint(BigDecimal id, String nombre, double angulo, BigDecimal tiempo) {
        this.id = id;
        this.nombre = nombre;
        this.angulo = angulo;
        this.tiempo = tiempo;
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

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public BigDecimal getTiempo() {
        return tiempo;
    }

    public void setTiempo(BigDecimal tiempo) {
        this.tiempo = tiempo;
    }

    @XmlTransient
    public List<Accion> getAccionList() {
        return accionList;
    }

    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
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
        if (!(object instanceof Joint)) {
            return false;
        }
        Joint other = (Joint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Joint[ id=" + id + " ]";
    }
    
}
