/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "ACTIVIDADPWA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadpwa.findAll", query = "SELECT a FROM Actividadpwa a")
    , @NamedQuery(name = "Actividadpwa.findById", query = "SELECT a FROM Actividadpwa a WHERE a.id = :id")
    , @NamedQuery(name = "Actividadpwa.findByNombre", query = "SELECT a FROM Actividadpwa a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Actividadpwa.findByTipo", query = "SELECT a FROM Actividadpwa a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "Actividadpwa.findByDuracion", query = "SELECT a FROM Actividadpwa a WHERE a.duracion = :duracion")
    , @NamedQuery(name = "Actividadpwa.findByGusto", query = "SELECT a FROM Actividadpwa a WHERE a.gusto = :gusto")
    , @NamedQuery(name = "Actividadpwa.findByEnriqfav", query = "SELECT a FROM Actividadpwa a WHERE a.enriqfav = :enriqfav")})
public class Actividadpwa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "DURACION")
    private BigInteger duracion;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Basic(optional = false)
    @Column(name = "ENRIQFAV")
    private BigInteger enriqfav;
    @JoinTable(name = "ACTXPREFERENCIA", joinColumns = {
        @JoinColumn(name = "ACTIVIDADPWA_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PERFIL_PREFERENCIA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA")})
    @ManyToMany
    private List<PerfilPreferencia> perfilPreferenciaList;
    @JoinColumn(name = "DIFICULTAD_DIFICULTAD", referencedColumnName = "DIFICULTAD")
    @ManyToOne
    private Dificultad dificultadDificultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadpwa")
    private List<Registroactividad> registroactividadList;

    public Actividadpwa() {
    }

    public Actividadpwa(BigInteger id) {
        this.id = id;
    }

    public Actividadpwa(BigInteger id, String nombre, String tipo, BigInteger duracion, double gusto, BigInteger enriqfav) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.duracion = duracion;
        this.gusto = gusto;
        this.enriqfav = enriqfav;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public BigInteger getDuracion() {
        return duracion;
    }

    public void setDuracion(BigInteger duracion) {
        this.duracion = duracion;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public BigInteger getEnriqfav() {
        return enriqfav;
    }

    public void setEnriqfav(BigInteger enriqfav) {
        this.enriqfav = enriqfav;
    }

    @XmlTransient
    public List<PerfilPreferencia> getPerfilPreferenciaList() {
        return perfilPreferenciaList;
    }

    public void setPerfilPreferenciaList(List<PerfilPreferencia> perfilPreferenciaList) {
        this.perfilPreferenciaList = perfilPreferenciaList;
    }

    public Dificultad getDificultadDificultad() {
        return dificultadDificultad;
    }

    public void setDificultadDificultad(Dificultad dificultadDificultad) {
        this.dificultadDificultad = dificultadDificultad;
    }

    @XmlTransient
    public List<Registroactividad> getRegistroactividadList() {
        return registroactividadList;
    }

    public void setRegistroactividadList(List<Registroactividad> registroactividadList) {
        this.registroactividadList = registroactividadList;
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
