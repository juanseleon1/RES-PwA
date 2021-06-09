/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentResPwA.Agent;

import Adapter.ResPwaAdapter;
import BESA.ExceptionBESA;
import PepperPackage.Utils.PepperAdapter;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderRequest;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderSuscribe;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderAgentExceptionBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDescriptor;
import BESA.Kernel.Social.ServiceProvider.agent.StateServiceProvider;
import BESA.Local.Directory.AgLocalHandlerBESA;
import BESA.Log.ReportBESA;
import ServiceAgentResPwA.ActivityServices.ActivityService;
import ServiceAgentResPwA.AutonomyServices.AutonomyService;
import ServiceAgentResPwA.EnergyServices.EnergyService;
import ServiceAgentResPwA.HumanServices.HumanService;
import ServiceAgentResPwA.LocationServices.LocationService;
import ServiceAgentResPwA.MovementServices.MovementService;
import ServiceAgentResPwA.RobotStateServices.RobotStateService;
import ServiceAgentResPwA.TabletServices.TabletService;
import ServiceAgentResPwA.VoiceServices.VoiceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author juans
 */
public class ServiceAgentRESPwA extends ServiceProviderBESA {
    
    private static AgLocalHandlerBESA agh;
    //Se encarga de animaciones
    public static String servActividades="servActividades";
    //Control de funciones autonomas
    public static String servAutonomia="servAutonomia";
    //Aagar*prender/susenderrobot
    public static String servEnergia="servEnergia";
    //Pedir informacion sobre el PwA, incluye login.
    public static String servHumanos="servHumanos";
    //Manejo de modelo del mundo y ubicacion del PwA
    public static String servLocation="servLocation";
    //Solicitud de movimiento del robot.
    public static String servMovimiento="servMovimiento";
    //Manejo de estado emocional del robot.
    public static String servEstadoRobot="servEstadoRobot";
    //Solicitudes de Voz
    public static String servVoz="servVoz";
    //Solicitudes de tablet
    public static String servTablet="servTablet";
    private ResPwaAdapter adapter;
    
    private ServiceAgentRESPwA(String alias, ResPwaAdapter adapter, StateServiceProvider ssp) throws KernelAgentExceptionBESA {
        super(alias,ssp,getDefaultStruct() ,0.96);
        this.adapter=adapter;
        this.adapter.setRpa(this);
        System.out.println("RobotProviderAgent Iniciado");
   }
    
        public static StructBESA getDefaultStruct() {
        try {
            StructBESA struct = ServiceProviderBESA.getDefaultStruct();
            struct.addBehavior("BehaviorAsyncServiceProvider");
            struct.bindGuard("BehaviorAsyncServiceProvider", ActivateAsynchronousServiceGuard.class);
            return struct;
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
            return null;
        }
    }
    public static ServiceAgentRESPwA buildRobotSPAgent(String alias, ResPwaAdapter adapter) throws KernelAgentExceptionBESA
    {
     return new ServiceAgentRESPwA(alias,adapter,prepareServiceProvider(adapter));
    }
    private static StateServiceProvider prepareServiceProvider(ResPwaAdapter adapter)
    {
        StateServiceProvider estado=null;
        try {
            estado = new StateServiceProvider(adapter,buildProviderDescriptor());
        } catch (ServiceProviderAgentExceptionBESA ex) {
            Logger.getLogger(ServiceAgentRESPwA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServiceAgentRESPwA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
    @Override
    public void setupAgent() {
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servActividades);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servAutonomia);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servEnergia);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servHumanos);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servLocation);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servMovimiento);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servEstadoRobot);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servVoz);
        this.getAdmLocal().bindSPServiceInDirectory(this.getAid(), servTablet);
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
           EnergyService bs= new EnergyService();
           bs.setName(servEnergia);
           HumanService hs= new HumanService();
           hs.setName(servHumanos);
           LocationService ls= new LocationService();
           ls.setName(servLocation);
           MovementService ms= new MovementService();
           ms.setName(servMovimiento);
           RobotStateService ss= new RobotStateService();
           ss.setName(servEstadoRobot);
           VoiceService vs= new VoiceService();
           vs.setName(servVoz);
           TabletService ts= new TabletService();
           ts.setName(servTablet);
           spd.addSPService(as);
           spd.addSPService(autos);
           spd.addSPService(bs);
           spd.addSPService(hs);
           spd.addSPService(ls);
           spd.addSPService(ms);
           spd.addSPService(ss);
           spd.addSPService(vs);
           spd.addSPService(ts);
        
        return spd;
    }
}
