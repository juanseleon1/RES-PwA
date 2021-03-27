/*
 * @(#)StateServiceProvider.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import java.util.Hashtable;
import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.StateBESA;
import java.util.ArrayList;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class StateServiceProvider extends StateBESA {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = "StateServiceProvider".hashCode();
    /**
     * Hashtable containing the agents and guards entered the answer to be synchronously.
     */
    private Hashtable<String, ArrayList<SPInfoGuard>> agentsGuardsTableSync;
    /**
     * Hashtable containing the agents and guards entered the answer to be asynchronous.
     */
    private Hashtable<String, ArrayList<SPInfoGuard>> agentsGuardsTableAsync;
    /**
     * Adapter that can use the services.
     */
    private AdapterBESA adapter;
    /**
     * Service provider descriptor.
     */
    private ServiceProviderDescriptor descriptor;

    /**
     * Creates an instance of StateServiceProvider class.
     * @param Adapter.
     * @param Service provider descriptor.
     */
    public StateServiceProvider(AdapterBESA adapter, ServiceProviderDescriptor descriptor) {
        super();
        this.descriptor = descriptor;
        this.agentsGuardsTableSync = new Hashtable<String, ArrayList<SPInfoGuard>>();
        this.agentsGuardsTableAsync = new Hashtable<String, ArrayList<SPInfoGuard>>();
        this.adapter = adapter;
    }

    /**
     * Returns the adapter.
     * @return Adapter.
     */
    public AdapterBESA getAdapter() {
        return adapter;
    }

    /**
     * Returns the agentsGuardsTable.
     * @return Agents guards table.
     */
    public Hashtable<String, ArrayList<SPInfoGuard>> getAgentsGuardsTable() {
        return agentsGuardsTableSync;
    }

    /**
     * Returns the descriptor.
     * @return Descriptor.
     */
    public ServiceProviderDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Returns the agentsGuardsTableAsync.
     * @return Agents guards tableAsync.
     */
    public Hashtable<String, ArrayList<SPInfoGuard>> getAgentsGuardsTableAsync() {
        return agentsGuardsTableAsync;
    }

    /**
     * The agentsGuardsTableAsync to set.
     * @param agents guards tableAsync.
     */
    public void setAgentsGuardsTableAsync(Hashtable<String, ArrayList<SPInfoGuard>> agentsGuardsTableAsync) {
        this.agentsGuardsTableAsync = agentsGuardsTableAsync;
    }
}
