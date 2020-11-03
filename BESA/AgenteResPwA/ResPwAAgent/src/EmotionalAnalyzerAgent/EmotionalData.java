/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

import java.util.HashMap;
import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author juans
 */
public class EmotionalData extends InfoData{
    
    private Map<String, Object> info;
    
    public EmotionalData(){
        super("emodata");
        info=new HashMap<>();
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

}
