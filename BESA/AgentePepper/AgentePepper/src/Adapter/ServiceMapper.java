/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.AutonomyServices.AutonomyServiceRequestType;
import ServiceAgentResPwA.EnergyServices.EnergyServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.LocationServices.LocationServiceRequestType;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceRequestType;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author juans
 * @param <M> tipoDeRetorno
 */
public abstract class ServiceMapper<M> {
    protected Map<String,M> mapper;
    protected ServiceMapper(){
        initServiceMapper();
        uploadServiceRelation();
    }
    private void initServiceMapper(){
        mapper= new ConcurrentHashMap<>();
        List<String> listEnum= new ArrayList<>();
        Arrays.asList(ActivityServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(AutonomyServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(EnergyServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(HumanServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(LocationServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(MovementServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(RobotStateServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(TabletServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });
        Arrays.asList(VoiceServiceRequestType.values()).stream().forEach((e)->{
                mapper.put(e.getServiceType(), null);
        });

    }

    public Map<String, M> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, M> mapper) {
        this.mapper = mapper;
    }
    
    protected abstract void  uploadServiceRelation();
    public abstract M getServiceTranslation(String object);
        
}
