/*
 * @(#)StageDataBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.protocol;

import BESA.Kernel.Agent.Event.DataMultiGuardBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class StageDataBESA extends DataMultiGuardBESA {

    /**
     *
     */
    private String reply2Guard;
    /**
     * 
     */
    private String myId;

    /**
     * @param guardIndex
     * @param reply2Guard
     */
    public StageDataBESA(String guardIndex, String reply2Guard, String myId) {
        super(guardIndex);
        this.reply2Guard = reply2Guard;
        this.myId = myId;
    }

    /**
     * @return  Returns the reply2Guard.
     * @uml.property  name="reply2Guard"
     */
    public String getReply2Guard() {
        return reply2Guard;
    }

    /**
     * @return  Returns the myId.
     * @uml.property  name="myId"
     */
    public String getMyId() {
        return myId;
    }
}
