/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDescriptor;
import BESA.Kernel.Social.ServiceProvider.agent.StateServiceProvider;
import BESA.Local.Directory.AgLocalHandlerBESA;

/**
 *
 * @author juans
 */
public class RobotProviderAgent extends ServiceProviderBESA {
    
    private static AgLocalHandlerBESA agh;
    public static String servActividades;
    public static String servAutonomia;
    public static String servBateria;
    public static String servHumanos;
    public static String servLocation;
    public static String servMovimiento;
    public static String servEstado;
    public static String servVoz;
    private static PepperAdapter adapterP;
    
    public RobotProviderAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias,prepareServiceProvider(),ServiceProviderBESA.getDefaultStruct() ,0.96);
   }

    private static StateServiceProvider prepareServiceProvider()
    {
        adapterP = new PepperAdapter();
        StateServiceProvider estado = new StateServiceProvider(adapterP,new ServiceProviderDescriptor());
        return estado;
    }
    @Override
    public void setupAgent() {
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servActividades);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servAutonomia);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servBateria);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servHumanos);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servLocation);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servMovimiento);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servEstado);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servVoz);
    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
