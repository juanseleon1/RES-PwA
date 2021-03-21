/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import ServiceAgentResPwA.ServiceDataRequest;
import java.util.Map;

/**
 *
 * @author jsleon
 */
public abstract class ModulationStrategy {
    private RelationProfile relProf;
    public abstract Map<String,Object> modulateAction(ServiceDataRequest sdr);
    protected abstract Map<String,Object> calculateModulateValues();

    public RelationProfile getRelProf() {
        return relProf;
    }

    public void setRelProf(RelationProfile relProf) {
        this.relProf = relProf;
    }
}
