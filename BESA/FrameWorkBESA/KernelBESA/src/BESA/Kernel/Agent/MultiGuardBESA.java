/*
 * @(#)MultiGuardBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.Kernel.Agent.Event.DataMultiGuardBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a guard that can have different behaviors for different or for
 * the same agent. It use the field ack (name given by historical reasons) of
 * DataBESA.
 * 
 * TODO Verificar.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class MultiGuardBESA extends GuardBESA {

    /**
     * Guard list.
     */
    protected ArrayList<MultiGuardIndexBESA> guardIndex;

    /**
     * Creates a new multiguard.
     * 
     * @param ag Multiguard associated agent.
     */
    public MultiGuardBESA() {
        super();
        guardIndex = new ArrayList<MultiGuardIndexBESA>();
    }

    /**
     * Adds a multiguardaAck to this multiguard.
     * 
     * @param m MultiGuardIndexBESA to add.
     */
    public void addMultiGuardIndex(MultiGuardIndexBESA m) {
        guardIndex.add(m);
    }

    /**
     * Evaluates if the guard must be or not executed by the behaviors.By 
     * default the function returns true, the guard is executed with the
     * occurrence of the event, if not desire this then is due to rewrite
     * this function in the subclass.
     * 
     * @param objEvalBool Internal agent state, used to verify the execution
     * condition.
     * @return true if the guard must be executed; false otherwise.
     */
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    /**
     * Conduct to execute when the guard initializes. The guard is executed
     * within a behavior to which it has been associated. The conduct depends 
     * on the data associated to the event. The conduct depends and/or can 
     * modify the agent state. This conduct watches the field ack and if 
     * multiguardAck associated has the same data that ack, executes
     * multiguardaAck.
     * 
     * @param event Received event, contains the related data.
     */
    @SuppressWarnings("unchecked")
    public void funcExecGuard(EventBESA event) {
        for (Iterator i = guardIndex.iterator(); i.hasNext();) {
            MultiGuardIndexBESA m = (MultiGuardIndexBESA) i.next();
            ReportBESA.trace("Execuites of funcExecGuard");
            String ack = ((DataMultiGuardBESA) event.getData()).getGuardIndex();
            try {
                Class classEventAck = Class.forName(ack);
                Class classMultiguard = Class.forName(m.getAck());
                if (classEventAck.isAssignableFrom(InheritableAckBESA.class)) {
                    if (classMultiguard.isAssignableFrom(classEventAck)) {
                        m.funcExecGuard(event);
                    }
                } else {
                    if (((DataMultiGuardBESA) event.getData()).getGuardIndex().equals(m.getAck())) {
                        m.funcExecGuard(event);
                    }
                }
            } catch (ClassNotFoundException e) {
                if (((DataMultiGuardBESA) event.getData()).getGuardIndex().equals(m.getAck())) {
                    m.funcExecGuard(event);
                }
            }
        }
    }
}
