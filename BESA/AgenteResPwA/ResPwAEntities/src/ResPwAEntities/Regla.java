/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author ASUS
 */
@Entity
public class Regla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "FEEDBACK")
    private float feedback;
    
    @Basic(optional = false)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    
    
    @JoinTable(name = "LISTAANTECEDENTES", joinColumns = {
        @JoinColumn(name = "REGLA_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ANTECEDENTES_ID", referencedColumnName = "ETIQUETA")})
    @ManyToMany
    private List<Antecedente> antecedentesList;
    
//    @Basic(optional = false)
//    @Column(name = "EMOTIONVALUE")
//    private double emotionValue;

    public List<Antecedente> getAntecedentesList() {
        return antecedentesList;
    }

    
    public float getFeedback() {
        return feedback;
    }

    public void setFeedback(float feedback) {
        this.feedback = feedback;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Regla)) {
            return false;
        }
        Regla other = (Regla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Regla[ id=" + id + " ]";
    }
    
}
