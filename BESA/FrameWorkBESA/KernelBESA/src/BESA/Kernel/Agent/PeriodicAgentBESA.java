/*
 * @(#)PeriodicAgentBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

/**
 * This class represents an BESA agent which behavior is periodic.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class PeriodicAgentBESA extends AgentBESA {

    /**
     * Builds a periodic BESA agent. In order to construct an agent BESA is
     * necessary to provide a structure that contains the associations between
     * behaviors, guards and multiguards, in addition to a state in where the
     * internal states of the agent are stored.
     *
     * @param alias Name of the agent with whom it will be identified in the
     * BESA container.
     * @param state Internal state of the agent, which inherits of StateBESA.
     * @param structAgent Structure that indicates the associations between
     * guards, multiguards and behaviors.
     * @param passwd Password necessary to access to the agent.
     */
    public PeriodicAgentBESA(String alias, StateBESA state,
            StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    /**
     * Closing routine of the agent. In this resources are freed and behaviors 
     * are stopped.
     */
    @Override
    public void shutdownAgent() {
    }

    /**
     * Starting routine of the agent. Invoked automatically before the infinite
     * cycle.
     */
    @Override
    public void setupAgent() {
    }
}
