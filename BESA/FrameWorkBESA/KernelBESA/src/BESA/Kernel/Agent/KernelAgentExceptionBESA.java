/*
 * @(#)ConfigExceptionBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.ExceptionBESA;

/**
 * This class represents the type exception for the kernell agent.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class KernelAgentExceptionBESA extends ExceptionBESA {

    /**
     * Creates a new instance.
     *
     * @param message Exception message.
     */
    public KernelAgentExceptionBESA(String message) {
        super(message);
    }
}
