/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

/**
 *
 * @author jsleon
 */
public enum WHO {
    ROBOT(0.2),PWA(0.3);
    private final double value;
    
    private WHO(double value){
        this.value=value;
        
    }
    
    public double getValue(){
        return value;
    }
}
