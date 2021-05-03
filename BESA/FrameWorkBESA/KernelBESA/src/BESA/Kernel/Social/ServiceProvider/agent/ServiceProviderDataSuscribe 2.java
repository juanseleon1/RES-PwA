/*
 * @(#)ServiceProviderDataSuscribe.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ServiceProviderDataSuscribe extends DataBESA {

    /**
     *
     */
    private static final long serialVersionUID = "ServiceProviderDataSuscribe".hashCode();
    /**
     * 
     */
    private SPInfoGuard replyGuardName;
    /**
     * 
     */
    private int type;
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
     * @param replyGuardName Nombre de la clase
     * @param type SYNC_SUBSCRIBE = 0,ASYNC_SUBSCRIBE = 1,SYNC_UNSUBSCRIBE = 2,ASYNC_UNSUBSCRIBE = 3;
     * Use the statio definitions to provide this parameter
     */
    public ServiceProviderDataSuscribe(SPInfoGuard replyGuardName, int type) {
        super();
        this.replyGuardName = replyGuardName;
        this.type = type;
    }

    /**
     *
     * @param replyGuardName Nombre de la clase
     * @param type SYNC_SUBSCRIBE = 0,ASYNC_SUBSCRIBE = 1,SYNC_UNSUBSCRIBE = 2,ASYNC_UNSUBSCRIBE = 3;
     */
    public ServiceProviderDataSuscribe(String replyGuardName, int type, String serviceName, String dataType) {
        super();
        this.replyGuardName = new SPInfoGuard(replyGuardName, serviceName, dataType);
        this.type = type;
        this.serviceName = serviceName;
        this.dataType = dataType;
    }

    /**
     * @return Returns the replyGuardName.
     */
    public SPInfoGuard getReplyGuardName() {
        return replyGuardName;
    }

    /**
     * @return Returns the type.
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param agId
     */
    public void sendEvent(String agId) throws ExceptionBESA {
        EventBESA event = new EventBESA("BESA.Social.ServiceProvider.guards.GuardServiceProviderSusbribe", this);
        (AdmBESA.getInstance().getHandlerByAid(agId)).sendEvent(event);
    }

    /**
     * @return Returns the dataType.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param dataType The dataType to set.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
}
