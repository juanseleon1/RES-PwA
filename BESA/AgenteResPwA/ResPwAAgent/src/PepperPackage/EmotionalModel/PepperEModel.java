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
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import SensorHandlerAgent.SensorData;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import Tareas.Cuenteria.LedsColor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperEModel extends EmotionalModel {

    private double state;
    private final double refreshRate;
    private final double normalState;
    private final double speechBase = 1.1;
    private final double speechVBase = 100;
    private final double ledsIntBase = 1;
    private double velf=1;
    private double velh=100;
    private double pitch=1.1;
    private double ledInt=1;
    private final double ledRotVel=2;
    private static final double CHANGE_FACT = 0.3;
    private LedsColor lc;

    public PepperEModel(double normalState) {
        this.state = normalState;
        this.refreshRate = 0;
        this.normalState = normalState;
    }

    @Override
    public Map<String, Object> filterFromEM(Map<String, Object> map) {
        Map<String, Object> map2 = new HashMap<>();
        map2.putAll(map);
        double velf1 = 0, velh1 = 0, pitch1 = 0, ledInt1 = 0, ledRotVel1 = 0;
        String s = "";
        if (map2.containsKey(PepperEMParams.HVel.getTipo())) {
            velh1 = (double) map2.get(PepperEMParams.HVel.getTipo());
            velh1 += ((velh - velh1) * CHANGE_FACT);
            map2.replace(PepperEMParams.HVel.getTipo(), velh1);
        }
        if (map2.containsKey(PepperEMParams.FVel.getTipo())) {
            velf1 = (double) map2.get(PepperEMParams.FVel.getTipo());
            velf1 += ((velf - velf1) * CHANGE_FACT);
            map2.replace(PepperEMParams.FVel.getTipo(), velf1);
        }
        if (map2.containsKey(PepperEMParams.LEDINT.getTipo())) {
            ledInt1 = (double) map2.get(PepperEMParams.LEDINT.getTipo());
            ledInt1 += ((ledInt - ledInt1) * CHANGE_FACT);
            map2.replace(PepperEMParams.LEDINT.getTipo(), ledInt1);
        }
        if (map2.containsKey(PepperEMParams.TONOH.getTipo())) {
            pitch1 = (double) map2.get(PepperEMParams.TONOH.getTipo());
            pitch1 += ((pitch - pitch1) * CHANGE_FACT);
            map2.replace(PepperEMParams.TONOH.getTipo(), pitch1);
        }
        if (map2.containsKey(PepperEMParams.ANIMSTATE.getTipo())) {
            s = (String) map2.get(PepperEMParams.ANIMSTATE.getTipo());
            map2.replace(PepperEMParams.ANIMSTATE.getTipo(), s);
        }
        if (map2.containsKey(PepperEMParams.LEDROT.getTipo())) {
            ledRotVel1 = (double) map2.get(PepperEMParams.LEDROT.getTipo());
            ledRotVel1 += ((ledRotVel - ledRotVel1) * CHANGE_FACT);
            map2.replace(PepperEMParams.LEDROT.getTipo(), ledRotVel1);
        }
        return map2;
    }

//    protected enum EmoTypes{
//        EASE(0),SMILE(0),JOY(0),SORROW(0),EXCT(0),CALM(0),ANGER(0),SURP(0),LAUGH(0),VALEN(0),ATTENT(0);
//        private double perc;
//        private EmoTypes(double perc){
//            this.perc=perc;
//        }
//    };
    @Override
    public void updateModel() {
        try {
            double act = refreshRate, dif = normalState - state, fact = 1;
            if (dif < 0) {
                fact *= -1;
            }
            if (dif < act) {
                act = dif;
            }
            act *= fact;
            if (dif != 0) {
                state += act;
            }
            EmotionalData e = new EmotionalData();
            Map<String, Object> map = e.getInfo();
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
            EmotionalData e = new EmotionalData();
            Map<String, Object> map = e.getInfo();
            calcNewEmotionalParams(map, sd);
            e.setInfo(map);
            sendAct(e);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PepperEModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calcNewEmotionalParams(Map<String, Object> map, SensorData sd) {
        Map<String, Object> pe = sd.getDataPE(), aux, auxEmo;
        aux = (Map<String, Object>) pe.get("bodyLanguageState");
        aux = (Map<String, Object>) aux.get("ease");
        double relval = (double) aux.get("level") * (double) aux.get("confidence");
//        System.out.println("Es esta: "+pe);
        aux = (Map<String, Object>) pe.get("smile");
        double smval = (double) aux.get("confidence") * (double) aux.get("value");
        aux = (Map<String, Object>) pe.get("expressions");
        auxEmo = (Map<String, Object>) aux.get("joy");
        double joyval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("sorrow");
        double sowval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("excitement");
        double excval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("calm");
        double calval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("anger");
        double angval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("surprise");
        double surval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        auxEmo = (Map<String, Object>) aux.get("laughter");
        double lauval = (double) auxEmo.get("confidence") * (double) auxEmo.get("value");
        aux = (Map<String, Object>) pe.get("valence");
        double valval = (double) aux.get("confidence") * (double) aux.get("value");
        aux = (Map<String, Object>) pe.get("attention");
        double atval = (double) aux.get("confidence") * (double) aux.get("value");

        EmotionPwA emoP = null;
        if (angval >= joyval && angval >= sowval) {
            emoP = EmotionPwA.ANGER;
        } else if (angval <= joyval && joyval >= sowval) {
            emoP = EmotionPwA.HAPPINESS;
        } else if (sowval >= joyval && angval <= sowval) {
            emoP = EmotionPwA.SADNESS;
        }

        map.put("relajacion", relval);
        map.put("atencion", atval);
        map.put("predEm", emoP);
        calcNewEmotionalParams(map);
    }

    private void calcNewEmotionalParams(Map<String, Object> map) {
        if (state >= LedsColor.BLUE.getMin() && state < LedsColor.BLUE.getMax()) {
            lc = LedsColor.BLUE;
        } else if (state >= LedsColor.PURPLE.getMin() && state < LedsColor.PURPLE.getMax()) {
            lc = LedsColor.PURPLE;
        } else if (state >= LedsColor.GREEN.getMin() && state < LedsColor.GREEN.getMax()) {
            lc = LedsColor.GREEN;
        } else if (state >= LedsColor.RED.getMin() && state < LedsColor.RED.getMax()) {
            lc = LedsColor.RED;
        } else if (state >= LedsColor.YELLOW.getMin() && state <= LedsColor.YELLOW.getMax()) {
            lc = LedsColor.YELLOW;
        }
       
        lc = LedsColor.WHITE;
        velf = 0;
        velh = (speechVBase * state) / normalState;
        pitch = (speechBase * state) / normalState;
        ledInt = (speechVBase * state) / normalState;
        //x= baseV*actual/baseE
        map.put("factorVelocidad", velf);
        map.put("velHabla", velh);
        map.put("tonoHabla", pitch);
        map.put("ledIntens", ledInt);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.putAll(map);
        map2.put("COLOR", lc.getHexa());
        map2.put("DURATION",ledRotVel);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.ROBOTEMOTION, (HashMap<String, Object>) map2);
        requestService(srb);
    }

    public double getState() {
        return state;
    }

}
