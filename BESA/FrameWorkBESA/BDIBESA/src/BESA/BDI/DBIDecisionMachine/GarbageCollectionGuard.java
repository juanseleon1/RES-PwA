/*
 * @(#)GarbageCollectionGuard.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.DBIDecisionMachine;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.BDIMachineParams;
import BESA.BDI.AgentStructuralModel.DesireHierarchyPyramid;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import rational.mapping.Believes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Guard for the Garbage Collection Process</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class GarbageCollectionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {           
            AgentBDI agentBDI = (AgentBDI) agent;
            StateBDI stateBDI = (StateBDI) agentBDI.getState();
            Believes believes = stateBDI.getBelieves();
            BDIMachineParams paramsBDI = stateBDI.getMachineBDIParams();
            DesireHierarchyPyramid pyramidBDI = paramsBDI.getPyramidGoals();
            pyramidBDI.callGarbageCollector(believes, stateBDI.getMachineBDIParams());
        } catch (KernellAgentEventExceptionBESA ex) {
            Logger.getLogger(GarbageCollectionGuard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DesireToIntentionInstantiationGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
