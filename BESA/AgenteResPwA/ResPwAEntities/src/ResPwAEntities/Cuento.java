/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "CUENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuento.findAll", query = "SELECT c FROM Cuento c")
    , @NamedQuery(name = "Cuento.findByAutor", query = "SELECT c FROM Cuento c WHERE c.autor = :autor")
    , @NamedQuery(name = "Cuento.findByGusto", query = "SELECT c FROM Cuento c WHERE c.gusto = :gusto")
    , @NamedQuery(name = "Cuento.findByNombre", query = "SELECT c FROM Cuento c WHERE c.nombre = :nombre")})
public class Cuento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "AUTOR")
    private String autor;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "PREFERENCIAXCUENTO", joinColumns = {
        @JoinColumn(name = "CUENTO_NOMBRE", referencedColumnName = "NOMBRE")}, inverseJoinColumns = {
        @JoinColumn(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA")})
    @ManyToMany
    private List<PerfilPreferencia> perfilPreferenciaList;
    @JoinColumn(name = "GENERO_GENERO", referencedColumnName = "GENERO")
    @ManyToOne(optional = false)
    private Genero generoGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuento")
    private List<Frases> frasesList;

    public Cuento() {
    }

    public Cuento(String nombre) {
        this.nombre = nombre;
    }

    public Cuento(String nombre, String autor, double gusto) {
        this.nombre = nombre;
        this.autor = autor;
        this.gusto = gusto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<Frases> getFrasesList() {
        return frasesList;
    }

    public void setFrasesList(List<Frases> frasesList) {
        this.frasesList = frasesList;
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
        if (!(object instanceof Cuento)) {
            return false;
        }
        Cuento other = (Cuento) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Cuento[ nombre=" + nombre + " ]";
    }
    
}
