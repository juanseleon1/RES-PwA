package rational;

import BESA.Kernel.Agent.StateBESA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.mapping.Believes;

public class RationalState extends StateBESA {

    public enum TYPE{
        SYNCHRONIC,
        ASYNC;
    }
    Believes believes;
    RationalRole mainRole;
    Map<String, String> SPservices;
    Map<String, List<Class> > syncronicServices;
    Map<String, List<Class> > asyncronicServices;
    List<String> subscriptionsToUpdate;

    
    public RationalState() {
        super();
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }
    
    public RationalState(Believes believes) {
        super();
        this.believes = believes;
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }
    
    
    public RationalState(Believes believes, RationalRole mainRole) {
        super();
        this.believes = believes;
        this.mainRole = mainRole;
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    public RationalRole getMainRole() {
        return mainRole;
    }

    public Believes getBelieves() {
        return believes;
    }

    public void setBelieves(Believes believes) {
        this.believes = believes;
    }

    public void assignMainRole(RationalRole mainRole) {
        if (mainRole != null) {
            this.mainRole = mainRole;
        }
    }

    public List<String> getSubscriptionsToUpdate() {
        return subscriptionsToUpdate;
    }
    
    public void setMainRole(RationalRole mainRole) {
        this.mainRole = mainRole;
    }

    public Map<String, List<Class>> getAsyncronicServices() {
        return asyncronicServices;
    }

    public void setAsyncronicServices(Map<String, List<Class>> asyncronicServices) {
        this.asyncronicServices = asyncronicServices;
    }

    public Map<String, List<Class>> getSyncronicServices() {
        return syncronicServices;
    }

    public void setSyncronicServices(Map<String, List<Class>> syncronicServices) {
        this.syncronicServices = syncronicServices;
    }

    public Map<String, String> getSPservices() {
        return SPservices;
    }

    public void setSPservices(Map<String, String> SPservices) {
        this.SPservices = SPservices;
    }
    
    public void subscribeGuardToUpdate(String guardName){
        this.subscriptionsToUpdate.add(guardName);
    }

    public void addService(String service, String sp, TYPE type, Class guard){
        this.SPservices.put(service, sp);
        if(type == TYPE.SYNCHRONIC){
            if(this.syncronicServices.containsKey(sp)){
                this.syncronicServices.get(sp).add(guard);
            }else{
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.syncronicServices.put(sp, guards);
            }
        }else {
            if(this.asyncronicServices.containsKey(sp)){
                this.asyncronicServices.get(sp).add(guard);
            }else{
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.asyncronicServices.put(sp, guards);
            }
        }
    }
    

}
