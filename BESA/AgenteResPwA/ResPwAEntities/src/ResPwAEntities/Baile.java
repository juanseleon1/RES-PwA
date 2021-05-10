/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "BAILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baile.findAll", query = "SELECT b FROM Baile b"),
    @NamedQuery(name = "Baile.findById", query = "SELECT b FROM Baile b WHERE b.id = :id"),
    @NamedQuery(name = "Baile.findByNombre", query = "SELECT b FROM Baile b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Baile.findByGusto", query = "SELECT b FROM Baile b WHERE b.gusto = :gusto")})
public class Baile implements Serializable {

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
    @Column(name = "GUSTO")
    private double gusto;
    @JoinTable(name = "BAILEXPERFILPREFERENCIA", joinColumns = {
        @JoinColumn(name = "BAILE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PERFIL_PREFERENCIA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PerfilPreferencia> perfilPreferenciaList;
    @JoinColumn(name = "GENERO_GENERO", referencedColumnName = "GENERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Genero generoGenero;

    public Baile() {
    }

    public Baile(BigDecimal id) {
        this.id = id;
    }

    public Baile(BigDecimal id, String nombre, double gusto) {
        this.id = id;
        this.nombre = nombre;
        this.gusto = gusto;
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

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    @XmlTransient
    public List<PerfilPreferencia> getPerfilPreferenciaList() {
        return perfilPreferenciaList;
    }

    public void setPerfilPreferenciaList(List<PerfilPreferencia> perfilPreferenciaList) {
        this.perfilPreferenciaList = perfilPreferenciaList;
    }

    public Genero getGeneroGenero() {
        return generoGenero;
    }

    public void setGeneroGenero(Genero generoGenero) {
        this.generoGenero = generoGenero;
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
        if (!(object instanceof Baile)) {
            return false;
        }
        Baile other = (Baile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Baile[ id=" + id + " ]";
    }
    
}
