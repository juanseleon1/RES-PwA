/*
 * @(#)ServiceProviderBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class ServiceProviderBESA extends AgentBESA {

    /**
     *
     */
    public static final int SYNCHRONIC_SERVICE = 0;
    /**
     * 
     */
    public static final int ASYNCHRONIC_SERVICE = 1;

    /**
     * @param alias
     * @param state
     * @param structAgent
     * @param passwd
     */
    public ServiceProviderBESA(String alias, StateServiceProvider state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    /**
     * (non-Javadoc)
     *
     * @see BESA.Agent.AgentBESA#shutdownAgent()
     */
    @Override
    public void shutdownAgent() {
        // OJO No hay que unbind guardas
        // Se hace automaticamente antes de llamar a shutdownAgent
        // OJO No hay que Parar/matar comportamientos
        // Se hace automaticamente en el metodo kill del agente
        // Rutina de cierre del agente - cerrar y liberar recursos
        // Invocada autom�ticamente al salir de la bucla infinita
    }

    /*
     * (non-Javadoc)
     *
     * @see BESA.Agent.AgentBESA#setupAgent()
     */
    @Override
    public abstract void setupAgent();

    //abstract public void initServiceAccess();

    /*public void addServiceAccess(ServiceAccess servAc) {
    this.serviceAccessTable.put(servAc.getServiceType(), servAc);
    this.getAdmLocal().addService(servAc.getServiceType());
    this.getAdmLocal().bindService(this.getAid(), servAc.getServiceType());
    }*/
    /**
     *
     * @return
     */
    public static StructBESA getDefaultStruct() {
        try {
            StructBESA struct = new StructBESA();
            struct.addBehavior("BehaviorServiceProvider");
            struct.bindGuard("BehaviorServiceProvider", GuardServiceProviderRequest.class);
            struct.bindGuard("BehaviorServiceProvider",GuardServiceProviderSuscribe.class);
            return struct;
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
            return null;
        }
    }

    /**
     * 
     * @param data
     * @param serviceName
     */
    public void processAsychEvent(DataBESA data, String serviceName) {
        Set<String> mySet = ((StateServiceProvider) this.getState()).getAgentsGuardsTableAsync().keySet();
        for (String key : mySet) {
            ArrayList<SPInfoGuard> res = ((StateServiceProvider) this.getState()).getAgentsGuardsTableAsync().get(key);
            for (int i = 0; i < res.size(); i++) {
                SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                if (tmp.getServiceName().equals(serviceName) && tmp.getDataType().equals(data.getClass().getName())) {
                    //Devuelve el resultado de la ejeuciï¿½n al agente solicitante
                    EventBESA evento = new EventBESA(tmp.getIdGuard(), data);
                    try {
                        this.getAdmLocal().getHandlerByAid(key).sendEvent(evento);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ReportBESA.error(e.toString());
                    }
                    break;
                }
            }
        }
    }
    //public abstract String getDIrectoryServiceName(int index);
}
