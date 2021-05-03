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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author ASUS
 */

@Entity
@Table(name = "BAILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baile.findAll", query = "SELECT b FROM Baile b")
    , @NamedQuery(name = "Baile.findByNombre", query = "SELECT b FROM Baile b WHERE b.nombre= :nombre")})
public class Baile implements Serializable{
    
    
    @Id
    private String nombre;
    @Basic(optional = false)
    @Column(name = "GUSTO")
    private double gusto;

    public Baile() {
    }

    public Baile(String nombre, double gusto) {
        this.nombre = nombre;
        this.gusto = gusto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGusto() {
        return gusto;
    }

    public void setGusto(double gusto) {
        this.gusto = gusto;
    }
    
}
