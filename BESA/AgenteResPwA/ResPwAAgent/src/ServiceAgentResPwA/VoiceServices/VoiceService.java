/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.VoiceServices;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPInfoGuard;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import PepperPackage.PepperAdapter;
import Adapter.ResPwaAdapter;
import java.util.ArrayList;
import java.util.Map;
import rational.services.AsynchronousService;

/**
 *
 * @author juans
 */
public class VoiceService extends AsynchronousService{

    @Override
    public void executeAsyncService(SPServiceDataRequest data, AdapterBESA adapter, Map<String, ArrayList<SPInfoGuard>> subscribeAgents) {
        System.out.println("VoiceService Solicitado");
        ResPwaAdapter rpadapter= (ResPwaAdapter)adapter;
        rpadapter.VoiceServiceReqAsync(data);
    }
    
}