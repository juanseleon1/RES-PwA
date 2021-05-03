/*
 * @(#)ProtocolBehavior.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.protocol;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.BehaviorBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ProtocolBehavior extends BehaviorBESA {

    /**
     * @param ag
     */
    public ProtocolBehavior(AgentBESA ag) {
        super(ag);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param ag
     * @param periodic
     * @param milliseconds
     * @param type
     */
    public ProtocolBehavior(AgentBESA ag, boolean periodic, long milliseconds,
            int type) {
        super(ag, periodic, milliseconds, type);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see BESA.Agent.BehaviorBESA#setupBehavior()
     */
    //@Override
    protected void setupBehavior() {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see BESA.Agent.BehaviorBESA#shutdownBehavior()
     */
    //@Override
    protected void shutdownBehavior() {
        // TODO Auto-generated method stub
    }
}
