/*
 * @(#)GuardServiceProviderRequest.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
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
public class GuardServiceProviderRequest extends GuardBESA {

    /**
     * @param ag
     */
    public GuardServiceProviderRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see BESA.Agent.GuardBESA#funcExecGuard(BESA.Agent.Event.EventBESA)
     */
    @Override
    public void funcExecGuard(EventBESA event) {

        //Saca el data enviado
        ServiceProviderDataRequest data;
        data = (ServiceProviderDataRequest) event.getData();

        //Llama a la funci�n de ejecuci�n del servicio en el agente
        ServiceProviderBESA ag;
        ag = (ServiceProviderBESA) this.getAgent();

        //Saca los datos que vienen en el data
        //Saca el tipo de servicio solicitado.
        String serviceName;
        serviceName = data.getServiceName();
        //Saca el identificador del agente que env�a el evento
        String clientAgentId = data.getAgID();
        //Sace los dtos que vienen
        SPServiceDataRequest SPData = data.getSpRequestData();
        String guard = SPData.getGuard();
        //Busca el servicio en la tabla
        SPService service = ((StateServiceProvider) ag.getState()).getDescriptor().getServiceAccessTable().get(serviceName);

        //Llama a la funci�n correspondiente

        //SPServiceResultControlRobot resultData;
        //Llama la funci�n de ejecuci�n del servicio
        StateServiceProvider state = (StateServiceProvider) ag.getState();
        AdapterBESA adpt = state.getAdapter();
        //verifica que el agente y la guarda est�n registrados
        ArrayList<SPInfoGuard> res = ((StateServiceProvider) ag.getState()).getAgentsGuardsTable().get(clientAgentId);
        if (res != null) {
            int cont = 0;
            for (int i = 0; i < res.size(); i++) {
                SPInfoGuard tmp = (SPInfoGuard) res.get(i);
                if (tmp.getIdGuard().equals(guard) && tmp.getServiceName().equals(serviceName)) {
                    cont++;
                }
            }
            if (cont == 0) {
                try {
                    throw new ServiceProviderAgentExceptionBESA("The guard and data hasn't been found into SP registry.");
                } catch (ServiceProviderAgentExceptionBESA ex) {
                    ReportBESA.error("The guard and data hasn't been found into SP registry: " + ex.toString());
                    ex.printStackTrace();
                }
            } else {
                DataBESA resultData = service.executeService(SPData, adpt);
                //Devuelve el resultado de la ejeuci�n al agente solicitante
                EventBESA evento = new EventBESA(guard, resultData);
                try {
                    ag.getAdmLocal().getHandlerByAid(clientAgentId).sendEvent(evento);
                } catch (Exception e) {
                    try {
                        throw new ServiceProviderAgentExceptionBESA("Couldn't return the execution result to claimant agent: " + e.toString());
                    } catch (ServiceProviderAgentExceptionBESA ex) {
                        ReportBESA.error("Couldn't return the execution result to claimant agent: " + ex.toString());
                        ex.printStackTrace();
                    }
                }
            }
        } else {
            try {
                throw new ServiceProviderAgentExceptionBESA("The guards are null.");
            } catch (ServiceProviderAgentExceptionBESA ex) {
                ReportBESA.error("The guards are null: " + ex.toString());
                ex.printStackTrace();
            }
        }
    }
}
