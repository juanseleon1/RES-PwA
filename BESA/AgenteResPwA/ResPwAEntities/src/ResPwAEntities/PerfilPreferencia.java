/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "PERFIL_PREFERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilPreferencia.findAll", query = "SELECT p FROM PerfilPreferencia p")
    , @NamedQuery(name = "PerfilPreferencia.findByPerfilpwaCedula", query = "SELECT p FROM PerfilPreferencia p WHERE p.perfilpwaCedula = :perfilpwaCedula")
    , @NamedQuery(name = "PerfilPreferencia.findByNombrepreferido", query = "SELECT p FROM PerfilPreferencia p WHERE p.nombrepreferido = :nombrepreferido")
    , @NamedQuery(name = "PerfilPreferencia.findByGustokaraoke", query = "SELECT p FROM PerfilPreferencia p WHERE p.gustokaraoke = :gustokaraoke")
    , @NamedQuery(name = "PerfilPreferencia.findByGustomusica", query = "SELECT p FROM PerfilPreferencia p WHERE p.gustomusica = :gustomusica")
    , @NamedQuery(name = "PerfilPreferencia.findByGustobaile", query = "SELECT p FROM PerfilPreferencia p WHERE p.gustobaile = :gustobaile")
    , @NamedQuery(name = "PerfilPreferencia.findByVolpreferido", query = "SELECT p FROM PerfilPreferencia p WHERE p.volpreferido = :volpreferido")})
public class PerfilPreferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERFILPWA_CEDULA")
    private String perfilpwaCedula;
    @Basic(optional = false)
    @Column(name = "NOMBREPREFERIDO")
    private String nombrepreferido;
    @Basic(optional = false)
    @Column(name = "GUSTOKARAOKE")
    private double gustokaraoke;
    @Basic(optional = false)
    @Column(name = "GUSTOMUSICA")
    private double gustomusica;
    @Basic(optional = false)
    @Column(name = "GUSTOBAILE")
    private double gustobaile;
    @Basic(optional = false)
    @Column(name = "VOLPREFERIDO")
    private BigInteger volpreferido;
    @ManyToMany(mappedBy = "perfilPreferenciaList")
    private List<Actividadpwa> actividadpwaList;
    @ManyToMany(mappedBy = "perfilPreferenciaList")
    private List<Cuento> cuentoList;
    @ManyToMany(mappedBy = "perfilPreferenciaList")
    private List<Cancion> cancionList;
    @JoinColumn(name = "PERFILPWA_CEDULA", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Perfilpwa perfilpwa;

    public PerfilPreferencia() {
    }

    public PerfilPreferencia(String perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    public PerfilPreferencia(String perfilpwaCedula, String nombrepreferido, double gustokaraoke, double gustomusica, double gustobaile, BigInteger volpreferido) {
        this.perfilpwaCedula = perfilpwaCedula;
        this.nombrepreferido = nombrepreferido;
        this.gustokaraoke = gustokaraoke;
        this.gustomusica = gustomusica;
        this.gustobaile = gustobaile;
        this.volpreferido = volpreferido;
    }

    public String getPerfilpwaCedula() {
        return perfilpwaCedula;
    }

    public void setPerfilpwaCedula(String perfilpwaCedula) {
        this.perfilpwaCedula = perfilpwaCedula;
    }

    public String getNombrepreferido() {
        return nombrepreferido;
    }

    public void setNombrepreferido(String nombrepreferido) {
        this.nombrepreferido = nombrepreferido;
    }

    public double getGustokaraoke() {
        return gustokaraoke;
    }

    public void setGustokaraoke(double gustokaraoke) {
        this.gustokaraoke = gustokaraoke;
    }

    public double getGustomusica() {
        return gustomusica;
    }

    public void setGustomusica(double gustomusica) {
        this.gustomusica = gustomusica;
    }

    public double getGustobaile() {
        return gustobaile;
    }

    public void setGustobaile(double gustobaile) {
        this.gustobaile = gustobaile;
    }

    public BigInteger getVolpreferido() {
        return volpreferido;
    }

    public void setVolpreferido(BigInteger volpreferido) {
        this.volpreferido = volpreferido;
    }

    @XmlTransient
    public List<Actividadpwa> getActividadpwaList() {
        return actividadpwaList;
    }

    public void setActividadpwaList(List<Actividadpwa> actividadpwaList) {
        this.actividadpwaList = actividadpwaList;
    }

    @XmlTransient
    public List<Cuento> getCuentoList() {
        return cuentoList;
    }

    public void setCuentoList(List<Cuento> cuentoList) {
        this.cuentoList = cuentoList;
    }

    @XmlTransient
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public Perfilpwa getPerfilpwa() {
        return perfilpwa;
    }

    public void setPerfilpwa(Perfilpwa perfilpwa) {
        this.perfilpwa = perfilpwa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilpwaCedula != null ? perfilpwaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilPreferencia)) {
            return false;
        }
        PerfilPreferencia other = (PerfilPreferencia) object;
        if ((this.perfilpwaCedula == null && other.perfilpwaCedula != null) || (this.perfilpwaCedula != null && !this.perfilpwaCedula.equals(other.perfilpwaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.PerfilPreferencia[ perfilpwaCedula=" + perfilpwaCedula + " ]";
    }
    
}
