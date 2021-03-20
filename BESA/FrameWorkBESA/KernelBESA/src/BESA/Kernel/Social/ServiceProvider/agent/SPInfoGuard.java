/*
 * @(#)SPInfoGuard.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class SPInfoGuard {

    /**
     *
     */
    private String idGuard;
    /**
     * 
     */
    private String serviceName;
    /**
     * 
     */
    private String dataType;

    /**
     *
     * @param idGuard
     * @param serviceName
     * @param dataType
     */
    public SPInfoGuard(String idGuard, String serviceName, String dataType) {
        this.idGuard = idGuard;
        this.serviceName = serviceName;
        this.dataType = dataType;
    }

    /**
     *
     * @param idGuard
     */
    public SPInfoGuard(String idGuard) {
        this.idGuard = idGuard;
    }

    /**
     *
     * @return
     */
    public String getIdGuard() {
        return idGuard;
    }

    /**
     *
     * @param idGuard
     */
    public void setIdGuard(String idGuard) {
        this.idGuard = idGuard;
    }

    /**
     *
     * @return
     */
    public String getDataType() {
        return dataType;
    }

    /**
     *
     * @param dataType
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     *
     * @return
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
