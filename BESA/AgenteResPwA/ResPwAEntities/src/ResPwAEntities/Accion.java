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
import javax.persistence.ManyToMany;
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
@Table(name = "ACCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a")
    , @NamedQuery(name = "Accion.findById", query = "SELECT a FROM Accion a WHERE a.id = :id")
    , @NamedQuery(name = "Accion.findByNombre", query = "SELECT a FROM Accion a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Accion.findByTipo", query = "SELECT a FROM Accion a WHERE a.tipo = :tipo")})
public class Accion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "ROBOT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Robot robot;
    @JoinColumn(name = "EMOCION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Emocion emocion;
    @ManyToMany(mappedBy = "jointList")
    private List<Joint> jointList;

    public Accion(BigDecimal id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    @XmlTransient
    public List<Joint> getJointList() {
        return jointList;
    }

    public void setJointList(List<Joint> jointList) {
        this.jointList = jointList;
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
        Accion other = (Accion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}