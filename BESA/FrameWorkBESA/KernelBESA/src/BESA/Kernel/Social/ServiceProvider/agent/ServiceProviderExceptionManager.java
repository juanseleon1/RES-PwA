/*
 * @(#)ServiceProviderExceptionManager.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ServiceProviderExceptionManager extends ServiceProviderBESA {

    /**
     *
     */
    public static final String idPrefix = "ExceptionManager";
    /**
     * 
     */
    private ArrayList<Integer> myRobots;
    /**
     * 
     */
    public static String EXCEPTIONMANAGER_SERVICEONDIRECTORY = "ExceptionManager";

    /**
     * @param alias
     * @param state
     * @param structAgent
     * @param passwd
     * @param myRobots
     */
    public ServiceProviderExceptionManager(String alias, StateServiceProvider state, StructBESA structAgent, double passwd, ArrayList<Integer> myRobots) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
        this.myRobots = myRobots;
    }

    /**
     * 
     */
    @Override
    public void setupAgent() {
        ReportBESA.trace("Starts setup.");
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(),
                ServiceProviderExceptionManager.EXCEPTIONMANAGER_SERVICEONDIRECTORY);
    }

    /**
     * @return the myRobots
     */
    public ArrayList<Integer> getMyRobots() {
        return myRobots;
    }
}
