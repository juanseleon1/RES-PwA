/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ResPwAEntities;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "ACCIONXJOINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccionxJoint.findAll", query = "SELECT a FROM AccionxJoint a")
    , @NamedQuery(name = "AccionxJoint.findByAccionId", query = "SELECT a FROM AccionxJoint a WHERE a.accionxjointPK.accionId = :accionId")
    , @NamedQuery(name = "AccionxJoint.findByJointId", query = "SELECT a FROM AccionxJoint a WHERE a.accionxjointPK.jointId = :jointId")})

public class AccionxJoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccionxJointPK accionxjointPK;
    @JoinColumn(name = "ACCION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Accion accion;
    @JoinColumn(name = "JOINT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Joint joint;

    public AccionxJoint() {
    }

    public AccionxJoint(AccionxJointPK accionxjointPK) {
        this.accionxjointPK = accionxjointPK;
    }

    public AccionxJoint(BigDecimal accionId, BigDecimal jointId) {
        this.accionxjointPK = new AccionxJointPK(accionId, jointId);
    }

    public AccionxJointPK getAccionxJointPK() {
        return accionxjointPK;
    }

    public void AccionxJointPK(AccionxJointPK accionxjointPK) {
        this.accionxjointPK = accionxjointPK;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Joint getJoint() {
        return joint;
    }

    public void setJoint(Joint joint) {
        this.joint = joint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accionxjointPK != null ? accionxjointPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionxJoint)) {
            return false;
        }
        AccionxJoint other = (AccionxJoint) object;
        if ((this.accionxjointPK == null && other.accionxjointPK != null) || (this.accionxjointPK != null && !this.accionxjointPK.equals(other.accionxjointPK))) {
            return false;
        }
        return true;
    }

}
