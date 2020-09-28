/*
 * @(#)AGENTSTATE.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

/**
 * This a enumeration that contains the possible states of the life cycle of 
 * an agent.
 * TODO Validar estados del agente.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since JDK1.4
 * @since JAXB2.0
 */
public enum AGENTSTATE {
    /**
     * Represents the create state.
     */
    CREATE,
    /**
     * Represents the move state.
     */
    MOVE,
    /**
     * Represents the destroy state.
     */
    DESTROY,
    /**
     * Represents the kill state.
     */
    KILL,
    /**
     * Represents the alive state.
     */
    ALIVE
}
