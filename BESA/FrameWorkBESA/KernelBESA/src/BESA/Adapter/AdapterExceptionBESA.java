/*
 * @(#)AdapterExceptionBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Adapter;

import BESA.ExceptionBESA;

/**
 * This class represents the exceptions set of the BESA adapter.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since   JDK1.4
 */
public class AdapterExceptionBESA extends ExceptionBESA {

    /**
     * Creates a new instance.
     * 
     * @param msg Exception message.
     */
    public AdapterExceptionBESA(String msg) {
        super(msg);
    }
}
