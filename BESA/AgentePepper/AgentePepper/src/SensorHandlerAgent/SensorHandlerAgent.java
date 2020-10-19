/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderSuscribe;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataSuscribe;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import static RobotAgentBDI.RobotAgentBDI.PERIODIC_TIME;
import ServiceAgentPepper.RobotProviderAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class SensorHandlerAgent extends AgentBESA {
public static String GetInfoGuard= "GetInfoGuard";
    public SensorHandlerAgent(String alias) throws KernelAgentExceptionBESA {
        super(alias, new SensorHandlerState(), buildSensorHandlerStruct(), 0.96);
        System.out.println("SensorHandlerAgent Iniciado");
    }

    @Override
    public void setupAgent() {
        
    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static StructBESA buildSensorHandlerStruct()
    {
         StructBESA struct=new StructBESA();
        try {
            struct.addBehavior("GetInfoGuard");
            struct.bindGuard(GetInfoGuard, GetInfoGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SensorHandlerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return struct;
    }
    
 

    public void subscribeServices() throws ExceptionBESA {
        String spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servHumanos);
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
            ServiceProviderDataSuscribe spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servHumanos,
                    SensorData.class.getName());
            //Crea el evento a enviar
            EventBESA evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);

            /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servActividades);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servActividades,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servAutonomia);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servAutonomia,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servBateria);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servBateria,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servLocation);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servLocation,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servMovimiento);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servMovimiento,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servEstado);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servEstado,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
             /////////////////////////////////////////////////////////////////////////////////////////////////
             spAgId = AdmBESA.getInstance().lookupSPServiceInDirectory(RobotProviderAgent.servVoz);
             agH = AdmBESA.getInstance().getHandlerByAid(spAgId);
            //Crea el data de suscripcion
             spDataSuscribe = new ServiceProviderDataSuscribe(
                    GetInfoGuard.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    RobotProviderAgent.servVoz,
                    SensorData.class.getName());
            //Crea el evento a enviar
             evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Env�a el evento
            agH.sendEvent(evSP);
            
    }
}
