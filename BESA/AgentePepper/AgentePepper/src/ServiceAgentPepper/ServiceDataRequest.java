/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import java.util.HashMap;
import rational.services.ActivateServiceData;

/**
 *
 * @author juans
 */
public class ServiceDataRequest extends ActivateServiceData{
    
    private String subservice;
    private HashMap<String,Object> params;
    
    
    public ServiceDataRequest(String service,String subservice, HashMap<String,Object> params)
    {    
        
       super(service);
       this.subservice=subservice;
       this.params=params;
        
    }
    

    
}
