/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "PERFILPWA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfilpwa.findAll", query = "SELECT p FROM Perfilpwa p"),
    @NamedQuery(name = "Perfilpwa.findByNombre", query = "SELECT p FROM Perfilpwa p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Perfilpwa.findByApellido", query = "SELECT p FROM Perfilpwa p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Perfilpwa.findByFechanacimiento", query = "SELECT p FROM Perfilpwa p WHERE p.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Perfilpwa.findByPaisnacimiento", query = "SELECT p FROM Perfilpwa p WHERE p.paisnacimiento = :paisnacimiento"),
    @NamedQuery(name = "Perfilpwa.findByEdad", query = "SELECT p FROM Perfilpwa p WHERE p.edad = :edad"),
    @NamedQuery(name = "Perfilpwa.findByCedula", query = "SELECT p FROM Perfilpwa p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Perfilpwa.findByProfesion", query = "SELECT p FROM Perfilpwa p WHERE p.profesion = :profesion")})
public class Perfilpwa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "PAISNACIMIENTO")
    private String paisnacimiento;
    @Basic(optional = false)
    @Column(name = "EDAD")
    private BigDecimal edad;
    @Id
    @Basic(optional = false)
    @Column(name = "CEDULA")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "PROFESION")
    private String profesion;
    @ManyToMany(mappedBy = "perfilpwaList", fetch = FetchType.EAGER)
    private List<Familiar> familiarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilpwa", fetch = FetchType.EAGER)
    private List<Registroactividad> registroactividadList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perfilpwa", fetch = FetchType.EAGER)
    private PerfilMedico perfilMedico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perfilpwa", fetch = FetchType.EAGER)
    private PerfilPreferencia perfilPreferencia;
    @JoinColumn(name = "CUIDADOR_NOMBREUSUARIO", referencedColumnName = "NOMBREUSUARIO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cuidador cuidadorNombreusuario;
    @JoinColumn(name = "ESTADOCIVIL_TIPOEC", referencedColumnName = "TIPOEC")
    @ManyToOne(fetch = FetchType.EAGER)
    private Estadocivil estadocivilTipoec;
    @JoinColumn(name = "NIVEL_EDUCATIVO_TIPONE", referencedColumnName = "TIPONE")
    @ManyToOne(fetch = FetchType.EAGER)
    private NivelEducativo nivelEducativoTipone;

    public Perfilpwa() {
    }

    public Perfilpwa(String cedula) {
        this.cedula = cedula;
    }

    public Perfilpwa(String cedula, String nombre, String apellido, Date fechanacimiento, String paisnacimiento, BigDecimal edad, String profesion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.paisnacimiento = paisnacimiento;
        this.edad = edad;
        this.profesion = profesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getPaisnacimiento() {
        return paisnacimiento;
    }

    public void setPaisnacimiento(String paisnacimiento) {
        this.paisnacimiento = paisnacimiento;
    }

    public BigDecimal getEdad() {
        return edad;
    }

    public void setEdad(BigDecimal edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @XmlTransient
    public List<Familiar> getFamiliarList() {
        return familiarList;
    }

    public void setFamiliarList(List<Familiar> familiarList) {
        this.familiarList = familiarList;
    }

    @XmlTransient
    public List<Registroactividad> getRegistroactividadList() {
        return registroactividadList;
    }

    public void setRegistroactividadList(List<Registroactividad> registroactividadList) {
        this.registroactividadList = registroactividadList;
    }

    public PerfilMedico getPerfilMedico() {
        return perfilMedico;
    }

    public void setPerfilMedico(PerfilMedico perfilMedico) {
        this.perfilMedico = perfilMedico;
    }

    public PerfilPreferencia getPerfilPreferencia() {
        return perfilPreferencia;
    }

    public void setPerfilPreferencia(PerfilPreferencia perfilPreferencia) {
        this.perfilPreferencia = perfilPreferencia;
    }

    public Cuidador getCuidadorNombreusuario() {
        return cuidadorNombreusuario;
    }

    public void setCuidadorNombreusuario(Cuidador cuidadorNombreusuario) {
        this.cuidadorNombreusuario = cuidadorNombreusuario;
    }

    public Estadocivil getEstadocivilTipoec() {
        return estadocivilTipoec;
    }

    public void setEstadocivilTipoec(Estadocivil estadocivilTipoec) {
        this.estadocivilTipoec = estadocivilTipoec;
    }

    public NivelEducativo getNivelEducativoTipone() {
        return nivelEducativoTipone;
    }

    public void setNivelEducativoTipone(NivelEducativo nivelEducativoTipone) {
        this.nivelEducativoTipone = nivelEducativoTipone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfilpwa)) {
            return false;
        }
        Perfilpwa other = (Perfilpwa) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Perfilpwa[ cedula=" + cedula + " ]";
    }
    
}
