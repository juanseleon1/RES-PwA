/*
 * @(#)StateBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the internal state of the BESA agent.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class StateBESA implements Serializable {

    /**
     * Serial version UID.
     */
    public static final long serialVersionUID = "StateBESA".hashCode();
    /**
     * Guard list.
     */
    private transient ArrayList<GuardBESA> guards;

    /**
     * Creates a new StateBESA. All the subclasses must implement the
     * Serializable interface, to allow mobility.
     */
    public StateBESA() {
        initGuards();
    }

    /**
     * Initializes the guards storage.
     */
    public void initGuards() {
        guards = new ArrayList<GuardBESA>();
    }

    /**
     * Puts the guard in the vector to consider it for update.
     * 
     * @param guard Guard to be considered.
     */
    protected void bindGuard(GuardBESA guard) {
        guards.add(guard);
    }

    /**
     * Removes the guard of the vector to not consider it.
     * 
     * @param guard Guard to remove.
     */
    protected void unbindGuard(GuardBESA guard) {
        guards.remove(guard);
    }

    /**
     * Try to shoot the guards associated to the object.<BR><BR>
     * 
     * It should to be called always that StateBESA has been modified.
     */
    public void update() {
        for (int i = 0; i < guards.size(); i++) {
            GuardBESA guard = (GuardBESA) guards.get(i);
            guard.getPort().tryGuard();
        }
    }
}


