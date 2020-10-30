/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author juans
 */
@Entity
@Table(name = "CANCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancion.findByGusto", query = "SELECT c FROM Cancion c WHERE c.gusto = :gusto")})
public class Cancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @JoinTable(name = "LISTATAGS", joinColumns = {
        @JoinColumn(name = "CANCION_NOMBRE", referencedColumnName = "NOMBRE")}, inverseJoinColumns = {
        @JoinColumn(name = "TAGS_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Tags> tagsList;
    @JoinTable(name = "PREFERENCIACANCION", joinColumns = {
        @JoinColumn(name = "CANCIONNOMBRE", referencedColumnName = "NOMBRE")}, inverseJoinColumns = {
        @JoinColumn(name = "CEDULA", referencedColumnName = "PERFILPWA_CEDULA")})
    @ManyToMany
    private List<PerfilPreferencia> perfilPreferenciaList;
    @JoinColumn(name = "NOMBREGENERO", referencedColumnName = "NOMBREGENERO")
    @ManyToOne(optional = false)
    private Genero nombregenero;

    public Cancion() {
    }

    public Cancion(String nombre) {
        this.nombre = nombre;
    }

    public Cancion(String nombre, double gusto) {
        this.nombre = nombre;
        this.gusto = gusto;
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
    public List<Tags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tags> tagsList) {
        this.tagsList = tagsList;
    }

    @XmlTransient
    public List<PerfilPreferencia> getPerfilPreferenciaList() {
        return perfilPreferenciaList;
    }

    public void setPerfilPreferenciaList(List<PerfilPreferencia> perfilPreferenciaList) {
        this.perfilPreferenciaList = perfilPreferenciaList;
    }

    public Genero getNombregenero() {
        return nombregenero;
    }

    public void setNombregenero(Genero nombregenero) {
        this.nombregenero = nombregenero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Cancion[ nombre=" + nombre + " ]";
    }
    
}
