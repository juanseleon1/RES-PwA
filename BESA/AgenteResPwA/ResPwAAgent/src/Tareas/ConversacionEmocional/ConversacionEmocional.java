/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.ConversacionEmocional;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.PepperTopicsNames;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import rational.mapping.Believes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author LaMafecitaBebeLean
 */
public class ConversacionEmocional extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public ConversacionEmocional() {
//        System.out.println("--- Task Revisar Perfil Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Conversacion Emocional ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        String emotionalTalk = blvs.getbEstadoInteraccion().getEstadoEmocional();
        String resulset[] = emotionalTalk.split(" ");
        String result = "";
        //Pasa el resto del string
        for(int i = 1; i< resulset.length; i++){
            result = result + resulset[i] + " ";
        }
        ServiceDataRequest srb = null;
        if (resulset != null){
            
            if (resulset[0] == "happy"){
                activateTopic( PepperTopicsNames.ALEGRETOPIC, blvs);
                //Cambiar algun signal que no se donde se mete
            }
            if (resulset[0] == "sad"){
                activateTopic( PepperTopicsNames.SADTOPIC, blvs);
            }
            if (resulset[0] == "angry"){
                activateTopic( PepperTopicsNames.IRATOPIC, blvs);
            }
            if (resulset[0] == "normal"){
                activateTopic( PepperTopicsNames.NORMALTOPIC, blvs);
            }
            //infoServicio.put("FALTA SABER QUE METER ACA", emotionalTalk);
            //srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            
        }
        requestService(srb,blvs);

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Conversacion Emocional ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Conversacion Emocional ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
                super.checkFinish(believes);

        return false;
    }

}
