/*
 * @(#)EventStampBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent.Event;

/**
 * This class allows to handle the stamp of a BESA event.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
class EventStampBESA {

    /**
     * 
     */
    private static int counter = 0;

    /**
     * Obtains and increase the event stamp. 
     * @return The stamp represented by an integer.
     */
    synchronized static public int nextStamp() {
        return ++counter;
    }
}
