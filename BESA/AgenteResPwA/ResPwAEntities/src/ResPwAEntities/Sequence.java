/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author maria.f.garces.cala
 */
@Entity
@Table(name = "SEQUENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequence.findAll", query = "SELECT s FROM Sequence s"),
    @NamedQuery(name = "Sequence.findBySeqName", query = "SELECT s FROM Sequence s WHERE s.seqName = :seqName"),
    @NamedQuery(name = "Sequence.findBySeqCount", query = "SELECT s FROM Sequence s WHERE s.seqCount = :seqCount")})
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SEQ_NAME")
    private String seqName;
    @Column(name = "SEQ_COUNT")
    private BigInteger seqCount;

    public Sequence() {
    }

    public Sequence(String seqName) {
        this.seqName = seqName;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public BigInteger getSeqCount() {
        return seqCount;
    }

    public void setSeqCount(BigInteger seqCount) {
        this.seqCount = seqCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqName != null ? seqName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sequence)) {
            return false;
        }
        Sequence other = (Sequence) object;
        if ((this.seqName == null && other.seqName != null) || (this.seqName != null && !this.seqName.equals(other.seqName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Sequence[ seqName=" + seqName + " ]";
    }
    
}
