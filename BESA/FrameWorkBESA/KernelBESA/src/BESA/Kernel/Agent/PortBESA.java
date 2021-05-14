/*
 * @(#)PortBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import BESA.Util.TimeOutData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a port BESA, which makes treatment of behaviors,
 * guards and events.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class PortBESA {

    /**
     * Events queue.
     */
    private QueueBESA events;
    /**
     * Behaviors list associated to the created PortBESA object.
     */
    private ArrayList<BehaviorBESA> behaviors;
    /**
     * Guard associated to the created PortBESA object.
     */
    private GuardBESA guard;
    /**
     * Flag indicating if the timeout mechanism is activated.
     */
    private boolean timeOutActive = false;
    /**
     * Timeout Thread.
     */
    private TimeOutThread timeOutThread;
    /**
     * Timeout event tipe.
     */
    private static String TIMEOUT_EVENT_TYPE = "__TimeOutEventTypeBESA__";
    /**
     * Collection of threads synchronized in order to implement timeout and periodic guard.
     */
    private List<PeriodicThread> periodicThreads = Collections.synchronizedList(new ArrayList<PeriodicThread>());

    /**
     * Creates a new instance of PortBESA associated to a guard.
     * 
     * @param guard Guard associated to the created PortBESA object. 
     */
    public PortBESA(GuardBESA guard) {
        this.guard = guard;
        this.behaviors = new ArrayList<BehaviorBESA>();                         //Initializes the behavior storage.
        events = new QueueBESA();                                               //Initializes the queue storage.
    }

    /**
     * Associates a behavior to the guard of the port.
     * 
     * @param behavior Behavior to associate. 
     */
    final protected void bindBehavior(BehaviorBESA behavior) {
        behaviors.add(behavior);
    }

    /**
     * Eliminates the associated behavior to the guard of the port.
     * 
     * @param behavior Behavior to eliminate.
     */
    final protected void unbindBehavior(BehaviorBESA behavior) {
        BehaviorBESA tmp;                                                       //Temporary variable.
        for (int i = 0; i < behaviors.size(); i++) {                            //Starts for. For each behavior.
            tmp = (BehaviorBESA) behaviors.get(i);
            if (tmp == behavior) {                                              //Starts if. That is equal to behavior to eliminate.
                behaviors.remove(i);                                            //Eliminates the associated.
            }                                                                   //End if.
        }                                                                       //End for.
    }

    /**
     * Puts an event in the internal queue of the port.
     * 
     * @param ev Event to add.
     */
    final protected void putEventInQueue(EventBESA ev) {
        events.add(ev);
    }

    /**
     * Verifies if there are events in the queue to be transferred to the
     * behaviors interested in these.
     */
    final public void tryGuard() {
        if (!events.isEmpty()) {                                                //Starts if, check for events in queue.
            if (guard.funcEvalBool(guard.getAgent().getState())) {              //Starts if, validates the Boolean function associated guard.
                this.stopTimeOut();                                             //Interrupts the thread of the object TimeOutThread.
                EventBESA ev = (EventBESA) events.first();
                if (getBehaviors().size() == 0) {
                    ReportBESA.warn("There aren't behaviors into port.");
                }
                for (int i = 0; i < getBehaviors().size(); ++i) {
                    BehaviorBESA behavior = (BehaviorBESA) getBehaviors().get(i);
                    behavior.sendEvent(ev);
                }
            }
        }
    }

    /**
     * Initializes a new TimeOutThread object with a wait time associated.
     *  
     * @param waitMSTime Specified wait time. 
     */
    public void createTimeOutThread() {
        this.timeOutThread = new TimeOutThread(this);
        this.timeOutThread.start();
    }

    /**
     * Interrupts the thread of the object TimeOutThread.
     */
    public synchronized void stopTimeOut() {
        if (this.isTimeOutActive()) {
            this.timeOutActive = false;
            if (!this.timeOutThread.isInterrupted()) {
                this.timeOutThread.interrupt();
            }
        }
    }

    /**
     * Initializes the thread of the object TimeOutThread
     */
    public synchronized void startTimeOut(long waitMSTime) {
        if (!this.isTimeOutActive()) {
            this.timeOutActive = true;
            this.timeOutThread.setWaitMSTime(waitMSTime);
            this.notify();
        }
    }

    /**
     * Locks the PortBESA object until TimeOut has been initialized.
     */
    private synchronized void waitInitTimeOut() {
        try {
            this.wait();
        } catch (Exception e) {
        }
    }

    /**
     * Returns the guard associated.
     * 
     * @return The guard.
     */
    public GuardBESA getGuard() {
        return guard;
    }

    /**
     * Returns the list of associated behaviors.
     * 
     * @return Returns the behaviors.
     */
    public ArrayList getBehaviors() {
        return behaviors;
    }

    /**
     * Sets a group of behaviors.
     * 
     * @param behaviors The behaviors list to set.
     */
    public void setBehaviors(ArrayList<BehaviorBESA> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * Create a new periodic thread with a wait time and a delay time associated.
     * 
     * @param waitMSTime Wait time of the periodic thread. 
     * @param delayTime Delay time of the periodic thread.
     * @return The PeriodicThread created object.
     */
    public PeriodicThread createPeriodicThread(long waitMSTime, long delayTime) {
        PeriodicThread pthd = new PeriodicThread(waitMSTime, delayTime, this);
        periodicThreads.add(pthd);
        pthd.start();
        return pthd;
    }

    /**
     * @return Returns the timeOutActive.
     */
    public synchronized boolean isTimeOutActive() {
        return timeOutActive;
    }

    /**
     * This class represents a periodic thread in the system. This class is
     * used when there are periodic guards or multiguards.
     *
     * @author  SIDRe - Pontificia Universidad Javeriana
     * @author  Takina  - Pontificia Universidad Javeriana
     * @version 2.0, 11/01/11
     * @since   JDK1.0
     */
    class PeriodicThread extends Thread {

        /**
         *
         */
        private long waitMSTime;
        /**
         *
         */
        private long delayTime;
        /**
         *
         */
        @SuppressWarnings("unused")
        private long timeStamp;
        /**
         *
         */
        @SuppressWarnings("unused")
        private PortBESA myPort;
        /**
         *
         */
        private int started = 0;
        /**
         *
         */
        public int THREAD_STOPPED = 0; // Hilo parado
        /**
         *
         */
        public int THREAD_STARTED = 1; // Hilo en ejecucion
        /**
         *
         */
        public int THREAD_SUSPENDED = 2; // Hilo suspendido

        /**
         * Constructs a new thread periodic.
         * 
         * @param waitMSTime Time to wait, in milliseconds. 
         * @param delayTime Dalay time.
         * @param port Associated port.
         */
        public PeriodicThread(long waitMSTime, long delayTime, PortBESA port) {
            this.waitMSTime = waitMSTime;
            this.delayTime = delayTime;
            this.myPort = port;
            this.started = THREAD_SUSPENDED;
        }

        /**
         * Initializes the thread.
         */
        public synchronized void startThread() {
            started = THREAD_STARTED;
            this.notify();
        }

        /**
         * Suspends the associated thread.
         */
        public void suspendThread() {
            started = THREAD_SUSPENDED;
        }

        /**
         * Stops the associated thread.
         */
        public synchronized void stopThread() {
            started = THREAD_STOPPED;
            if (!this.isInterrupted()) {
                this.interrupt();
            }
        }

        /**
         * Represents the execution of the periodic thread.
         */
        public void run() {

            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
            }
//			Obtengo la medida de tiempo a partir de la cual inicio
            this.timeStamp = System.currentTimeMillis();
            while (guard.getAgent().isAlive()) {
                try {
                    if (started == THREAD_SUSPENDED) {
                        // Si estoy iniciando apenas, bloqueo el hilo
                        // hasta que reciba el notify de startThread()
                        synchronized (this) {
                            this.wait();
                        }
                        this.timeStamp = System.currentTimeMillis();
                    }

                    // Espero el evento
                    synchronized (this) {
                        this.wait(waitMSTime);
                    }

                    // Si esta parado el hilo
                    if (started == THREAD_STOPPED) {
                        break;
                    }

                    // Envio el evento a todos los behavior
                    // Como esta el dise�o actual de BESA, solo hay un behavior
                    // registrado
                    EventBESA ev = new EventBESA(PeriodicGuardBESA.PERIODIC_CALL, null);
                    ev.setGuard(getGuard());
                    // Estos eventos deben tener la m�xima prioridad
                    // No seria frecuente que alguien cambiara la prioridad
                    ev.setPriority(EventBESA.EV_PRIORITY_MAX);

                    for (int i = 0; i < getBehaviors().size(); ++i) {
                        BehaviorBESA behavior = (BehaviorBESA) getBehaviors().get(i);
                        behavior.sendEvent(ev);
                    }

                } catch (InterruptedException e) {
                    ReportBESA.warn("The periodic thread was interrupted: " + e.toString());
                }
                // Finaliza la ejecuci�n si fue parado
                if (started == THREAD_STOPPED) {
                    break;
                }
            }
            ReportBESA.trace("Closes the thread periodic thread.");
        }

        /**
         * Obtains the value of the associated port.
         *
         * @return Returns the myPort value.
         */
        public PortBESA getMyPort() {
            return myPort;
        }

        /**
         * Returns the time stamp associated value.
         *
         * @return Returns the timeStamp.
         */
        public long getTimeStamp() {
            return this.timeStamp;
        }
    }

    /**
     * This class represents a time out thread in the system.
     *
     * @author  SIDRe - Pontificia Universidad Javeriana
     * @author  Takina  - Pontificia Universidad Javeriana
     * @version 2.0, 11/01/11
     * @since   JDK1.0
     */
    class TimeOutThread extends Thread {

        private boolean start;
        /**
         *
         */
        private long waitMSTime;
        /**
         *
         */
        private PortBESA myPort;

        /**
         * Initializes a newly created TimeOutThread object.
         *
         * @param waitMSTime Time to wait, in milliseconds.
         * @param port The associated port.
         */
        public TimeOutThread(PortBESA port) {
            this.waitMSTime = 1000;
            this.myPort = port;
            this.start = false;
        }

        /**
         * Represents the execution thread associated.
         */
        public void run() {
            while (guard.getAgent().isAlive()) {
                if (!this.start) {
                    this.myPort.waitInitTimeOut();
                    this.start = true;
                }
                do {
                    try {
                        Thread.sleep(this.waitMSTime);
                        EventBESA ev = new EventBESA(this.myPort.getGuard().getEvType(), new TimeOutData());
                        ev.setGuard(this.myPort.getGuard());
                        for (int i = 0; i < getBehaviors().size(); ++i) {
                            BehaviorBESA behavior = (BehaviorBESA) getBehaviors().get(i);
                            behavior.sendEvent(ev);
                        }
                    } catch (Exception e) {
                    }
                } while (this.myPort.isTimeOutActive());
            }
        }

        /**
         * 
         * @param waitMSTime
         */
        public void setWaitMSTime(long waitMSTime) {
            this.waitMSTime = waitMSTime;
            this.start = false;
        }
    }
}
