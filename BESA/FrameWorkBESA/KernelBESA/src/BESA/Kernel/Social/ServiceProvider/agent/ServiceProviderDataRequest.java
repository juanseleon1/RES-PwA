/*
 * @(#)ServiceProviderDataRequest.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ServiceProviderDataRequest extends DataBESA {

    /**
     *
     */
    private static final long serialVersionUID = "ServiceProviderDataRequest".hashCode();
    /**
     * 
     */
    private String agID;
    /**
     * 
     */
    private String serviceName;
    /**
     * 
     */
    private SPServiceDataRequest spRequestData;

    /**
     *
     */
    public ServiceProviderDataRequest(String agID, String sName, SPServiceDataRequest spData) {
        super();
        this.agID = agID;
        this.serviceName = sName;
        this.spRequestData = spData;
    }

    /**
     * @return Returns the agID.
     */
    public String getAgID() {
        return agID;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @return Returns the spRequestData.
     */
    public SPServiceDataRequest getSpRequestData() {
        return spRequestData;
    }
}
