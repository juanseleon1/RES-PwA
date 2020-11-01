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
import javax.persistence.JoinColumns;
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
@Table(name = "ENRIQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enriq.findAll", query = "SELECT e FROM Enriq e")
    , @NamedQuery(name = "Enriq.findByFrasesOrden", query = "SELECT e FROM Enriq e WHERE e.enriqPK.frasesOrden = :frasesOrden")
    , @NamedQuery(name = "Enriq.findByFrasesCuentoNombre", query = "SELECT e FROM Enriq e WHERE e.enriqPK.frasesCuentoNombre = :frasesCuentoNombre")
    , @NamedQuery(name = "Enriq.findByParams", query = "SELECT e FROM Enriq e WHERE e.enriqPK.params = :params")
    , @NamedQuery(name = "Enriq.findByValor", query = "SELECT e FROM Enriq e WHERE e.valor = :valor")})
public class Enriq implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnriqPK enriqPK;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumns({
        @JoinColumn(name = "FRASES_ORDEN", referencedColumnName = "ORDEN", insertable = false, updatable = false)
        , @JoinColumn(name = "FRASES_CUENTO_NOMBRE", referencedColumnName = "CUENTO_NOMBRE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Frases frases;

    public Enriq() {
    }

    public Enriq(EnriqPK enriqPK) {
        this.enriqPK = enriqPK;
    }

    public Enriq(EnriqPK enriqPK, String valor) {
        this.enriqPK = enriqPK;
        this.valor = valor;
    }

    public Enriq(BigInteger frasesOrden, String frasesCuentoNombre, String params) {
        this.enriqPK = new EnriqPK(frasesOrden, frasesCuentoNombre, params);
    }

    public EnriqPK getEnriqPK() {
        return enriqPK;
    }

    public void setEnriqPK(EnriqPK enriqPK) {
        this.enriqPK = enriqPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Frases getFrases() {
        return frases;
    }

    public void setFrases(Frases frases) {
        this.frases = frases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enriqPK != null ? enriqPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enriq)) {
            return false;
        }
        Enriq other = (Enriq) object;
        if ((this.enriqPK == null && other.enriqPK != null) || (this.enriqPK != null && !this.enriqPK.equals(other.enriqPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Enriq[ enriqPK=" + enriqPK + " ]";
    }
    
}
