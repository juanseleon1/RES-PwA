/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tareas.ConfiguracionInicial;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Accion;
import ResPwAEntities.Perfilpwa;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author maria.f.garces.cala
 */
public class CreacionJSON extends ResPwaTask{

    private HashMap<String,Object> infoServicio = new HashMap<>();
    private HashMap<String,List<Accion>> json = new HashMap<>();

    public CreacionJSON() {
//        System.out.println("--- Task Creacion JSON Iniciada ---");
    }

@Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Creacion JSON ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;

        fillJSON();
        infoServicio.put("INITIALCONF", this.json);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.INITIALCONF, infoServicio);
        requestService(srb,blvs);
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Creacion JSON ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Creacion JSON ---");

    }

    @Override
    public boolean checkFinish(Believes believes) {
        return false;
    }

    public void fillJSON(){
        List<Accion> acciones = RESPwABDInterface.getAcciones();
        List<String> tipos = new ArrayList<>();
        List<Accion> accionxtipo = new ArrayList<>();
        Set<String> uniqueTipos = new HashSet<>();

        for(Accion a: acciones){
          tipos.add(a.getTipo());
        }

        uniqueTipos = new HashSet<String>(tipos);

        for (String t: uniqueTipos){
          this.json.put(t, new ArrayList<>());
        }

        for (String i : this.json.keySet()) {
          for (Accion a: acciones){
            if(i.equals(a.getTipo())){
              accionxtipo = this.json.get(i);
              accionxtipo.add(a);
              this.json.put(i, accionxtipo);
            }
          }
        }
    }

}
