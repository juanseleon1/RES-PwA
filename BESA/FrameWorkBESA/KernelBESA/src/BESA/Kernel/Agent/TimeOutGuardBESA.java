/*
 * @(#)TimeOutGuardBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Util.TimeOutData;

/**
 * This class represents
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class TimeOutGuardBESA extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        if (event.getData() instanceof TimeOutData) {
            this.funcTimeOutExecGuard(event);
        } else {
            this.funcNormalExecGuard(event);
        }
    }

    /**
     * Initializes the thread of the object TimeOutThread
     */
    public synchronized void startTimeOut(long timeOutTime) {
        this.getAgent().getChannel().findPort(this).startTimeOut(timeOutTime);
    }

    /**
     *
     */
    public synchronized void stopTimeOut() {
        this.getAgent().getChannel().findPort(this).stopTimeOut();
    }

    /**
     *
     * @param event
     */
    public abstract void funcNormalExecGuard(EventBESA event);

    /**
     *
     * @param event
     */
    public abstract void funcTimeOutExecGuard(EventBESA event);
}
