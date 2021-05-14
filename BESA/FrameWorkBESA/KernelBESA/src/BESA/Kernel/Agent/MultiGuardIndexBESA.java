/*
 * @(#)MultiGuardIndexBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;

/**
 * This class represents a subguard that executes itself if its field ack is equal to the
 * event field in DataBESA.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class MultiGuardIndexBESA {

    /**
     * MultiGuardBESA.
     */
    private MultiGuardBESA multiGuardBESA;
    /**
     * ACK.
     */
    private String ack;

    /**
     * Creates a new instance of MultiGuardIndexBESA. In order to inherit
     * guardIndex associates in the constructor, are due to call the
     * constructor of the base class with multiGuardBESA and if wants to modify
     * ack, it is due to do using the protected method setAck () and in this
     * way mobility is not affected. By default, ack is the class name.
     * 
     * @param multiGuardBESA MultiGuardBESA associated to this guard.
     */
    public MultiGuardIndexBESA(MultiGuardBESA multiGuardBESA) {
        this.setMultiGuardBESA(multiGuardBESA);
        multiGuardBESA.addMultiGuardIndex(this);
        ack = this.getClass().getName();
    }

    /**
     * This function is executed if the field ack is equal to the field ack
     * of DataBESA.
     * 
     * @param event Received event, contains the related data.
     */
    abstract public void funcExecGuard(EventBESA event);

    /**
     * Returns a string that indicate the ack to which responds.
     * 
     * @return The ack string.
     */
    public String getAck() {
        return ack;
    }

    /**
     * Sets the ack string value.
     * 
     * @param ack The ack value. 
     */
    protected void setAck(String ack) {
        this.ack = ack;
    }

    /**
     * Agent associated to the MultiGuardIndexBESA.
     * 
     * @return MultiGuardIndexBESA associated agent.
     */
    public AgentBESA getAgent() {
        return getMultiGuardBESA().getAgent();
    }

    /**
     * Returns the ack associated guard.
     * 
     * @return Guard associated to ack.
     */
    public MultiGuardBESA getMultiGuardBESA() {
        return multiGuardBESA;
    }

    /**
     * Sets the MultiGuardBESA associated.
     * 
     * @param multiGuardBESA The MultiGuardBESA.
     */
    private void setMultiGuardBESA(MultiGuardBESA multiGuardBESA) {
        this.multiGuardBESA = multiGuardBESA;
    }
}
