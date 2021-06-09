/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.MovementServices;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPInfoGuard;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import PepperPackage.Utils.PepperAdapter;
import Adapter.ResPwaAdapter;
import java.util.ArrayList;
import java.util.Map;
import rational.services.AsynchronousService;

/**
 *
 * @author juans
 */
public class MovementService extends AsynchronousService{

    @Override
    public void executeAsyncService(SPServiceDataRequest data, AdapterBESA adapter, Map<String, ArrayList<SPInfoGuard>> subscribeAgents) {
        System.out.println("MovementService Solicitado");
        ResPwaAdapter rpadapter= (ResPwaAdapter)adapter;
        rpadapter.MovementServiceReqAsync(data);
    }
    
}