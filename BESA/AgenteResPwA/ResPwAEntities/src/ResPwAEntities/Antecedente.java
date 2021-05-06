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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ASUS
 */

@NamedQueries({
    @NamedQuery(name = "Antecedente.findAll", query = "SELECT a FROM Antecedente a")
    , @NamedQuery(name = "Antecedente.findByEtiqueta", query = "SELECT a FROM Antecedente a WHERE a.etiqueta= :etiqueta")})
@Entity
public class Antecedente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ETIQUETA")
    private String etiqueta;
    
    @ManyToMany(mappedBy = "antecedentesList")
    private List<Regla> reglaList;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private double valor;

    public Antecedente() {
    }

    public Antecedente(String Etiqueta, double valor) {
        this.Etiqueta = Etiqueta;
        this.valor = valor;
    }

    
    public String getId() {
        return etiqueta;
    }

    public void setId(String id) {
        this.etiqueta = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etiqueta != null ? etiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antecedente)) {
            return false;
        }
        Antecedente other = (Antecedente) object;
        if ((this.etiqueta == null && other.etiqueta != null) || (this.etiqueta != null && !this.etiqueta.equals(other.etiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Antecedente[ id=" + etiqueta + " ]";
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
