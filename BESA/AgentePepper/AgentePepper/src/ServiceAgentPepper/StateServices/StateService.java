/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper.StateServices;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPService;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import ServiceAgentPepper.PepperAdapter;

/**
 *
 * @author juans
 */
public class StateService extends SPService{

        
    @Override
    public DataBESA executeService(SPServiceDataRequest data, AdapterBESA adapter) {
        PepperAdapter padapter= (PepperAdapter)adapter;
        return padapter.solicitarInfoStateAsync(data);
    }
    
}
