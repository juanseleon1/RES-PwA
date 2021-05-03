/*
 * @(#)SPServiceDataRequest.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Kernel.Agent.Event.DataBESA;
import java.io.Serializable;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class SPServiceDataRequest extends DataBESA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = "SPServiceDataRequest".hashCode();
    /**
     * 
     */
    private String guard;
    /**
     * 
     */
    private String dataType;

    /**
     * 
     */
    public SPServiceDataRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param guard
     * @param dataType
     */
    public SPServiceDataRequest(String guard, String dataType) {
        super();
        this.guard = guard;
        this.dataType = dataType;
    }

    /**
     * @return Returns the dataType.
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType The dataType to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return Returns the guard.
     */
    public String getGuard() {
        return guard;
    }

    /**
     * @param guard The guard to set.
     */
    public void setGuard(String guard) {
        this.guard = guard;
    }
}
