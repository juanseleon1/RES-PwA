/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "ENRIQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enriq.findAll", query = "SELECT e FROM Enriq e"),
    @NamedQuery(name = "Enriq.findByParams", query = "SELECT e FROM Enriq e WHERE e.params = :params"),
    @NamedQuery(name = "Enriq.findByValor", query = "SELECT e FROM Enriq e WHERE e.valor = :valor")})
public class Enriq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PARAMS")
    private String params;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "CANCION_NOMBRE", referencedColumnName = "NOMBRE")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cancion cancionNombre;
    @JoinColumns({
        @JoinColumn(name = "FRASES_ORDEN", referencedColumnName = "ORDEN"),
        @JoinColumn(name = "FRASES_NOMBRE", referencedColumnName = "CUENTO_NOMBRE")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Frases frases;

    public Enriq() {
    }

    public Enriq(String params) {
        this.params = params;
    }

    public Enriq(String params, String valor) {
        this.params = params;
        this.valor = valor;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Cancion getCancionNombre() {
        return cancionNombre;
    }

    public void setCancionNombre(Cancion cancionNombre) {
        this.cancionNombre = cancionNombre;
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
        hash += (params != null ? params.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enriq)) {
            return false;
        }
        Enriq other = (Enriq) object;
        if ((this.params == null && other.params != null) || (this.params != null && !this.params.equals(other.params))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Enriq[ params=" + params + " ]";
    }
    
}
