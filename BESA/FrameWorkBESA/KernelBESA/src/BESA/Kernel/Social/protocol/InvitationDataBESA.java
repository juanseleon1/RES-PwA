/*
 * @(#)InvitationDataBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.protocol;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class InvitationDataBESA extends DataBESA {

    /**
     *
     */
    private String myId;

    /**
     * @param response2Guard
     */
    public InvitationDataBESA(String myId) {
        this.myId = myId;
    }

    /**
     * @return  Returns the myId.
     * @uml.property  name="myId"
     */
    public String getMyId() {
        return myId;
    }

    /**
     * 
     * @param myId
     */
    public void setMyId(String myId) {
        this.myId = myId;
    }
}
