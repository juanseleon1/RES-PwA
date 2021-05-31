/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Table(name = "FRASES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frases.findAll", query = "SELECT f FROM Frases f"),
    @NamedQuery(name = "Frases.findByOrden", query = "SELECT f FROM Frases f WHERE f.frasesPK.orden = :orden"),
    @NamedQuery(name = "Frases.findByContenido", query = "SELECT f FROM Frases f WHERE f.contenido = :contenido"),
    @NamedQuery(name = "Frases.findByCuentoNombre", query = "SELECT f FROM Frases f WHERE f.frasesPK.cuentoNombre = :cuentoNombre"),
    @NamedQuery(name = "Frases.findByEmotionalevent", query = "SELECT f FROM Frases f WHERE f.emotionalevent = :emotionalevent"),
    @NamedQuery(name = "Frases.findByAccion", query = "SELECT f FROM Frases f WHERE f.accion = :accion"),
    @NamedQuery(name = "Frases.findByUrlimagen", query = "SELECT f FROM Frases f WHERE f.urlimagen = :urlimagen")})
public class Frases implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FrasesPK frasesPK;
    @Basic(optional = false)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Column(name = "EMOTIONALEVENT")
    private String emotionalevent;
    @Column(name = "ACCION")
    private String accion;
    @Column(name = "URLIMAGEN")
    private String urlimagen;
    @OneToMany(mappedBy = "frases", fetch = FetchType.EAGER)
    private List<Enriq> enriqList;
    @JoinColumn(name = "CUENTO_NOMBRE", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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

    public Frases(BigDecimal orden, String cuentoNombre) {
        this.frasesPK = new FrasesPK(orden, cuentoNombre);
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

    public String getEmotionalevent() {
        return emotionalevent;
    }

    public void setEmotionalevent(String emotionalevent) {
        this.emotionalevent = emotionalevent;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    @XmlTransient
    public List<Enriq> getEnriqList() {
        return enriqList;
    }

    public void setEnriqList(List<Enriq> enriqList) {
        this.enriqList = enriqList;
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
