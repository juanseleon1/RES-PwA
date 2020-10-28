/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA;

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
    @NamedQuery(name = "Cuento.findAll", query = "SELECT c FROM Cuento c"),
    @NamedQuery(name = "Cuento.findByNombreautor", query = "SELECT c FROM Cuento c WHERE c.nombreautor = :nombreautor"),
    @NamedQuery(name = "Cuento.findByGusto", query = "SELECT c FROM Cuento c WHERE c.gusto = :gusto"),
    @NamedQuery(name = "Cuento.findByNombrecuento", query = "SELECT c FROM Cuento c WHERE c.nombrecuento = :nombrecuento")})
public class Cuento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NOMBREAUTOR")
    private String nombreautor;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRECUENTO")
    private String nombrecuento;
    @JoinTable(name = "PREFERENCIAXCUENTO", joinColumns = {
        @JoinColumn(name = "CUENTO_NOMBRECUENTO", referencedColumnName = "NOMBRECUENTO")}, inverseJoinColumns = {
        @JoinColumn(name = "PERFIL_PREFERENCIA_PERFILPWA_CEDULA", referencedColumnName = "PERFILPWA_CEDULA")})
    @ManyToMany
    private List<PerfilPreferencia> perfilPreferenciaList;
    @JoinColumn(name = "GENERO_NOMBREGENERO", referencedColumnName = "NOMBREGENERO")
    @ManyToOne(optional = false)
    private Genero generoNombregenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuento")
    private List<Frases> frasesList;

    public Cuento() {
    }

    public Cuento(String nombrecuento) {
        this.nombrecuento = nombrecuento;
    }

    public Cuento(String nombrecuento, String nombreautor, double gusto) {
        this.nombrecuento = nombrecuento;
        this.nombreautor = nombreautor;
        this.gusto = gusto;
    }

    public String getNombreautor() {
        return nombreautor;
    }

    public void setNombreautor(String nombreautor) {
        this.nombreautor = nombreautor;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    public String getNombrecuento() {
        return nombrecuento;
    }

    public void setNombrecuento(String nombrecuento) {
        this.nombrecuento = nombrecuento;
    }

    @XmlTransient
    public List<PerfilPreferencia> getPerfilPreferenciaList() {
        return perfilPreferenciaList;
    }

    public void setPerfilPreferenciaList(List<PerfilPreferencia> perfilPreferenciaList) {
        this.perfilPreferenciaList = perfilPreferenciaList;
    }

    public Genero getGeneroNombregenero() {
        return generoNombregenero;
    }

    public void setGeneroNombregenero(Genero generoNombregenero) {
        this.generoNombregenero = generoNombregenero;
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
        hash += (nombrecuento != null ? nombrecuento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuento)) {
            return false;
        }
        Cuento other = (Cuento) object;
        if ((this.nombrecuento == null && other.nombrecuento != null) || (this.nombrecuento != null && !this.nombrecuento.equals(other.nombrecuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RobotAgentBDI.Believes.PerfilPwA.Cuento[ nombrecuento=" + nombrecuento + " ]";
    }
    
}
