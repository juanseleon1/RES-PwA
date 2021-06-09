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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "FAMILIAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familiar.findAll", query = "SELECT f FROM Familiar f"),
    @NamedQuery(name = "Familiar.findById", query = "SELECT f FROM Familiar f WHERE f.id = :id"),
    @NamedQuery(name = "Familiar.findByNombre", query = "SELECT f FROM Familiar f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Familiar.findByParentesco", query = "SELECT f FROM Familiar f WHERE f.parentesco = :parentesco"),
    @NamedQuery(name = "Familiar.findByInteres", query = "SELECT f FROM Familiar f WHERE f.interes = :interes"),
    @NamedQuery(name = "Familiar.findByEstavivo", query = "SELECT f FROM Familiar f WHERE f.estavivo = :estavivo"),
    @NamedQuery(name = "Familiar.findByNacimiento", query = "SELECT f FROM Familiar f WHERE f.nacimiento = :nacimiento")})
public class Familiar implements Serializable {

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
    @Column(name = "PARENTESCO")
    private String parentesco;
    @Basic(optional = false)
    @Column(name = "INTERES")
    private double interes;
    @Basic(optional = false)
    @Column(name = "ESTAVIVO")
    private BigDecimal estavivo;
    @Column(name = "NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nacimiento;
    @JoinTable(name = "FAMILIARES", joinColumns = {
        @JoinColumn(name = "FAMILIAR_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PERFILPWA_CEDULA", referencedColumnName = "CEDULA")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfilpwa> perfilpwaList;

    public Familiar() {
    }

    public Familiar(BigDecimal id) {
        this.id = id;
    }

    public Familiar(BigDecimal id, String nombre, String parentesco, double interes, BigDecimal estavivo) {
        this.id = id;
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.interes = interes;
        this.estavivo = estavivo;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public BigDecimal getEstavivo() {
        return estavivo;
    }

    public void setEstavivo(BigDecimal estavivo) {
        this.estavivo = estavivo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    @XmlTransient
    public List<Perfilpwa> getPerfilpwaList() {
        return perfilpwaList;
    }

    public void setPerfilpwaList(List<Perfilpwa> perfilpwaList) {
        this.perfilpwaList = perfilpwaList;
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
        if (!(object instanceof Familiar)) {
            return false;
        }
        Familiar other = (Familiar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Familiar[ id=" + id + " ]";
    }
    
}
