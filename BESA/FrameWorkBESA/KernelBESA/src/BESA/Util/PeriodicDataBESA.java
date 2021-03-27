/*
 * @(#)PeriodicDataBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Util;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 * This class represents the configuration data for the periodic thread of one
 * periodic guard.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class PeriodicDataBESA extends DataBESA {

    /**
     * Defines the periodic time.
     */
    private long periodicTime;
    /**
     * Defines the call or commad that determinies the periodic thread state.
     * The states could be: START, STOP or SUSPEND.
     */
    private String call;

    /**
     * Crrates a new instance of the data type for configuration of the
     * periodic thread.
     *
     * @param periodicTime Ttime out between periodics.
     * @param call Command that defines the state the periodic thread. The
     * states could be: START, STOP or SUSPEND.
     */
    public PeriodicDataBESA(long periodicTime, String call) {
        this.periodicTime = periodicTime;
        this.call = call;
    }

    /**
     * Creates a new instance of the data type for configuration of the
     * periodic thread as wiht defaults values.
     *
     * @param call Command that defines the state the periodic thread. The
     * states could be: START, STOP or SUSPEND.
     */
    public PeriodicDataBESA(String call) {
        this.call = call;
    }

    /**
     * Gets the call menssage.
     * @return
     */
    public String getCall() {
        return call;
    }

    /**
     * Sets the menssage call.
     * @param call Menssage.
     */
    public void setCall(String call) {
        this.call = call;
    }

    /**
     * Gets the periodic time.
     *
     * @return Gets the periodic time.
     */
    public long getPeriodicTime() {
        return periodicTime;
    }

    /**
     * Sets the periodic time.
     *
     * @param periodicTime Periodic time.
     */
    public void setPeriodicTime(long periodicTime) {
        this.periodicTime = periodicTime;
    }
}
