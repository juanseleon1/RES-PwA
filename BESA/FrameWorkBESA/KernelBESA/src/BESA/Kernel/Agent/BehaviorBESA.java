/*
 * @(#)BehaviorBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;

/**
 * This class represents a behavior of a BESA agent, to which a series of guards
 * is associated.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class BehaviorBESA implements Runnable {

    /**
     * Default value for periodic time of a periodic behavior.
     */
    public static final int PERIODIC = 0;
    /**
     * Default value for timeout of a timeout behavior.
     */
    public static final int TIMEOUT = 1;
    /**
     * Message that informs when an agent was killed.
     */
    public static String KILL_EVENT = "KILL_BESA_AGENT";
    /**
     * Priority queue BESA.
     */
    private PriorityQueueBESA queue;
    /**
     * Guard list.
     */
    protected ArrayList<GuardBESA> guards;
    /**
     * References to agent.
     */
    private AgentBESA agent;
    /**
     * Flag that indicates if the behavior is alive.
     */
    private Boolean alive = Boolean.FALSE;
    /**
     * Behavior thread.
     */
    private Thread thread;
    /**
     * Link to event BESA.
     */
    private EventBESA lnkEventBESA;
    /**
     * Class name.
     */
    private String name;
    /**
     * Flag that indicates if the behavior is periodic.
     */
    private boolean periodic;
    /**
     * Sets the delay time.
     */
    private long milliseconds;
    /**
     * TODO Verificar.
     */
    private int type;
    /**
     * Behavior name.
     */
    private String behaviorName;

    /**
     * Constructs a new behavior. When creating other constructors always must
     * be called this.
     *
     * @param ag Agent to whom belongs the behavior.
     */
    public BehaviorBESA(AgentBESA ag, String behaviorName) {
        this.behaviorName = behaviorName;
        this.periodic = false;
        this.setAgent(ag);
        this.name = this.getClass().getName();
        this.queue = new PriorityQueueBESA();
        this.guards = new ArrayList<GuardBESA>();
        this.setAlive();
        ag.registerBehavior(this);
    }

    /**
     * Constructs a new behavior. When creating other constructors always must
     * be called this.
     *
     * @param ag Agent to whom belongs the behavior.
     */
    public BehaviorBESA(AgentBESA ag) {
        this.periodic = false;
        this.setAgent(ag);
        this.name = this.getClass().getName();
        this.queue = new PriorityQueueBESA();
        this.guards = new ArrayList<GuardBESA>();
        this.setAlive();
        ag.registerBehavior(this);
    }

    /**
     * Constructs a new behavior.
     *
     * @param ag Agent to whom belongs the behavior.
     * @param periodic Value that it indicates if the behavior is periodic or
     * no.
     * @param milliseconds Value in milliseconds that it indicates or the time
     * out or the periodicity.
     * @param type Periodic or with time out
     */
    public BehaviorBESA(AgentBESA ag, boolean periodic, long milliseconds, int type) {
        this.periodic = periodic;
        this.milliseconds = milliseconds;
        this.type = type;
        this.setAgent(ag);
        this.name = this.getClass().getName();
        this.queue = new PriorityQueueBESA();
        this.guards = new ArrayList<GuardBESA>();
        this.setAlive();
        ag.registerBehavior(this);
    }

    /**
     * Kills the behavior.
     */
    final public void kill() throws KernelAgentExceptionBESA {
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(this.milliseconds);
            } catch (Exception e) {
                ReportBESA.error("Happened an error on kill: " + e.getMessage());
                throw new KernelAgentExceptionBESA("Happened an error on kill: " + e.getMessage());
            }
        }
        this.resetAlive();                                                      //Generates the bug output.
        //--------------------------------------------------------------------//
        // Creates the higth priority kill event and sents to behavior.       //
        //--------------------------------------------------------------------//
        String evType = KILL_EVENT;
        try {
            EventBESA ev = new EventBESA(evType, getAgent().getAdmLocal().getPasswd());
            this.sendEvent(ev);
        } catch (Exception e) {
            ReportBESA.error("Couldn't send the internal kill event: " + e.toString());
            throw new KernelAgentExceptionBESA("Couldn't send the internal kill event: " + e.toString());
        }
    }

    /**
     * Creates the timeout event.
     * 
     * @return Timeout event as event BESA.
     */
    final private EventBESA timeoutEvent() throws KernelAgentExceptionBESA {
        String evType = TimeOutGuardBESA.class.getName();
        EventBESA ev = null;
        try {
            ev = new EventBESA(evType, getAgent().getAdmLocal().getPasswd());
        } catch (Exception e) {
            ReportBESA.error("[BehaviorBESA:timeoutEvent]: Couldn't create time-out event: " + e.toString());
            throw new KernelAgentExceptionBESA("Couldn't create time-out event: " + e.toString());
        }
        return ev;
    }

    /**
     * Represents the execution thread of the behavior.
     */
    final public void run() {
        ReportBESA.trace("Starts the behavior thread: " + this.behaviorName + ",  of the agent: " + getAgent().getAlias() + ".");
        //--------------------------------------------------------------------//
        // Sends a notification of the behavior availability.                 //
        //--------------------------------------------------------------------//        
        try {
            this.getAgent().getChannel().signalBehBarrier();
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
            ex.printStackTrace();
        }
        //--------------------------------------------------------------------//
        // Execution cycle of the behavior.                                   //
        //--------------------------------------------------------------------//
        while (this.isAlive().booleanValue()) {            
            ReportBESA.trace("Before receive events of the behavior: " + this.name + ".");
            //----------------------------------------------------------------//
            // Waits the reception of the an event.                           //    
            //----------------------------------------------------------------//
            EventBESA ev = null;
            try {
                ev = this.receiveEvent();
            } catch (KernelAgentExceptionBESA ex) {
                ReportBESA.error("TODO" + ex.toString());
                ex.printStackTrace();
            }
            ReportBESA.trace("After of receive events of the behavior: " + this.name + ".");
            if (ev.getType().equalsIgnoreCase("KILL_BESA_AGENT")) {             //Checks if the event is of kill the agent.
                ReportBESA.trace("Starts the behavior: " + this.name + ", with event: " + ev.getStamp());
                this.resetAlive();                                              //Sets the exit of the behavior cycle.
            } else {                                                            //Is a normal event.
                ReportBESA.trace("Starts the behavior: " + this.name + ", with event: " + ev.getStamp() + ", with type: " + ev.getGuard().getEvType());
                ev.getGuard().funcExecGuard(ev);                                //Executes the function of the guard associate to event.
            }
        } // End while alive.
        //--------------------------------------------------------------------//
        // Closes the behavior and frees resources.                           //
        //--------------------------------------------------------------------//
        ReportBESA.trace("Closes behavior: " + this.name);
        // Invoca cierre de Guardas
        this.getAgent().getChannel().purgePorts(this);                          //Closes the ports.
        try {            
            for (int i = 0; i < guards.size(); ++i) {
                GuardBESA igua = (GuardBESA) guards.get(i);
                ReportBESA.trace("Closes guard: " + igua);
                guards.remove(i);                                               //Unbinds the guards.
            }
        } catch (Exception e) {
            ReportBESA.error("Couldn't close the behavior guard: " + this.name);
            try {
                throw new KernelAgentExceptionBESA("Couldn't close the behavior guard: " + e.toString());
            } catch (KernelAgentExceptionBESA ex) {
                ex.printStackTrace();
            }
        }
        //--------------------------------------------------------------------//
        // Sends a notification of the behavior is not availability.          //
        //--------------------------------------------------------------------//
        try {
            this.getAgent().getChannel().signalBehBarrier();
        } catch (KernelAgentExceptionBESA ex) {
            ReportBESA.error(ex);
            ex.printStackTrace();
        }
    }

    /**
     * Adds a event to the queue and notify the receptor to wake up. It doesn't 
     * have to be called by the user.
     *
     * @param ev Event to send.
     */
    final public synchronized void sendEvent(EventBESA ev) {
        queue.add(ev, ev.getPriority());
        this.notify();
    }

    /**
     * Receives a event.
     * 
     * @return Event Received event.
     */
    final private synchronized EventBESA receiveEvent() throws KernelAgentExceptionBESA {
        //--------------------------------------------------------------------//
        // handles the event of periodic way.                                 //
        //--------------------------------------------------------------------//        
        if (this.periodic) {
            if (this.type == BehaviorBESA.PERIODIC) {
                while (queue.isEmpty()) {
                    try {
                        Thread.sleep(this.milliseconds);
                    } catch (Exception e) {
                        ReportBESA.error("Happened an error on behavior periodic: " + e.getMessage());
                        throw new KernelAgentExceptionBESA("Happened an error on behavior periodic: " + e.getMessage());
                    }
                }
                return (EventBESA) queue.first();

            } else if (this.type == BehaviorBESA.TIMEOUT) {
                try {
                    Thread.sleep(this.milliseconds);
                } catch (Exception e) {
                    ReportBESA.error("Happened an error on behavior time-out: " + e.getMessage());
                    throw new KernelAgentExceptionBESA("Happened an error on behavior time-out: " + e.getMessage());
                }
                if (queue.isEmpty()) {
                    return this.timeoutEvent();
                } else {
                    return (EventBESA) queue.first();
                }
            } else {
                String evType = BehaviorBESA.KILL_EVENT;
                try {
                    return new EventBESA(evType, getAgent().getAdmLocal().getPasswd());
                } catch (Exception e) {
                    ReportBESA.error("Couldn't create the event of type \"BehaviorBESA.KILL_EVENT\":" + e.getMessage());
                    throw new KernelAgentExceptionBESA("Couldn't create the event of type \"BehaviorBESA.KILL_EVENT\":" + e.getMessage());
                }
            }
        } else {
            //----------------------------------------------------------------//
            // Handles the event of normal way.                               //
            //----------------------------------------------------------------//
            while (queue.isEmpty()) {                                           //Checks if there are events, for input on wait.            
                try {
                    this.wait();
                } catch (InterruptedException ie) {
                    ReportBESA.error("Happened an error on wait time of the event queue: " + ie.toString());
                    throw new KernelAgentExceptionBESA("Happened an error on wait time of the event queue: " + ie.toString());
                }
            }
            return (EventBESA) queue.first();                                   //Gets the first event of the queue.
        }
    }

    /**
     * Indicates if the behavior is alive or not.
     * 
     * @return true if the behavior is alive; false otherwise. 
     */
    private synchronized Boolean isAlive() {
        return alive;
    }

    /**     
     * Indicates that the behavior is alive. 
     */
    private synchronized void setAlive() {
        this.alive = Boolean.TRUE;
    }

    /**
     * Indicates that the behavior isn't alive. 
     */
    private synchronized void resetAlive() {
        this.alive = Boolean.FALSE;
    }

    /**
     * Returns the behavior associated thread.
     *
     * @return Associated thread.
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Establishes the thread associated to the behavior.
     *
     * @param thread Thread to associate.
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * Registers a guard in the behavior. It doesn't have to be called by 
     * the user.
     * 	 
     * @param evType A String that represents the event type.
     */
    protected void registerGuard(String evType) {
        guards.add(getAgent().registerGuard(this, evType));
    }

    /**
     * Obtains the class name of this behavior.
     *
     * @return Behavior name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the agent to whom the behavior belongs.
     *
     * @return Propietary agent of the behavior.
     */
    public AgentBESA getAgent() {
        return agent;
    }

    /**
     * Sets the agent associated to the behavior.
     * 
     * @param agent Agent to associate. 
     */
    private void setAgent(AgentBESA agent) {
        this.agent = agent;
    }

    /**
     * Obtains the lnkEventBESA that represents an BESA event.
     * 
     * @return Returns the lnkEventBESA.
     */
    public EventBESA getLnkEventBESA() {
        return lnkEventBESA;
    }

    /**
     * Gets the behavior name.
     * 
     * @return Behavior name.
     */
    public String getBehaviorName() {
        return behaviorName;
    }
}
