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
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ASUS
 */
@Entity
public class Antecedente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ETIQUETA")
    private String Etiqueta;
    
    @ManyToMany(mappedBy = "antecedentesList")
    private List<Regla> reglaList;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private double valor;

    public String getId() {
        return Etiqueta;
    }

    public void setId(String id) {
        this.Etiqueta = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Etiqueta != null ? Etiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antecedente)) {
            return false;
        }
        Antecedente other = (Antecedente) object;
        if ((this.Etiqueta == null && other.Etiqueta != null) || (this.Etiqueta != null && !this.Etiqueta.equals(other.Etiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Antecedente[ id=" + Etiqueta + " ]";
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
