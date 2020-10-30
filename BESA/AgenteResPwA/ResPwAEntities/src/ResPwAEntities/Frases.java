/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "FRASES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frases.findAll", query = "SELECT f FROM Frases f"),
    @NamedQuery(name = "Frases.findByContenido", query = "SELECT f FROM Frases f WHERE f.contenido = :contenido"),
    @NamedQuery(name = "Frases.findByOrden", query = "SELECT f FROM Frases f WHERE f.frasesPK.orden = :orden"),
    @NamedQuery(name = "Frases.findByNombrecuento", query = "SELECT f FROM Frases f WHERE f.frasesPK.nombrecuento = :nombrecuento")})
public class Frases implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FrasesPK frasesPK;
    @Basic(optional = false)
    @Column(name = "CONTENIDO")
    private String contenido;
    @JoinColumn(name = "NOMBRECUENTO", referencedColumnName = "NOMBRECUENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuento cuento;

    public Frases() {
    }

    public Frases(FrasesPK frasesPK) {
        this.frasesPK = frasesPK;
    }

    public Frases(FrasesPK frasesPK, String contenido) {
        this.frasesPK = frasesPK;
        this.contenido = contenido;
    }

    public Frases(BigInteger orden, String nombrecuento) {
        this.frasesPK = new FrasesPK(orden, nombrecuento);
    }

    public FrasesPK getFrasesPK() {
        return frasesPK;
    }

    public void setFrasesPK(FrasesPK frasesPK) {
        this.frasesPK = frasesPK;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Cuento getCuento() {
        return cuento;
    }

    public void setCuento(Cuento cuento) {
        this.cuento = cuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frasesPK != null ? frasesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frases)) {
            return false;
        }
        Frases other = (Frases) object;
        if ((this.frasesPK == null && other.frasesPK != null) || (this.frasesPK != null && !this.frasesPK.equals(other.frasesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Frases[ frasesPK=" + frasesPK + " ]";
    }
    
}
