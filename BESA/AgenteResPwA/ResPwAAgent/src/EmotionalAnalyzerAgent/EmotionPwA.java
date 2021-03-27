/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

/**
 *
 * @author mafegarces
 */
public enum EmotionPwA {
    ANGER("ANGER"),
    SADNESS("SADNESS"),
    HAPPINESS("HAPPINESS");
    
    private String dict;

    private EmotionPwA(String s)
    {
        dict=s;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }
    
}
