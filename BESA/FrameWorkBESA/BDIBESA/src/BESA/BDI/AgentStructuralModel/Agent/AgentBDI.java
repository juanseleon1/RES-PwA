/*
 * @(#)AgentBDI.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel.Agent;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.BDI.DBIDecisionMachine.DesireToIntentionInstantiationGuard;
import BESA.BDI.DBIDecisionMachine.GarbageCollectionGuard;
import BESA.BDI.DBIDecisionMachine.Timer.GarbageCollectorTimerTask;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.ArrayList;
import java.util.List;
import rational.RationalAgent;
import rational.RationalState;
import java.util.Timer;
import rational.RationalRole;
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

    private Timer viewerGarbageCollector;

    public AgentBDI(String alias, Believes believes, List<GoalBDI> goals, double passwd, double threshold) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, new StateBDI(), setupBDIStruct(), passwd);
        StateBDI state = (StateBDI) this.getState();
        state.getMachineBDIParams().setNeedThreshold(threshold);
        state.getMachineBDIParams().setDutyThreshold(threshold);
        state.getMachineBDIParams().setOportunityThreshold(threshold);
        state.getMachineBDIParams().setRequirementThreshold(threshold);
        state.getMachineBDIParams().setSurvivalThreshold(threshold);

        List<String> roleNameList = new ArrayList<>();
        for (GoalBDI goal : goals) {
            state.getMachineBDIParams().addPotentialGoal(goal);
            roleNameList.add(goal.getRole().getRoleName());
        }
        state.setBelieves(believes);

        state.initPool(roleNameList, alias);
    }

    /**
     * Creates a new instance.
     *
     * @param alias Agent alias.
     * @param state Agent state.
     * @param structAgent Agent struct.
     * @param passwd Agent password.
     */
    public AgentBDI(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, (RationalState) state, structAgent, passwd);
        StructBESA newStruct = this.getStructAgent();
        newStruct.addBehavior("DesireToIntentionInstantiationBehavior");
        newStruct.addBehavior("GarbageCollectionBehavior");
        newStruct.bindGuard("DesireToIntentionInstantiationBehavior", DesireToIntentionInstantiationGuard.class);
        newStruct.bindGuard("GarbageCollectionBehavior", GarbageCollectionGuard.class);
    }

    @Override
    public void setupAgent() {
        super.setupAgent();
        RationalState rationalState = (RationalState) getState();
        rationalState.subscribeGuardToUpdate(DesireToIntentionInstantiationGuard.class.getName());
    }

    /**
     * <p>
     * setup the 3 BDI flow default behaviors (Threads) to the BDI Structure</p>
     *
     * @throws ExceptionBESA
     */
    private static StructBESA setupBDIStruct() throws ExceptionBESA {
        StructBESA structBESA = new StructBESA();
        structBESA.addBehavior("DesireToIntentionInstantiationBehavior");
        structBESA.addBehavior("GarbageCollectionBehavior");
        structBESA.bindGuard("DesireToIntentionInstantiationBehavior", DesireToIntentionInstantiationGuard.class);
        structBESA.bindGuard("GarbageCollectionBehavior", GarbageCollectionGuard.class);
        return structBESA;
    }

    private static StructBESA setupBDIStruct(StructBESA structBESA) throws ExceptionBESA {
        structBESA.addBehavior("DataAndInformationFlowBehavior");
        structBESA.addBehavior("DesireToIntentionInstantiationBehavior");
        structBESA.addBehavior("DominantGoalMappingBehavior");
        structBESA.addBehavior("GarbageCollectionBehavior");
        structBESA.bindGuard("DesireToIntentionInstantiationBehavior", DesireToIntentionInstantiationGuard.class);
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

    @Override
    public void shutdownAgent() {
        super.shutdownAgent();
        if (viewerGarbageCollector != null) {
            viewerGarbageCollector.cancel();
        }
    }

    public void initAgentBDI(Believes believes, List<GoalBDI> goals, double threshold) {
        StateBDI state = (StateBDI) this.getState();

        state.setBelieves(believes);

        state.getMachineBDIParams().setNeedThreshold(threshold);
        state.getMachineBDIParams().setDutyThreshold(threshold);
        state.getMachineBDIParams().setOportunityThreshold(threshold);
        state.getMachineBDIParams().setRequirementThreshold(threshold);
        state.getMachineBDIParams().setSurvivalThreshold(threshold);

        List<String> roleNameList = new ArrayList<>();
        for (GoalBDI goal : goals) {
            state.getMachineBDIParams().addPotentialGoal(goal);
            roleNameList.add(goal.getRole().getRoleName());
        }
        state.setBelieves(believes);

        state.initPool(roleNameList, this.getAlias());
    }

}
