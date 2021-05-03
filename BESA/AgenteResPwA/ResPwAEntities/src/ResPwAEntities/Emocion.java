/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "EMOCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emocion.findAll", query = "SELECT e FROM Emocion e")
    , @NamedQuery(name = "Emocion.findById", query = "SELECT e FROM Emocion e WHERE e.id = :id")
    , @NamedQuery(name = "Emocion.findByValorMaximo", query = "SELECT e FROM Emocion e WHERE e.valormaximo = :valormaximo")
    , @NamedQuery(name = "Emocion.findByValorMinimo", query = "SELECT e FROM Emocion e WHERE e.valorminimo = :valorminimo")})
public class Emocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "VALORMAXIMO")
    private double valorMaximo;
    @Basic(optional = false)
    @Column(name = "VALORMINIMO")
    private double valorMinimo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emocion")
    private List<Accion> accionList;

    public Emocion(BigDecimal id, double valorMaximo, double valorMinimo) {
        this.id = id;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo() {
        this.valorMaximo = valorMaximo;
    }
    
    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo() {
        this.valorMinimo = valorMinimo;
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
        if (!(object instanceof Familiar)) {
            return false;
        }
        Emocion other = (Emocion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
