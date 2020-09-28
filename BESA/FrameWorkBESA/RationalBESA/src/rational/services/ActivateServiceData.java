package rational.services;

import BESA.Kernel.Agent.Event.DataBESA;

public class ActivateServiceData extends DataBESA{

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
