/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.EnableTaskExecutionGuard;
import rational.guards.NotifyTaskFinalizedGuard;
import rational.guards.PlanExecutionGuard;

/**
 *
 * @author usuario
 */
public class WorkerAgent extends AgentBESA implements Serializable {

    public WorkerAgent() throws KernelAgentExceptionBESA {
        super("", new WorkerState(), buildStruct(), 0.9);
    }
    public WorkerAgent(String alias, WorkerState state) throws KernelAgentExceptionBESA {
        super(alias, state, buildStruct(), 0.9);
    }

    public static StructBESA buildStruct() {
        try {
            StructBESA structBESA = new StructBESA();
            structBESA.addBehavior("PlanExecution");
            structBESA.bindGuard("PlanExecution", PlanExecutionGuard.class);
            structBESA.addBehavior("NotifyTask");
            structBESA.bindGuard("NotifyTask", NotifyTaskFinalizedGuard.class);
            structBESA.bindGuard("NotifyTask", EnableTaskExecutionGuard.class);
            return structBESA;
        } catch (ExceptionBESA ex) {
            Logger.getLogger(WorkerAgent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public void setupAgent() {
    }

    @Override
    public void shutdownAgent() {
    }
    
}
