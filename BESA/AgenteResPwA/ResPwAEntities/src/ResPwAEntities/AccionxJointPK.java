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
import javax.persistence.Embeddable;

/**
 *
 * @author maria.f.garces.cala
 */
@Embeddable
public class AccionxJointPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ACCION_ID")
    private BigDecimal accionId;
    @Basic(optional = false)
    @Column(name = "JOINT_ID")
    private BigDecimal jointId;

    public AccionxJointPK() {
    }

    public AccionxJointPK(BigDecimal accionId, BigDecimal jointId) {
        this.accionId = accionId;
        this.jointId = jointId;
    }

    public BigDecimal getAccionId() {
        return accionId;
    }

    public void setAccionId(BigDecimal accionId) {
        this.accionId = accionId;
    }

    public BigDecimal getJointId() {
        return jointId;
    }

    public void setJointId(BigDecimal jointId) {
        this.jointId = jointId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accionId != null ? accionId.hashCode() : 0);
        hash += (jointId != null ? jointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionxJointPK)) {
            return false;
        }
        AccionxJointPK other = (AccionxJointPK) object;
        if ((this.accionId == null && other.accionId != null) || (this.accionId != null && !this.accionId.equals(other.accionId))) {
            return false;
        }
        if ((this.jointId == null && other.jointId != null) || (this.jointId != null && !this.jointId.equals(other.jointId))) {
            return false;
        }
        return true;
    }

}
