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
import javax.persistence.FetchType;
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
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "CANCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancion.findByUrl", query = "SELECT c FROM Cancion c WHERE c.url = :url")})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "URL")
    private String url;
    @JoinTable(name = "LISTATAGS", joinColumns = {
        @JoinColumn(name = "CANCION_NOMBRE", referencedColumnName = "NOMBRE")}, inverseJoinColumns = {
        @JoinColumn(name = "TAGS_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tags> tagsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancionNombre", fetch = FetchType.EAGER)
    private List<Enriq> enriqList;
    @JoinColumn(name = "GENERO_GENERO", referencedColumnName = "GENERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Genero generoGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion", fetch = FetchType.EAGER)
    private List<Preferenciaxcancion> preferenciaxcancionList;

    public Cancion() {
    }

    public Cancion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<Tags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tags> tagsList) {
        this.tagsList = tagsList;
    }

    @XmlTransient
    public List<Enriq> getEnriqList() {
        return enriqList;
    }

    public void setEnriqList(List<Enriq> enriqList) {
        this.enriqList = enriqList;
    }

    public Genero getGeneroGenero() {
        return generoGenero;
    }

    public void setGeneroGenero(Genero generoGenero) {
        this.generoGenero = generoGenero;
    }

    @XmlTransient
    public List<Preferenciaxcancion> getPreferenciaxcancionList() {
        return preferenciaxcancionList;
    }

    public void setPreferenciaxcancionList(List<Preferenciaxcancion> preferenciaxcancionList) {
        this.preferenciaxcancionList = preferenciaxcancionList;
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
