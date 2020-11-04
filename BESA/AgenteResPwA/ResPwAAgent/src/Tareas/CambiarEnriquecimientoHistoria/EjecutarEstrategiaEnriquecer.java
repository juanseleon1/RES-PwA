/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.CambiarEnriquecimientoHistoria;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwaTask;
import ServiceAgentResPwA.ServiceDataRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class EjecutarEstrategiaEnriquecer extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public EjecutarEstrategiaEnriquecer() {
//        System.out.println("--- Task Ejecutar Enriquecer Iniciada ---");
    }
    

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Ejecutar Enriquecer ---");
        
        //luces, pantalla, todos los recursos
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        ResPwAStrategy estrategia = blvs.getbEstadoActividad().getEstrategia();
        
        ServiceDataRequest srb = estrategia.execStrategy(parameters);
        requestService(srb);
        
        //propiedades voz(tono,etc) en blvs
        //plan mostrar emociones pepper
        //enriquecimiento: pre cargar enriquecimiento o no, act: si no pone atencion->¿enriquecemos o no?, cont:que tan distraido
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Ejecutar Enriquecer ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Ejecutar Enriquecer ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isEstaHablando() && !blvs.getbEstadoInteraccion().isEstaMoviendo() && 
                !blvs.getbEstadoInteraccion().isConfirmacionRepDisp() && blvs.getbEstadoInteraccion().getLeds() != null ){
            return true;
        }
        return false;
    }
    
}
