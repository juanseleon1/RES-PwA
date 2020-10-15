/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderAgentExceptionBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDescriptor;
import BESA.Kernel.Social.ServiceProvider.agent.StateServiceProvider;
import BESA.Local.Directory.AgLocalHandlerBESA;
import ServiceAgentPepper.ActivityServices.ActivityService;
import ServiceAgentPepper.AutonomyServices.AutonomyService;
import ServiceAgentPepper.BatteryServices.BatteryService;
import ServiceAgentPepper.HumanServices.HumanService;
import ServiceAgentPepper.LocationServices.LocationService;
import ServiceAgentPepper.MovementServices.MovementService;
import ServiceAgentPepper.StateServices.StateService;
import ServiceAgentPepper.VoiceServices.VoiceService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class RobotProviderAgent extends ServiceProviderBESA {
    
    private static AgLocalHandlerBESA agh;
    public static String servActividades="servActividades";
    public static String servAutonomia="servAutonomia";
    public static String servBateria="servBateria";
    public static String servHumanos="servHumanos";
    public static String servLocation="servLocation";
    public static String servMovimiento="servMovimiento";
    public static String servEstado="servEstado";
    public static String servVoz="servVoz";
    private static PepperAdapter adapterP;
    
    public RobotProviderAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias,prepareServiceProvider(),ServiceProviderBESA.getDefaultStruct() ,0.96);
        adapterP.setRpa(this);
        System.out.println("RobotProviderAgent Iniciado");
   }

    private static StateServiceProvider prepareServiceProvider()
    {
        StateServiceProvider estado=null;
        try {
            adapterP = new PepperAdapter();
            estado = new StateServiceProvider(adapterP,buildProviderDescriptor());
            
        } catch (ServiceProviderAgentExceptionBESA ex) {
            Logger.getLogger(RobotProviderAgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RobotProviderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        }
    private static ServiceProviderDescriptor buildProviderDescriptor() throws ServiceProviderAgentExceptionBESA
    {
        ServiceProviderDescriptor spd =new ServiceProviderDescriptor();
           ActivityService as= new ActivityService();
           as.setName(servActividades);
           AutonomyService autos= new AutonomyService();
           autos.setName(servAutonomia);
           BatteryService bs= new BatteryService();
           bs.setName(servBateria);
           HumanService hs= new HumanService();
           hs.setName(servHumanos);
           LocationService ls= new LocationService();
           ls.setName(servLocation);
           MovementService ms= new MovementService();
           ms.setName(servMovimiento);
           StateService ss= new StateService();
           ss.setName(servEstado);
           VoiceService vs= new VoiceService();
           vs.setName(servVoz);
           spd.addSPService(as);
           spd.addSPService(autos);
           spd.addSPService(bs);
           spd.addSPService(hs);
           spd.addSPService(ls);
           spd.addSPService(ms);
           spd.addSPService(ss);
           spd.addSPService(vs);
        
        return spd;
    }
}
