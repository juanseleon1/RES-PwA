/*
 * @(#)MBoxBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;

/**
 * This class allows to make the treatment of sent and received events.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class MBoxBESA {

    /**
     * Events queue.
     */
    private QueueBESA queue;

    /**
     * Initializes a newly created MBox object.
     */
    public MBoxBESA() {
        queue = new QueueBESA();
    }

    /**
     * Adds an event to the queue and notifies to wake up the receiver.
     * 
     * @param ev The event to send. 
     */
    final public synchronized void sendEvent(EventBESA ev) {
        queue.add(ev);                                                          //Adds the event to the queue.
        this.notify();                                                          //Notify to event receptor (recieveEvent method).
    }

    /**
     * Receives the first element in the message queue.
     * 
     * @return The received element. 
     */
    final protected synchronized EventBESA receiveEvent() throws KernelAgentExceptionBESA {
        while (queue.isEmpty()) {                                               //Checks if there are events into queue.
            try {
                ReportBESA.trace("Before of wait");
                this.wait();                                                    //Puts the the thread in wait state.
                ReportBESA.trace("After of wait");
            } catch (InterruptedException ie) {
                ReportBESA.error("Happened an error on wait time of receive event: " + ie.toString());
                throw new KernelAgentExceptionBESA("Happened an error on wait time of receive event: " + ie.toString());
            }
        }
        return (EventBESA) queue.first();                                       //Gets the firsth event.
    }
}
