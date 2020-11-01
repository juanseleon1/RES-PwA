/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juans
 */
public class EmotionalData extends DataBESA{
    
    private Map<String, Object> info;
    
    public EmotionalData(){
        info=new HashMap<>();
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
    
}
