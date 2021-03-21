/*
 * @(#)BDIBuilderDirector.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel.Agent.Builder;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;

/**
 * <p>Director for the BDI Agent Builder</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class BDIBuilderDirector {

    private BDIAgentBuilder bdiAgentBuilder;

    public BDIBuilderDirector() {
    }

    public BDIBuilderDirector(BDIAgentBuilder bDIAgentBuilder) {
        this.bdiAgentBuilder = bDIAgentBuilder;
    }

    public void setbDIAgentBuilder(BDIAgentBuilder bDIAgentBuilder) {
        this.bdiAgentBuilder = bDIAgentBuilder;
    }

    public AgentBDI getAgentBDI() {
        return bdiAgentBuilder.getAgentBDI();
    }

    /** 
     * <p> Execute the build flow </p>
     * @throws KernelAgentExceptionBESA
     * @throws ExceptionBESA 
     */
    public void buildBDIAgent() throws KernelAgentExceptionBESA, ExceptionBESA {
        bdiAgentBuilder.buildDBIMachine();
        bdiAgentBuilder.buildAgentBelieves();
        bdiAgentBuilder.buildAgent();
    }
}
