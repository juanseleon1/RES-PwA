/*
 * @(#)DataMultiGuardBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent.Event;

/**
 * This class represents the guard index associated to a DataBESA.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class DataMultiGuardBESA extends DataBESA {

    /**
     *
     */
    private String guardIndex;
    /**
     *
     */
    public static final long serialVersionUID = "DataMultiGuard".hashCode();

    /**
     * Constructs a new DataMultiGuard
     *  
     * @param guardIndex The guard index name.
     */
    public DataMultiGuardBESA(String guardIndex) {
        this.guardIndex = guardIndex;
    }

    /**
     * Returns the guardIndex to which is directed this DataBESA. It is used
     * when multiguards are contemplated.
     *
     * @return GuardIndex to activate.
     * @uml.property  name="guardIndex"
     */
    public String getGuardIndex() {
        return guardIndex;
    }
}
