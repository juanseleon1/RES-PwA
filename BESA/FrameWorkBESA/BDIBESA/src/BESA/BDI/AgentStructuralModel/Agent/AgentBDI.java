/*
 * @(#)AgentBDI.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel.Agent;

import BESA.BDI.AgentStructuralModel.BDIMachineParams;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.BDI.DBIDecisionMachine.DesireToIntentionInstantiationGuard;
import BESA.BDI.DBIDecisionMachine.EndedTheDesiresMachineGuard;
import BESA.BDI.DBIDecisionMachine.GarbageCollectionGuard;
import BESA.BDI.DBIDecisionMachine.IntermediateBehaviorToDesiresMachineGuard;
import BESA.BDI.DBIDecisionMachine.Timer.GarbageCollectorTimerTask;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.List;
import rational.RationalAgent;
import rational.RationalState;
import java.util.Timer;
import rational.mapping.Believes;

/**
 * <p>
 * Class that represents the BDI specific Agent</p>
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public abstract class AgentBDI extends RationalAgent {

    Timer viewerGarbageCollector;

    public AgentBDI(String alias, Believes believes, List<GoalBDI> goals, double threshold,  StructBESA structAgent) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, new StateBDI(goals, threshold, believes), setupBDIStruct(structAgent), 0.91);
    }
    
    public AgentBDI(String alias, Believes believes, BDIMachineParams machineBDIParams, double threshold, StructBESA structAgent) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, new StateBDI(machineBDIParams, believes,threshold), setupBDIStruct(structAgent), 0.91);
    }
    
    public AgentBDI(String alias, Believes believes, BDIMachineParams machineBDIParams, StructBESA structAgent) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, new StateBDI(machineBDIParams, believes), setupBDIStruct(structAgent), 0.91);
    }
 
    /**
     * Creates a new instance.
     *
     * @param alias Agent alias.
     * @param state Agent state.
     * @param structAgent Agent struct.
     * @param passwd Agent password.
     * @throws BESA.ExceptionBESA
     */
    public AgentBDI(String alias, StateBDI state, StructBESA structAgent, double passwd) throws ExceptionBESA {
        super(alias, (RationalState) state, setupBDIStruct(structAgent), passwd);
    }

    @Override
    final public void setupRationalAgent() {
        RationalState rationalState = (RationalState) getState();
        rationalState.subscribeGuardToUpdate(IntermediateBehaviorToDesiresMachineGuard.class.getName());
        setupAgentBDI();
    }

    /**
     * <p>
     * setup the 3 BDI flow default behaviors (Threads) to the BDI Structure</p>
     *
     * @throws ExceptionBESA
     */
 
    private static StructBESA setupBDIStruct(StructBESA structBESA) throws ExceptionBESA {
        structBESA.addBehavior("DataAndInformationFlowBehavior");
        structBESA.addBehavior("DominantGoalMappingBehavior");
        structBESA.addBehavior("IntermediateBehaviorToDesiresMachine");
        structBESA.addBehavior("DesireToIntentionInstantiationBehavior");
        structBESA.addBehavior("EndedTheDesiresMachine");
        structBESA.addBehavior("GarbageCollectionBehavior");
        structBESA.bindGuard("IntermediateBehaviorToDesiresMachine",IntermediateBehaviorToDesiresMachineGuard.class);
        structBESA.bindGuard("DesireToIntentionInstantiationBehavior", DesireToIntentionInstantiationGuard.class);
        structBESA.bindGuard("EndedTheDesiresMachine",EndedTheDesiresMachineGuard.class);
        structBESA.bindGuard("GarbageCollectionBehavior", GarbageCollectionGuard.class);
        return structBESA;
    }

    /**
     * <p>
     * Start the timer for the BDI Flow</p>
     *
     * @throws ExceptionBESA
     */
    public void startTimers() throws ExceptionBESA {
        if (this.isAlive()) {
            /**
             * start the timer for garbageCollector *
             */
            viewerGarbageCollector = new Timer();
            GarbageCollectorTimerTask taskGarbageCollectorTimer = new GarbageCollectorTimerTask(this.getAlias(), this.getAdmLocal());
            viewerGarbageCollector.schedule(taskGarbageCollectorTimer, 1000, 1000);
        }
    }

    public void startTimers(long delay, long period) throws ExceptionBESA {
        if (this.isAlive()) {
            /**
             * start the timer for garbageCollector *
             */
            viewerGarbageCollector = new Timer();
            GarbageCollectorTimerTask taskGarbageCollectorTimer = new GarbageCollectorTimerTask(this.getAlias(), this.getAdmLocal());
            viewerGarbageCollector.schedule(taskGarbageCollectorTimer, delay, period);
        }
    }
    
    @Override
    final public void shutdownRationalAgent() {
        if (viewerGarbageCollector != null) {
            viewerGarbageCollector.cancel();
        }
        shutdownAgentBDI();
    }
    
    public abstract void setupAgentBDI();
    public abstract void shutdownAgentBDI();
}
