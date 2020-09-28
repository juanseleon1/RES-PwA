package rational;

import BESA.Kernel.Agent.StateBESA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import java.util.UUID;

public class RationalState extends StateBESA {

    public enum TYPE {
        SYNCHRONIC,
        ASYNC;
    }

    private Believes believes;
    private Map<String, String> SPservices;
    private LinkedList<RationalRole> roleList;
    private List<String> subscriptionsToUpdate;
    private Map<String, WorkerAgent> workerTable;
    private Map<String, List<Class>> syncronicServices;
    private Map<String, List<Class>> asyncronicServices;

    public RationalState() {
        this.roleList = new LinkedList<>();
        this.workerTable = new HashMap<>();
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    public RationalState(Believes believes, RationalRole mainRole) {
        super();
        this.believes = believes;
        this.roleList = new LinkedList<>();
        this.workerTable = new HashMap<>();
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    public RationalRole getMainRole() {
        return !roleList.isEmpty() ? roleList.getFirst() : null;
    }

    public Believes getBelieves() {
        return believes;
    }

    public void setBelieves(Believes believes) {
        this.believes = believes;
    }

    public void assignMainRole(RationalRole mainRole) {
        if (mainRole != null) {
            roleList.add(mainRole);
        }
    }

    public List<String> getSubscriptionsToUpdate() {
        return subscriptionsToUpdate;
    }

    public void setMainRole(RationalRole mainRole) {
        roleList.add(mainRole);
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

    public void subscribeGuardToUpdate(String guardName) {
        if (!subscriptionsToUpdate.contains(guardName)) {
            this.subscriptionsToUpdate.add(guardName);
        }
    }

    public void addService(String service, String sp, TYPE type, Class guard) {
        this.SPservices.put(service, sp);
        if (type == TYPE.SYNCHRONIC) {
            if (this.syncronicServices.containsKey(sp)) {
                this.syncronicServices.get(sp).add(guard);
            } else {
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.syncronicServices.put(sp, guards);
            }
        } else {
            if (this.asyncronicServices.containsKey(sp)) {
                this.asyncronicServices.get(sp).add(guard);
            } else {
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.asyncronicServices.put(sp, guards);
            }
        }
    }

    public void updateRoleList(List<RationalRole> activeRoleList) {
        for (int i = 0; i < activeRoleList.size(); i++) {
            RationalRole r = activeRoleList.get(i);
            if (!roleList.contains(r)) {
                roleList.add(r);
            }
        }
    }

    public LinkedList<RationalRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(LinkedList<RationalRole> roleList) {
        this.roleList = roleList;
    }

    public WorkerAgent getWorker(String roleName) {
        return workerTable.get(roleName);
    }

    public void initPool(List<String> roleNameList, String agentAlias) {
        WorkerAgent agent;
        WorkerState stW;
        for (String name : roleNameList) {
            try {
                stW = new WorkerState(this.believes, agentAlias);
                //agent = new WorkerAgent(name + agentAlias, stW);
                //agent = new WorkerAgent(name, stW);
                agent = new WorkerAgent(UUID.randomUUID().toString(), stW);                
                agent.start();
                workerTable.put(name, agent);
            } catch (KernelAgentExceptionBESA ex) {
                Logger.getLogger(RationalState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
