package rational.services;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;

public class ActivateServiceData extends SPServiceDataRequest{

    String serviceName;

    public ActivateServiceData(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
