/*
 * @(#)GuardServiceProviderSuscribe.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import java.util.ArrayList;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class GuardServiceProviderSuscribe extends GuardBESA {

    /**
     *
     */
    static final int SYNC_SUBSCRIBE = 0;
    /**
     * 
     */
    static final int ASYNC_SUBSCRIBE = 1;
    /**
     * 
     */
    static final int SYNC_UNSUBSCRIBE = 2;
    /**
     * 
     */
    static final int ASYNC_UNSUBSCRIBE = 3;

    /**
     * @param ag
     */
    public GuardServiceProviderSuscribe() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see BESA.Agent.GuardBESA#funcExecGuard(BESA.Agent.Event.EventBESA)
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        //Saca el id del agente, el tipo de suscripci�n, y el nombre de la guarda a la que debe contestar
        ServiceProviderDataSuscribe data = (ServiceProviderDataSuscribe) event.getData();
        String responseGuardName = data.getReplyGuardName().getIdGuard();
        int subscriptionType = data.getType();
        String senderAgId = event.getSenderAgId();
        ServiceProviderBESA ag = (ServiceProviderBESA) this.getAgent();
        //Verifica qu� tipo de suscripci�n es
        ArrayList<SPInfoGuard> res = null;
        switch (subscriptionType) {
            case SYNC_SUBSCRIBE:
                //Adiciona el registro al hashTable del estado.
                res = ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().get(senderAgId);
                if (res == null) {
                    res = new ArrayList<SPInfoGuard>();
                    res.add(new SPInfoGuard(responseGuardName, data.getServiceName(), data.getDataType()));
                    ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().put(senderAgId, res);
                } else {
                    int cont = 0;
                    for (int i = 0; i < res.size(); i++) {
                        SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                        if (tmp.getIdGuard().equals(responseGuardName) && tmp.getServiceName().equals(data.getServiceName())) {
                            cont++;
                        }
                    }
                    if (cont == 0) {
                        res.add(new SPInfoGuard(responseGuardName, data.getServiceName(), data.getDataType()));
                        ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().put(senderAgId, res);
                    }
                }
                //System.out.println("Resgistered users:" +((StateServiceProvider)ag.getState()).getAgentsGuardsTable().size());
                //System.out.println("Resgistered guards:" +((StateServiceProvider)ag.getState()).getAgentsGuardsTable().get(senderAgId).size());
                break;

            case SYNC_UNSUBSCRIBE:
                //Elimina el registro de la HashTable
                res = ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().get(senderAgId);
                if (res != null) {
                    if (res.size() > 1) {
                        for (int i = 0; i < res.size(); i++) {
                            SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                            if (tmp.getIdGuard().equals(responseGuardName) && tmp.getServiceName().equals(data.getServiceName()) &&
                                    tmp.getDataType().equals(data.getDataType())) {
                                ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().remove(senderAgId);
                                i = res.size() + 1;
                            }
                        }
                    } else {
                        ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().remove(senderAgId);
                    }
                }

                break;

            case ASYNC_SUBSCRIBE:
                //TODO: Implementar suscrtipci�n asincr�nica especificando servicios diferencialmente (SensorBESA)
                res = ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().get(senderAgId);
                if (res == null) {
                    res = new ArrayList<SPInfoGuard>();
                    res.add(new SPInfoGuard(responseGuardName, data.getServiceName(), data.getDataType()));
                    ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().put(senderAgId, res);
                } else {
                    int cont = 0;
                    for (int i = 0; i < res.size(); i++) {
                        SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                        if (tmp.getIdGuard().equals(responseGuardName) && tmp.getServiceName().equals(data.getServiceName())) {
                            cont++;
                        }
                    }
                    if (cont == 0) {
                        res.add(new SPInfoGuard(responseGuardName, data.getServiceName(), data.getDataType()));
                        ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().put(senderAgId, res);
                    }
                }
                //System.out.println("Resgistered users:" +((StateServiceProvider)ag.getState()).getAgentsGuardsTableAsync().size());
                //System.out.println("Resgistered guards:" +((StateServiceProvider)ag.getState()).getAgentsGuardsTableAsync().get(senderAgId).size());
                break;

            case ASYNC_UNSUBSCRIBE:
                //TODO: Implementar suscrtipci�n asincr�nica especificando servicios diferencialmente (SensorBESA)
                res = ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().get(senderAgId);
                if (res != null) {
                    if (res.size() > 1) {
                        for (int i = 0; i < res.size(); i++) {
                            SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                            if (tmp.getIdGuard().equals(responseGuardName) && tmp.getServiceName().equals(data.getServiceName()) &&
                                    tmp.getDataType().equals(data.getDataType())) {
                                ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().remove(senderAgId);
                                i = res.size() + 1;
                            }
                        }
                    } else {
                        ((StateServiceProvider) ag.getState()).getAgentsGuardsTableAsync().remove(senderAgId);
                    }
                }
                break;
            default:
                //TODO: Implementar el default
                break;
        }
    }
}
