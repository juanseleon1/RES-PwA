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
@Table(name = "GENERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
    @NamedQuery(name = "Genero.findByNombregenero", query = "SELECT g FROM Genero g WHERE g.nombregenero = :nombregenero"),
    @NamedQuery(name = "Genero.findByGusto", query = "SELECT g FROM Genero g WHERE g.gusto = :gusto")})
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBREGENERO")
    private String nombregenero;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoNombregenero")
    private List<Cancion> cancionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generoNombregenero")
    private List<Cuento> cuentoList;

    public Genero() {
    }

    public Genero(String nombregenero) {
        this.nombregenero = nombregenero;
    }

    public Genero(String nombregenero, double gusto) {
        this.nombregenero = nombregenero;
        this.gusto = gusto;
    }

    public String getNombregenero() {
        return nombregenero;
    }

    public void setNombregenero(String nombregenero) {
        this.nombregenero = nombregenero;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }

    @XmlTransient
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    @XmlTransient
    public List<Cuento> getCuentoList() {
        return cuentoList;
    }

    public void setCuentoList(List<Cuento> cuentoList) {
        this.cuentoList = cuentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombregenero != null ? nombregenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.nombregenero == null && other.nombregenero != null) || (this.nombregenero != null && !this.nombregenero.equals(other.nombregenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RobotAgentBDI.Believes.PerfilPwA.Genero[ nombregenero=" + nombregenero + " ]";
    }
    
}
