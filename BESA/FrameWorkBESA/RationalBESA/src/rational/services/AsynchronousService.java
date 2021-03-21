package rational.services;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPInfoGuard;
import BESA.Kernel.Social.ServiceProvider.agent.SPService;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import java.util.ArrayList;
import java.util.Map;

public abstract class AsynchronousService extends SPService{

    @Override
    public DataBESA executeService(SPServiceDataRequest data, AdapterBESA adapter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract void executeAsyncService(SPServiceDataRequest data, AdapterBESA adapter, Map<String, ArrayList<SPInfoGuard> > subscribeAgents);

}
