package rational.services;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import BESA.Kernel.Social.ServiceProvider.agent.StateServiceProvider;

public class ActivateAsynchronousServiceGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        ActivateServiceData data = (ActivateServiceData) event.getData();
        StateServiceProvider state = (StateServiceProvider)this.getAgent().getState();
        Thread t1 = new Thread("nuevo"){
        @Override
        public void run(){
            AsynchronousService servicio = (AsynchronousService) state.getDescriptor().getServiceAccessTable().get(data.getServiceName());
            System.out.println("Enviando Solicitud");
            servicio.executeAsyncService((SPServiceDataRequest) data, state.getAdapter(), state.getAgentsGuardsTableAsync());
        }};
        t1.start();
    }

}
