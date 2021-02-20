/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ActivityServices.ActivityServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EvaluarEstrategiaEnriquecer extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EvaluarEstrategiaEnriquecer() {
//        System.out.println("--- Task Ejecutar Enriquecer Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Enriquecer ---");
        
        //luces, pantalla, todos los recursos
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        if(blvs.getbEstadoInteraccion().isQuiereEnriquec() || blvs.getbEstadoEmocionalPwA().getTiempoSinAtencion() > 10)
        {
            long num = blvs.getbEstadoInteraccion().getNivelEnriquecimiento();
        
            EnriquecerStrategy es = new EnriquecerStrategy();
            es.setNombre((int)num);
            blvs.getbEstadoActividad().setEstrategia(es);
        
            ServiceDataRequest srb = es.execStrategy(parameters);
            requestService(srb,blvs);
        }        
        
        //propiedades voz(tono,etc) en blvs
        //plan mostrar emociones pepper
        //enriquecimiento: pre cargar enriquecimiento o no, act: si no pone atencion->¿enriquecemos o no?, cont:que tan distraido
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Enriquecer ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;        
        
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
        if(blvs.getbEstadoInteraccion().isEstaMoviendo()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, null);
            requestService(srb,blvs);
        }
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.HIDEIMG, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Enriquecer ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoActividad().setEstrategia(null);
        if(blvs.getbEstadoInteraccion().isEstaHablando()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.STOPALL, null);
            requestService(srb,blvs);
        }
        if(blvs.getbEstadoInteraccion().isEstaMoviendo()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(ActivityServiceRequestType.STOPANIMATION, null);
            requestService(srb,blvs);
        }
        if(blvs.getbEstadoInteraccion().isConfirmacionRepDisp()) {
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.HIDEIMG, null);
            requestService(srb,blvs);
        }
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(blvs.getbEstadoActividad().getEstrategia()!=null && blvs.getbEstadoActividad().getEstrategia() instanceof EnriquecerStrategy && !blvs.getbEstadoInteraccion().isEstaHablando() && !blvs.getbEstadoInteraccion().isEstaMoviendo() && 
                !blvs.getbEstadoInteraccion().isConfirmacionRepDisp() && blvs.getbEstadoInteraccion().getLeds() != null ){
            return true;
        }
        return false;
    }
    
}
