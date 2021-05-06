/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalData;
import PepperPackage.EmotionalModel.PepperEmotionalModel;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionAxis;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalEvent;
import RobotAgentBDI.Believes.EstadoEmocional.EmotionalModel;
import SensorHandlerAgent.SensorData;
import PepperPackage.EmotionalModel.PepperEmotionRanges;
import PepperPackage.PepperConf;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.RobotStateServices.RobotStateServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoRobot extends PepperEmotionalModel implements Believes {

    private boolean bateria;
    private double batteryPerc;
    private int volumenVoz;
    private double velocidad = 0;
    private boolean libreEntorno = false;
    private boolean activadoMovEscucha = false;
    private boolean activadoConsciente = false;
    private boolean activadoSenalesDeVida = false;
    private boolean activadoMovHabla = false;
    private boolean estaSuspendido = false;
    private boolean conexionInternet = false;
    private long tiempoSinConexionInternet = 0;
    private boolean verificacionDispositivos = true;
    private double velHabla;
    private double tonoHabla;
    private double distanciaX;
    private double distanciaY;
    private double ledIntensity;
    private PepperEmotionRanges leds = null;
    private double brilloRobot = 0;

    public BEstadoRobot() {
        super();
    }

    public void setBrilloRobot(double brilloRobot) {
        this.brilloRobot = brilloRobot;
    }

    public double getBrilloRobot() {
        return brilloRobot;
    }

    @Override
    public boolean update(InfoData si) {

        System.out.println("******Act Estado Robot********* " + si.toString());
        if (si instanceof SensorData) {
            SensorData infoRecibida = (SensorData) si;
            if (infoRecibida.getDataP().containsKey("batteryLow")) {
                bateria = (boolean) infoRecibida.getDataP().get("batteryLow");
            }
            if (infoRecibida.getDataP().containsKey("batteryPerc")) {
                batteryPerc = Double.parseDouble(String.valueOf(infoRecibida.getDataP().get("batteryPerc")));
            }
            if (infoRecibida.getDataP().containsKey("activadoMovEscucha")) {
                activadoMovEscucha = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoMovEscucha"));
            }
            if (infoRecibida.getDataP().containsKey("activadoConsciente")) {
                activadoConsciente = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoConsciente"));
            }
            if (infoRecibida.getDataP().containsKey("activadoSenalesDeVida")) {
                activadoSenalesDeVida = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoSenalesDeVida"));
            }
            if (infoRecibida.getDataP().containsKey("activadoMovHabla")) {
                activadoMovHabla = Boolean.valueOf((String) infoRecibida.getDataP().get("activadoMovHabla"));
            }
            if (infoRecibida.getDataP().containsKey("robotIsWakeUp")) {
                estaSuspendido = Boolean.valueOf((String) infoRecibida.getDataP().get("robotIsWakeUp"));
            }
            if (infoRecibida.getDataP().containsKey("libreEntorno")) {
                libreEntorno = Boolean.valueOf((String) infoRecibida.getDataP().get("libreEntorno"));
            }
            if (infoRecibida.getDataP().containsKey("conexionInternet")) {
                conexionInternet = Boolean.valueOf((String) infoRecibida.getDataP().get("conexionInternet"));
                if (conexionInternet) {
                    tiempoSinConexionInternet = System.currentTimeMillis();
                }
            }
            if (infoRecibida.getDataP().containsKey("hotDeviceDetected")) {
                verificacionDispositivos = Boolean.valueOf((String) infoRecibida.getDataP().get("hotDeviceDetected"));
            }
        } else if (si instanceof EmotionalData) {
            EmotionalData emoDat = (EmotionalData) si;
            
            List<EmotionalEvent> emoEv = emoDat.getEmoEv();
            for (EmotionalEvent emotionalEvent : emoEv) {
                this.processEmotionalEvent(emotionalEvent);
            }
//            if (infoRecibida.getInfo().containsKey("LEDS")) {
//                leds = LedsColor.valueOf((String) infoRecibida.getInfo().get("LEDS"));
//            }
//            if (infoRecibida.getInfo().containsKey("velocidad")) {
//                velocidad = (double) infoRecibida.getInfo().get("velocidad");
//            }
//            if (infoRecibida.getInfo().containsKey("velHabla")) {
//                velHabla = (double) infoRecibida.getInfo().get("velHabla");
//            }
//            if (infoRecibida.getInfo().containsKey("tonoHabla")) {
//                tonoHabla = (double) infoRecibida.getInfo().get("tonoHabla");
//            }
//            if (infoRecibida.getInfo().containsKey("ledIntens")) {
//                ledIntensity = (double) infoRecibida.getInfo().get("ledIntens");
//            }
        }
        return true;
    }

    public boolean getBateria() {
        return bateria;
    }

    public void setBateria(boolean bateria) {
        this.bateria = bateria;
    }

    public boolean isActivadoMovEscucha() {
        return activadoMovEscucha;
    }

    public void setActivadoMovEscucha(boolean activadoMovEscucha) {
        this.activadoMovEscucha = activadoMovEscucha;
    }

    public boolean isActivadoConsciente() {
        return activadoConsciente;
    }

    public void setActivadoConsciente(boolean activadoConsciente) {
        this.activadoConsciente = activadoConsciente;
    }

    public boolean isActivadoSenalesDeVida() {
        return activadoSenalesDeVida;
    }

    public void setActivadoSenalesDeVida(boolean activadoSenalesDeVida) {
        this.activadoSenalesDeVida = activadoSenalesDeVida;
    }

    public boolean isActivadoMovHabla() {
        return activadoMovHabla;
    }

    public void setActivadoMovHabla(boolean activadoMovHabla) {
        this.activadoMovHabla = activadoMovHabla;
    }

    public boolean isEstaSuspendido() {
        return estaSuspendido;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    public int getVolumenVoz() {
        return volumenVoz;
    }

    public void setVolumenVoz(int volumenVoz) {
        this.volumenVoz = volumenVoz;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getVelHabla() {
        return velHabla;
    }

    public void setVelHabla(double velHabla) {
        this.velHabla = velHabla;
    }

    public double getTonoHabla() {
        return tonoHabla;
    }

    public void setTonoHabla(double tonoHabla) {
        this.tonoHabla = tonoHabla;
    }

    public double getDistanciaX() {
        return distanciaX;
    }

    public void setDistanciaX(double distanciaX) {
        this.distanciaX = distanciaX;
    }

    public double getDistanciaY() {
        return distanciaY;
    }

    public void setDistanciaY(double distanciaY) {
        this.distanciaY = distanciaY;
    }

    public boolean isLibreEntorno() {
        return libreEntorno;
    }

    public void setLibreEntorno(boolean libreEntorno) {
        this.libreEntorno = libreEntorno;
    }

    public boolean isConexionInternet() {
        return conexionInternet;
    }

    public boolean isVerificacionDispositivos() {
        return verificacionDispositivos;
    }

    public double getBatteryPerc() {
        return batteryPerc;
    }

    public void setBatteryPerc(double batteryPerc) {
        this.batteryPerc = batteryPerc;
    }

    public double getLedIntensity() {
        return ledIntensity;
    }

    public void setLedIntensity(double ledIntensity) {
        this.ledIntensity = ledIntensity;
    }

    public PepperEmotionRanges getLeds() {
        return leds;
    }

    public void setLeds(PepperEmotionRanges leds) {
        this.leds = leds;
    }

    public long getTiempoSinConexionInternet() {
        return System.currentTimeMillis() - tiempoSinConexionInternet;
    }

    public void setTiempoSinConexionInternet(long tiempoSinConexionInternet) {
        this.tiempoSinConexionInternet = tiempoSinConexionInternet;
    }

    @Override
    public void emotionalStateChanged() {
        try {
            HashMap<String, Object> infoServicio = new HashMap<>();
            EmotionAxis ea = getTopEmotionAxis();
            float state = ea.getCurrentValue();
            leds = PepperEmotionRanges.getFromEmotionalValue(state);
            System.out.println("VALOR DE EMOVAL "+state);
            infoServicio.put("velocidad", normalizeValue(state, PepperConf.SPEED));
            infoServicio.put("velHabla", normalizeValue(state, PepperConf.TALKSPEED));
            infoServicio.put("tonoHabla", normalizeValue(state, PepperConf.PITCH));
            infoServicio.put("ledIntens", normalizeValue(state, PepperConf.LEDINTENSITY));
            infoServicio.put("COLOR", leds.getHexa());
            infoServicio.put("EmotionalTag", leds.toString());
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(RobotStateServiceRequestType.ROBOTEMOTION, infoServicio);
            requestService(srb);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BEstadoRobot.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void updateEmotionalVariables(){
        processEmotionalEvent(new EmotionalEvent());
    }

    private float normalizeValue(float val, PepperConf conf) {
        float normalValue,max=conf.getMax(),min=conf.getMin(),oldRange,newRange;
        oldRange = 2;
        newRange = max-min;
        normalValue= (((val-min)*newRange)/(oldRange))+ min;
        return normalValue;
    }
}
