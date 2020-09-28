/*
 * @(#)AgHandlerBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.System.Directory;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AGENTSTATE;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import java.io.Serializable;

/**
 * This class represents
 * Clase que se usa para ver cual es el verdadero handler.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class AgHandlerBESA implements Serializable {

    /**
     * 
     */
    protected AGENTSTATE state;
    /**
     *
     */
    protected String agId;
    /**
     *
     */
    protected String alias;
    /**
     *
     */
    protected AgentBESA ag;

    /**
     * 
     */
    public AgHandlerBESA() {
        state = AGENTSTATE.CREATE;
    }

    /**
     * 
     * @param agId
     * @param alias 
     */
    public AgHandlerBESA(String agId, String alias) {
        state = AGENTSTATE.CREATE;
        this.agId = agId;
        this.alias = alias;
    }

    /**
     * 
     * @param agId
     * @param alias
     * @param ag 
     */
    public AgHandlerBESA(String agId, String alias, AgentBESA ag) {
        state = AGENTSTATE.CREATE;
        this.agId = agId;
        this.alias = alias;
        this.ag = ag;
    }

    /**
     * 
     * @param ev
     * @throws ExceptionBESA 
     */
    public abstract void sendEvent(EventBESA ev) throws ExceptionBESA;

    /**
     * 
     * @throws SystemDirectoryExceptionBESA 
     */
    public void notificar() throws SystemDirectoryExceptionBESA {
        //this.notify();
        try {
            this.notify();
        } catch (Exception e) {
            ReportBESA.error("Happened an error on the notify: " + e.toString());
            throw new SystemDirectoryExceptionBESA("Happened an error on the notify: " + e.toString());
        }

    }

    /**
     * 
     * @throws SystemDirectoryExceptionBESA 
     */
    public synchronized void notifyMove() throws SystemDirectoryExceptionBESA {
        try {
            this.notify();
        } catch (Exception e) {
            ReportBESA.error("Happened an error on the notify all of move agent: " + e.toString());
            throw new SystemDirectoryExceptionBESA("Happened an error on the notify all move agent: " + e.toString());
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getAgId() {
        return agId;
    }

    /**
     * 
     * @return 
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 
     * @return 
     */
    public AgentBESA getAg() {
        return ag;
    }

    /**
     * 
     * @param state 
     */
    public void setState(AGENTSTATE state) {
        this.state = state;
    }

    /**
     * 
     * @return 
     */
    public AGENTSTATE getState() {
        return state;
    }
}
