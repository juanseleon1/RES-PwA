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
        System.out.println("solicitarInfoActividadAsync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoAutonomyAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoAutonomyAsync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoBatteryAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoBatteryAsync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoHumanAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoHumanAsync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoLocationAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoLocationAsync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoStateAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoStateAsync Iniciado");
        return null;
    }

    public DataBESA solicitarVoiceAsync(SPServiceDataRequest data) {
        System.out.println("solicitarVoiceAsync Iniciado");
        return null;
    }

    public DataBESA solicitarMovementAsync(SPServiceDataRequest data) {
        System.out.println("solicitarMovementAsync Iniciado");
        return null;
    }
}
