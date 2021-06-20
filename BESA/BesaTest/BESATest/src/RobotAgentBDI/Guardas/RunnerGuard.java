package RobotAgentBDI.Guardas;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import besatest.BESATest;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.InformationFlowGuard;

/**
 *
 * @author usuario
 */
public class RunnerGuard extends PeriodicGuardBESA{

    @Override
    public void funcPeriodicExecGuard(EventBESA ebesa) {
        try{
            AgHandlerBESA handlerByAlias = null; //definicion del manejador del agente
            EventBESA  eventBESA= new EventBESA(InformationFlowGuard.class.getName(),null);
            handlerByAlias = AdmBESA.getInstance().getHandlerByAlias(BESATest.aliasRobotAgent);  
            handlerByAlias.sendEvent(eventBESA);

        }catch (ExceptionBESA ex){
            Logger.getLogger(RunnerGuard.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}