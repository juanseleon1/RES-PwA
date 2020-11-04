/*
 * @(#)DataBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent.Event;

import BESA.Log.ReportBESA;
import java.io.Serializable;

/**
 * This class represents the data to be sent in a message to a agent BESA.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class DataBESA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = "DataBESA".hashCode();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Constructs a new DataBESA. 
     */
    public DataBESA() {
    }

    /**
     * Allows to initialize a DataBESA, using a chain of characters.
     * This must be coherent with the method "getStringFromDataBesa"
     * @param str Initialization String.
     */
    public void getDataBesaFromString(String str) throws KernellAgentEventExceptionBESA {
        ReportBESA.error("The default non functional method has been invoked - This method"
                + "must be overwrite for proper operation. ");
        throw new KernellAgentEventExceptionBESA("The default non functional method has been invoked - This method"
                + "must be overwrite for proper operation. ");
    }

    /**
     * Allows to obtain a chain of characters from a DataBESA
     * This must be coherent with the method "getDataBesaFromString"
     *
     * Required for serialization purposes while connecting agents through BAP
     * for instance when Java agents communicate with .NET agents.
     *
     * This method is automatically called by "SenderBAP.java" class
     *
     */
    public String getStringFromDataBesa() throws KernellAgentEventExceptionBESA {
        ReportBESA.error("The default non functional method has been invoked - This method"
                + "must be overwrite for proper operation; i.e. The SenderBAP ");
        throw new KernellAgentEventExceptionBESA("The default non functional method has been invoked - This method"
                + "must be overwrite for proper operation. ");
    }
}
