/*
 * @(#)ProtocolStage.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.protocol;

import BESA.Kernel.Agent.MultiGuardIndexBESA;
import BESA.Kernel.Agent.MultiGuardBESA;
import BESA.Kernel.Agent.Event.EventBESA;

/**
 * This class represents
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class ProtocolStage extends MultiGuardIndexBESA {

    /**
     * @param multiGuardBESA
     */
    public ProtocolStage(MultiGuardBESA multiGuardBESA) {
        super(multiGuardBESA);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see BESA.Agent.MultiGuardIndexBESA#funcExecGuard(BESA.Agent.Event.EventBESA)
     */
    @Override
    public abstract void funcExecGuard(EventBESA event);
}
