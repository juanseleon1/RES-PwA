/*
 * @(#)ExceptionBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA;

/**
 * This class provides the methods to manage the BESA exceptions.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since   JDK1.4
 */
public class ExceptionBESA extends Exception {

    /**
     * Serial version UID.
     */
    public static final long serialVersionUID = "ExceptionBESA".hashCode();
    /**
     * Exception message.
     */
    private static final String msg;

    /**
     * Static block for get the location information of the exception.
     */
    static {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        msg = "[ExceptionBESA:" + stack[2].getClassName() + ":" + stack[2].getMethodName() + ":" + String.valueOf(stack[2].getLineNumber()) + "]: ";
    }

    /**
     * Sets the value of the message that contains a description of the
     * exception.
     *
     * @param msg A string with the exception.
     */
    public ExceptionBESA(String message) {
        super(msg + message);
    }
}
