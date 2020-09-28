/*
 * @(#)ChannelBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;

/**
 * This class represents a channel associated to a BESA agent.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
final public class ChannelBESA implements Runnable {

    /**
     * Port list binds to the agent chanel.
     */
    private ArrayList<PortBESA> ports;
    /**
     * Flag that indicates the chanel state.
     */
    private boolean waitlogicChannel;
    /**
     * References to agent.
     */
    private AgentBESA ag;
    /**
     * Barrier counter.
     */
    private int barrierCounter;
    /**
     * Allows to make the treatment of sent and received events.
     */
    private MBoxBESA mbox;
    /**
     * Flags that inidcates if the agent is moving.
     */
    private boolean mobile;

    /**
     * Initializes a newly created ChannelBESA object.
     * 
     * @param ag Agent to associate the channel.
     */
    public ChannelBESA(AgentBESA ag) {
        this.ag = ag;
        this.mbox = new MBoxBESA();
        this.ports = new ArrayList<PortBESA>();
        this.waitlogicChannel = false;
    }

    /**
     * Represents the execution thread of the channel.
     */
    @Override
    final public void run() {
        ReportBESA.trace("Starts the channel thread of the agent: " + ag.getAlias());
        //--------------------------------------------------------------------//
        // Makes the setup of the agent structure.                            //
        //--------------------------------------------------------------------//
        try {
            ag.init();
        } catch (KernelAgentExceptionBESA ex) {
            ReportBESA.error(ex.toString());
            ex.printStackTrace();
        }
        ag.setupAgent();
        //--------------------------------------------------------------------//
        // Starts the behaviors threads.                                      //  
        //--------------------------------------------------------------------//
        try {
            ag.startBehaviors();
        } catch (KernelAgentExceptionBESA ex) {
            ReportBESA.error(ex);
            ex.printStackTrace();
        }
        //--------------------------------------------------------------------//
        // The agent is ready for work, then sends the signal for start the   // 
        // agent contructor.                                                  //
        //--------------------------------------------------------------------//        
        this.ag.signal();
        ReportBESA.trace("After of the signal to agent: " + ag.getAlias());
        //--------------------------------------------------------------------//
        // Principal cycle of the event handler.                              //
        //--------------------------------------------------------------------//        
        while (ag.isAlive().booleanValue()) {
            if (ag.isAlive().booleanValue()) {                                  //For synchronized access. 
                ReportBESA.trace("Before of receive events onto agent's channel: " + ag.getAlias());               
                //------------------------------------------------------------//
                // Waits the reception of a event.                            // 
                //------------------------------------------------------------//
                EventBESA ev = null;
                try {
                    ev = mbox.receiveEvent();
                } catch (KernelAgentExceptionBESA ex) {
                    ReportBESA.error(ex);
                    ex.printStackTrace();
                }
                ReportBESA.trace("After of receive events onto agent's channel: " + ag.getAlias() + ", with event: " + ev.getStamp());
                //------------------------------------------------------------//
                // Checks the logic state of the agent. If the event is of    //
                // kill the agent or the channel is blocked, then does not    //
                // process.                                                   //
                //------------------------------------------------------------//                
                if (this.waitlogicChannel == false || ev.getType().equalsIgnoreCase("KILL_BESA_AGENT")) {
                    this.processEvent(ev);
                }
            }
        } // End while alive.
        //--------------------------------------------------------------------//
        // Closes the agent and frees resources.                              //
        //--------------------------------------------------------------------//
        ag.shutdownAgent();
        try {
            ag.getChannel().signalBehBarrier();
        } catch (KernelAgentExceptionBESA ex) {
            ReportBESA.error(ex.toString());
            ex.printStackTrace();
        }
        ag = null;
    }

    /**
     * Looks for the associated port a guard.
     * 
     * @param guard Guard on who the port will look for. 
     * @return The found PortBESA.
     */
    final protected PortBESA findPort(GuardBESA guard) {
        //--------------------------------------------------------------------//
        // Looks up the associated port with the guard.                       //
        //--------------------------------------------------------------------//
        for (int i = 0; i < ports.size(); ++i) {
            PortBESA iport = (PortBESA) ports.get(i);
            GuardBESA iguard = (GuardBESA) iport.getGuard();
            if (iguard.equals(guard)) {                                         //Checks if the guard correspond.
                return iport;
            }
        }
        return null;                                                            //Does not find correspond.
    }

    /**
     * Creates a port associated to the guard and inserts it in the ports
     * vector. Verifies that a port for this guard has not been created 
     * previously.
     * 
     * @param guard Guard to which the created port will be associated.
     * @return The created port.
     */
    final protected PortBESA addPort(GuardBESA guard) {
        //--------------------------------------------------------------------//
        // Creates the associated port to the guard.                          //
        //--------------------------------------------------------------------//
        PortBESA port = this.findPort(guard.getEvType());
        if (port == null) {                                                     //Checks that there are not port for guard.
            port = new PortBESA(guard);
            ports.add(port);
            return port;
        } else {
            return null;                                                        //Only one port can be associated to one guard.
        }
    }

    /**
     * Eliminates a guard associated port. In order to eliminate the port first
     * verifies that has been created a port for this guard and finally is made
     * the elimination of the port. 
     * 
     * @param guard Guard associated to the port. 
     * @return true if the port is removed; false otherwise.
     */
    final protected boolean removePort(GuardBESA guard) {
        //--------------------------------------------------------------------//
        // Finds the associated port to the guard and deletes the port.       //
        //--------------------------------------------------------------------//
        PortBESA port = this.findPort(guard);
        if (port != null) {
            if (port.getBehaviors().size() == 0) {
                port.getGuard().finalize();
                ports.remove(port);
                port = null;
            } else {
                return false;                                                   //Return false because the port is bound to one behavior.
            }
        }
        return true;
    }

    /**
     * Eliminates the behavior of the ports in which it appears associate.
     * 
     * @param beh The behavior to refer.
     */
    final protected void purgePorts(BehaviorBESA beh) {
        //--------------------------------------------------------------------//
        // Unbind the port with behaviors.                                    //
        //--------------------------------------------------------------------//
        for (int i = 0; i < ports.size(); ++i) {
            PortBESA iport = (PortBESA) ports.get(i);
            ArrayList ibehaviors = iport.getBehaviors();
            ibehaviors.remove(beh);
            this.removePort(iport.getGuard());
        }

    }

    /**
     * Makes the processing of an BESA event. For this the event to be
     * processed is transferred to the corresponding port, and is invoked to
     * tryGuard to try to trigger the associated guard.
     * 
     * @param ev The event to be processed.
     * @return true if the event is correctly processed and is been transfered
     * to the correct port; false if the event doesn't have an associated port.
     */
    final private boolean processEvent(EventBESA ev) {
        PortBESA port = this.findPort(ev.getType());                            //Finds the associated port to the type event.
        if (port != null) {                                                     //Checks if found port.
            ev.setGuard(port.getGuard());                                       //Binds guard implementation.
            if (ev.getSource() != EventBESA.EV_SOURCE_BESA) {                   //Checks the event source.
                port.putEventInQueue(ev);
            }
            port.tryGuard();                                                    //Tries fire the associated guard to the type event.
            return true;
        } else {
            return false;                                                       //Did not find port.
        }
    }

    /**
     * Obtains the listing of ports associated to a channel.
     * 
     * @return The list of ports.
     */
    final protected ArrayList getPorts() {
        return ports;
    }

    /**
     * Returns the messages box associated to the channel.
     * 
     * @return The MBox associated. 
     */
    final protected MBoxBESA getMbox() {
        return mbox;
    }

    /**
     * Looks for the associated port to an event related by its guard.
     * 
     * @param evType Event of reference for the search.. 
     * @return The found PortBESA.
     */
    final protected PortBESA findPort(String evType) {
        //--------------------------------------------------------------------//
        // Finds the associated port to the type event.                       //
        //--------------------------------------------------------------------//
        for (int i = 0; i < ports.size(); ++i) {
            PortBESA iport = (PortBESA) ports.get(i);
            GuardBESA iguard = (GuardBESA) iport.getGuard();
            if (iguard.getEvType().equals(evType)) {                            //Checks the types.
                return iport;
            }
        }
        return null;
    }

    /**
     * Initializes the barrier counter of the behavior.
     * 
     * @param counterValue Value to initialize the barrier counter. 
     */
    public synchronized void initBehBarrier(int counterValue) {
        this.barrierCounter = counterValue;
    }

    /**
     * Blocks the behavior while the barrier counter indicates it. 
     */
    public synchronized void waitBehBarrier() throws KernelAgentExceptionBESA {
        while (barrierCounter > 0) {
            ReportBESA.trace("Before of barrierCounter: " + this.barrierCounter);
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ReportBESA.error("Happened an error on wait behavior barrier: " + ie.toString());
                throw new KernelAgentExceptionBESA("Happened an error on wait behavior barrier: " + ie.toString());
            }
            ReportBESA.trace("After of barrierCounter: " + this.barrierCounter);
        }
    }

    /**
     * Sends a notification of the behavior availability.
     */
    public synchronized void signalBehBarrier() throws KernelAgentExceptionBESA {
        try {
            ReportBESA.trace("Before of barrierCounter notify: " + this.barrierCounter);
            this.barrierCounter--;
            this.notify();
            ReportBESA.trace("Before of barrierCounter notify: " + this.barrierCounter);
        } catch (Exception e) {
            ReportBESA.error("Happened an error on signal behavior barrier: " + e.toString());
            throw new KernelAgentExceptionBESA("Happened an error on signal behavior barrier: " + e.toString());
        }
    }

    /**
     * Logical lock of the agent so that during the phase of mobility or when
     * died, is generated an incapacity to process events directed to the agent.
     */
    public synchronized void waitlogicChannel() throws KernelAgentExceptionBESA {
        try {
            this.waitlogicChannel = true;
        } catch (Exception e) {
            ReportBESA.error("Happened an error on wait logic channel: " + e.toString());
            throw new KernelAgentExceptionBESA("Happened an error on wait logic channel: " + e.toString());
        }
    }

    /**
     * Obtains the barrier counter value.
     * 
     * @return The barrier counter value.
     */
    public int getbarrierCounter() {
        return barrierCounter;
    }

    /**
     * @return Returns the mobility value.
     */
    public boolean isMobile() {
        return mobile;
    }
}
