/*
 * @(#)ACLBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent.Event;

import java.io.Serializable;

/**
 * This class allows the handling of a DataBESA.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ACLBESA implements Serializable {

    /**
     *
     */
    private DataBESA data;
    /**
     *
     */
    private static final long serialVersionUID = -451427013L;
    //static final long serialVersionUID = "ACLBESA".hashCode();

    /**
     * Builds a new ACLBESA object.
     */
    public ACLBESA() {
        data = null;
    }

    /**
     * Obtains the data contained in ACLBESA.
     * 
     * @return The data. 
     */
    public DataBESA getData() {
        return data;
    }

    /**
     * Sets the data in ACLBESA.
     * 
     * @param data The data to be set.
     */
    public void setData(DataBESA data) {
        this.data = data;
    }
}
