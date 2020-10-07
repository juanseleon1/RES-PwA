/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;

/**
 *
 * @author juans
 */
public class PepperAdapter extends AdapterBESA{
    
    private RobotProviderAgent rpa;
    
    public PepperAdapter() {
        super(null,null);
        this.rpa=null;
    }

    public RobotProviderAgent getRpa() {
        return rpa;
    }

    public void setRpa(RobotProviderAgent rpa) {
        this.rpa = rpa;
    }
    
    
//AQUI VAN TODOS LOS SERVICIOS TANTO SYNC COMO ASYNC    

    public DataBESA solicitarInfoActividadAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarInfoAutonomyAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarInfoBatteryAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarInfoHumanAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarInfoLocationAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarInfoStateAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarVoiceAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataBESA solicitarMovementAsync(SPServiceDataRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
