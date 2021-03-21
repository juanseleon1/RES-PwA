/*
 * @(#)AdmHandler.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.System.Directory;

import java.io.Serializable;
import java.rmi.registry.Registry;
import java.util.UUID;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class AdmHandlerBESA implements Serializable {

    /**
     *
     */
    public static final long serialVersionUID = "AdmHandler".hashCode();
    
    /**
     * 
     */
    protected String admId;
    /**
     *
     */
    protected String alias;
    
    /**
     *
     * @return
     */
    public String getAdmId() {
        return admId;
    }

    /**
     *
     * @return
     */
    public String getAlias() {
        return alias;
    }   
}
