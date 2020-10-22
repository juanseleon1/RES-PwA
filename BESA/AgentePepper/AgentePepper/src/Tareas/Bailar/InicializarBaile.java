/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Bailar;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderRequest;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataRequest;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import ServiceAgentResPwA.RobotSPAgent;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import rational.services.ActivateAsynchronousServiceGuard;

/**
 *
 * @author mafegarces
 */
public class InicializarBaile extends ResPwaTask{
    
    //revisa el espacio para que no se choque

    public InicializarBaile() {
        System.out.println("--- Task Cambiar Baile Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
            System.out.println("--- Execute Task Cambiar Baile ---");

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cambiar Baile ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cambiar Baile---");
    }
    
}
