/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

import BESA.ExceptionBESA;
import EmotionalAnalyzerAgent.EmotionPwA;
import EmotionalAnalyzerAgent.EmotionalData;
import EmotionalAnalyzerAgent.EmotionalModel;
import SensorHandlerAgent.SensorData;
import Tareas.CambiarEnriquecimientoHistoria.LedsColor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperEModel extends EmotionalModel{
    private double state;
    private double refreshRate;
    private double normalState;
    private double speechBase=1.1;
    private double speechVBase=100;
    private double ledsIntBase=1;

    public PepperEModel(double normalState) {
        this.state = normalState;
        this.refreshRate = 0;
        this.normalState = normalState;
    }
    
    protected enum EmoTypes{
        EASE(0),SMILE(0),JOY(0),SORROW(0),EXCT(0),CALM(0),ANGER(0),SURP(0),LAUGH(0),VALEN(0),ATTENT(0);
        private double perc;
        private EmoTypes(double perc){
            this.perc=perc;
        }
    };
    
    @Override
    public void updateModel() {
        try {
            double act=refreshRate, dif=normalState-state,fact=1;
            if(dif<0)
                fact*=-1;
            if(dif<act)
                act=dif;
            act*=fact;
            if(dif!=0)
            {
                state+=act;
            }   EmotionalData e= new EmotionalData();
            Map<String,Object> map= e.getInfo();
            calcNewEmotionalParams(map);
            e.setInfo(map);
            sendAct(e);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PepperEModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updtModelFromEvt(SensorData sd) {
        try {
            EmotionalData e= new EmotionalData();
            Map<String,Object> map= e.getInfo();
            calcNewEmotionalParams(map,sd);
            e.setInfo(map);
            sendAct(e);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PepperEModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    private void calcNewEmotionalParams(Map<String, Object> map, SensorData sd) {
        Map<String, Object> pe = sd.getDataPE(),aux,auxEmo;
        aux=(Map<String, Object>) pe.get("bodyLanguageState");
        aux=(Map<String, Object>) aux.get("ease");
        double relval=(double) aux.get("level")*(double) aux.get("confidence");
        System.out.println("Es esta: "+pe);
        aux=(Map<String, Object>) pe.get("smile");
        double smval=(double) aux.get("confidence")*(double) aux.get("value");
        aux=(Map<String, Object>) pe.get("expressions");
        auxEmo=(Map<String, Object>) aux.get("joy");
        double joyval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("sorrow");
        double sowval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("excitement");
        double excval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("calm");
        double calval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("anger");
        double angval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("surprise");
        double surval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        auxEmo=(Map<String, Object>) aux.get("laughter");
        double lauval=(double) auxEmo.get("confidence")*(double) auxEmo.get("value");
        aux=(Map<String, Object>) pe.get("valence");
        double valval=(double) aux.get("confidence")*(double) aux.get("value");
        aux=(Map<String, Object>) pe.get("attention");
        double atval=(double) aux.get("confidence")*(double) aux.get("value");
        
        EmotionPwA emoP=null;
        if(angval>=joyval && angval>=sowval)
            emoP=EmotionPwA.ANGER;
        else if(angval<=joyval && joyval>=sowval)
            emoP=EmotionPwA.HAPPINESS;
        else if(sowval>=joyval && angval<=sowval)
            emoP=EmotionPwA.SADNESS;
        
        double change;
        map.put("relajacion",relval);
        map.put("atencion",atval);
        map.put("predEm", emoP);
        calcNewEmotionalParams(map);
    }//mariela angela
    private void calcNewEmotionalParams(Map<String, Object> map) {
        LedsColor lc=null;
         if(state>=-1 && state<-0.6)
         {
             lc=LedsColor.BLUE;
         }else if(state>=-0.6&& state<-0.2)
         {
             lc=LedsColor.BLGRE;
         }else if(state>=-0.2 && state<0.2)
         {
             lc=LedsColor.GREEN;
         }else if(state>=0.2 && state<0.6)
         {
             lc=LedsColor.GREYEL;
         }else if(state>=0.6 && state<=1)
         {
             lc=LedsColor.YELLOW;
         }
         float velf=0,velh=0,pitch=0,ledInt=0;
         
        map.put("LEDS", lc.name());
        map.put("velocidad", velf);
        map.put("velHabla", velh);
        map.put("tonoHabla", pitch);
        map.put("ledIntens", ledInt);
    }

}
