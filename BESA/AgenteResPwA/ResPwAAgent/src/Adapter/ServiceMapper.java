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
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.ArrayList;
import java.util.Arrays;
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
    protected List<String> keyvals;
    protected ServiceMapper(){
        initServiceMapper();
        uploadServiceRelation();
    }
    private void initServiceMapper(){
        mapper= new ConcurrentHashMap<>();
        keyvals= new ArrayList<>();
        Arrays.asList(ActivityServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(AutonomyServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(EnergyServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(HumanServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(LocationServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(MovementServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(RobotStateServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(TabletServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });
        Arrays.asList(VoiceServiceRequestType.values()).stream().forEach((e)->{
                keyvals.add(e.getServiceType());
        });

    }

    public Map<String, M> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, M> mapper) {
        this.mapper = mapper;
    }
    public void printMapper()
    {
        mapper.keySet().stream().forEach((keyval) -> {
//            System.out.println("K: "+keyval+"V:"+mapper.get(keyval));
        });
    }
    
    protected abstract void  uploadServiceRelation();
    public abstract M getServiceTranslation(String object);
        
}
