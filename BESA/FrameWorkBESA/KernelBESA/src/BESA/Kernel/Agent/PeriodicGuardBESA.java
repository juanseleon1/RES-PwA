/*
 * @(#)PeriodicGuardBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PortBESA.PeriodicThread;
import BESA.Util.PeriodicDataBESA;

/**
 * This class implements the call periodic of the a periodic guard.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public abstract class PeriodicGuardBESA extends GuardBESA {
    
    //========================================================================//
    //                              CONSTANTS                                 //
    //========================================================================//
    /**
     * Start periodic call menssage.
     */
    public static final String START_PERIODIC_CALL = "START_PERIODIC_CALL";
    /**
     * Suspend periodic call menssage.
     */
    public static final String SUSPEND_PERIODIC_CALL = "SUSPEND_PERIODIC_CALL";
    /**
     * Periodic call menssage.
     */
    public static final String PERIODIC_CALL = "PERIODIC_CALL";
    /**
     * Stop call menssage.
     */
    public static final String STOP_CALL = "STOP_CALL";
    /**
     * Default value periodic time.
     */
    private final long DEFAULT_PERIODIC_TIME = 1000;
    //========================================================================//
    //                              VARIABLES                                 //
    //========================================================================//
    /**
     * Periodic time.
     */
    private long periodicTime;
    /**
     * Time out before the starts the periodic thread.
     */
    private long delayTime;
    /**
     * Periodic thread.
     */
    private PeriodicThread periodicThread;

    /**
     * Creates a new instance.
     *
     * @param ag References to current agent.
     */
    public PeriodicGuardBESA() {
        super();
        this.periodicTime = DEFAULT_PERIODIC_TIME;                              //Sets the defaul value.
        this.delayTime = 1;
        this.periodicThread = null;
    }

    /**
     * Starts the call periodic of this guard.
     */
    public void startPeriodicCall() {
        if (periodicThread == null) {                                           //Checks if the periodic thread is null.
            periodicThread = this.getAgent().getChannel().findPort(this).createPeriodicThread(periodicTime, delayTime);//Creates a new instance for the periodic thread.
        }
        periodicThread.startThread();
    }

    /**
     * Sets the suspend state the periodic thread.
     */
    public void suspendPeriodicCall() {
        periodicThread.suspendThread();
    }

    /**
     * Sets the stop state the periodic thread.
     */
    public void stopPeriodicCall() {
        periodicThread.stopThread();
    }

    /**
     * (non-Javadoc)
     * @see BESA.Agent.GuardBESA#funcExecGuard(BESA.Agent.Event.EventBESA)
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        //--------------------------------------------------------------------//
        // Gets the type the event and checks if the type is a periodic call. //
        // If it is a periodic call then execuite the reaction developer for  //
        // user front the peridic call                                        //
        //--------------------------------------------------------------------//
        if (event.getType().equalsIgnoreCase(PERIODIC_CALL)) {
            this.funcPeriodicExecGuard(event);
            return;
        }
        //--------------------------------------------------------------------//
        // Gets event and checks if contains a configuration menssage the     //
        // periodic thread.                                                   //
        //--------------------------------------------------------------------//
        if (event.getData() instanceof PeriodicDataBESA) {
            PeriodicDataBESA periodicData = (PeriodicDataBESA) event.getData(); //Gets the configuration data from the event.
            String call = periodicData.getCall();                               //Gets the menssage or command.
            if (call.equalsIgnoreCase(START_PERIODIC_CALL)) {                   //Checks if the menssage is start.
                this.periodicTime = periodicData.getPeriodicTime();             //Sets the periodic time.
                this.delayTime = 1;                                             //Sets the delay time.
                this.startPeriodicCall();
                return;
            }
            if (call.equalsIgnoreCase(SUSPEND_PERIODIC_CALL)) {                 //Checks if the menssage is suspend.
                this.suspendPeriodicCall();
                return;
            }
            if (call.equalsIgnoreCase(STOP_CALL)) {                             //Checks if the menssage is stop.
                this.stopPeriodicCall();
                return;
            }
        }
    }

    /**
     * Abstract method where the user implements the reaction to the periodic
     * call.
     *
     * @param event Periodic call which contain information of the progres the
     * periodic thread.
     */
    public abstract void funcPeriodicExecGuard(EventBESA event);

    /**
     * Gets the periodic time.
     * @return Periodic time.
     */
    public long getPeriodicTime() {
        return periodicTime;
    }

    /**
     * Sets the periodic time.
     * @param periodicTime Periodic time.
     */
    public void setPeriodicTime(long periodicTime) {
        this.periodicTime = periodicTime;
    }

    /**
     * Gets the delay time.
     * @return Delay time.
     */
    public long getDelayTime() {
        return delayTime;
    }

    /**
     * Sets the delay time.
     *
     * @param delayTime The delayTime to set.
     */
    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }
}
