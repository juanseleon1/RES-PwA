/*
 * @(#)BDIEvaluable.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;


import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 * <p>Interface that contains the evaluable BDI methods for the goals</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
 public interface BDIEvaluable {

    /**
     * <p>evaluate the viability for a Goal</p>
     * @return
     */
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA;

    /**
     * <p>detect the goal for the BDI flow</p>
     * @return
     */
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA;

    /**
     * <p>evaluate the plausibility (legal) for the goal</p>
     * @return 
     */
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA;

    /**
     * <p>evaluate the contribution value for a Goal</p>
     * @return
     */
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA;

    /**
     * <p>evaluate the unlegality result prediction</p>
     * @param worldModelPredicted
     * @return 
     */
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA;

    /**
     * <p>evaluate if the Goal was accomplished</p>
     * @param rst
     * @return 
     */
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA;

}