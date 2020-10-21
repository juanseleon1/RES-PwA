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
        Thread t1 = new Thread("nuevo");
        t1.start();
        if(Thread.currentThread().getName().equals("nuevo")){
            AsynchronousService servicio = (AsynchronousService) state.getDescriptor().getServiceAccessTable().get(data.getServiceName());
            servicio.executeAsyncService((SPServiceDataRequest) data, state.getAdapter(), state.getAgentsGuardsTableAsync());
        }
    }

}
