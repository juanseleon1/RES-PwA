/*
 * @(#)GuardBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.EventBESA;

/**
 * This class represents a guard of an agent. A guard is made up of an evaluation function,
 * that indicates if the guard is or not activated, and a function that executes
 * in the associated behavior that makes some of the 3 following operations:
 * 
 * 1. Change the internal agent state.
 * 2. Modify the environment.
 * 3. Send events to another agents.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class GuardBESA {

    /**
     * Type of the event, that corresponds to the class name.
     */
    protected String evType;
    /**
     * Agent associated to the guard.
     */
    protected AgentBESA agent;
    /**
     * Port associated to the guard.
     */
    protected PortBESA port;

    /**
     * Agregado por Julian Angel
     * solo para hacer pruebas
     */
    public GuardBESA() {
        //TODO Verificar.
    }

    /**
     * Returns the type of the event, that corresponds to the class name.
     * 
     * @return String that represents the event type.
     */
    public String getEvType() {
        return evType;
    }

    /**
     * Eliminates initial binds of the guard.
     */
    @Override
    public void finalize() {
        getAgent().getState().unbindGuard(this);
    }

    /**
     * Evaluates if the guard must be or not executed by the behaviors.<BR><BR>
     * 
     * By default the function returns true, the guard is executed with the
     * occurrence of the event, if not desire this then is due to rewrite
     * this function in the subclass.
     * 
     * @param objEvalBool Internal agent state, used to verify the execution condition.
     * @return true if the guard must be executed; false otherwise.
     */
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    /** 
     * Conduct to execute when the guard initializes. The guard is executed
     * within a behavior to which it has been associated.<BR><BR>
     *  
     * The conduct depends on the data associated to the event.<BR><BR>
     * 
     * The conduct depends and/or can modify the agent state.
     * 
     * @param event Received event, contains the related data.
     */
    abstract public void funcExecGuard(EventBESA event);

    /**
     * Returns the port associated to the guard .
     * 
     * @return Port associated to the guard.
     */
    public PortBESA getPort() {
        return port;
    }

    /**
     * Returns the associated agent to the guard.
     * 
     * @return Associated agent.
     */
    public AgentBESA getAgent() {
        return agent;
    }

    /**
     * Associates an agent to the guard.
     * 
     * @param agent The agent to be associated.
     */
    private void setAgent(AgentBESA agent) {
        this.agent = agent;
    }
}
