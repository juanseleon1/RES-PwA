/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCancion extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public SeleccionarCancion() {
//        System.out.println("--- Task Seleccionar Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");

        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        blvs.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("SAY", "Estoy seleccionando una cancion");
        ServiceDataRequest data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        requestService(data, blvs);
        
        hm = new HashMap<>();
        hm.put("SAY", "La changua no deberia existir");
         data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        requestService(data, blvs);
        float gusto = -1;
        Cancion cancionEleg = null;
        List<Cancion> canciones = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getCancionList();
        for (Cancion c : canciones) {
            if (c.getGusto() * 0.7 + c.getGeneroGenero().getGusto() * 0.3 <= gusto) {
                cancionEleg = c;
                gusto = (float) (c.getGusto() * 0.7 + c.getGeneroGenero().getGusto() * 0.3);
            }
        }
        blvs.getbEstadoActividad().setCancionActual(cancionEleg);
        //falta seleccionar si se va a utilizar: mostrarFotos o activarLetra
    }
    
    public Cancion mejorCancionParaColocar(List<Cancion> canciones){
        
        Cancion cancionParaColocar = null;
//        Se busca en la lista cual tiene la mejor probabilidad para colocar esa cancion
        for( int i=0; i < canciones.size(); i++ ){
            
        }
        return cancionParaColocar;
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setCancionActual(null);
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoActividad().getCancionActual() != null) {
            return true;
        }
        return false;
    }

}
