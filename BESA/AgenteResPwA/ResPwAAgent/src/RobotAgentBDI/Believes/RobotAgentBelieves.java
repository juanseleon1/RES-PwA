/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionalData;
import ResPwaUtils.Imagen;
import SensorHandlerAgent.SensorData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBelieves implements Believes {

    private BEstadoInteraccion bEstadoInteraccion;
    private BEstadoEmocionalPwA bEstadoEmocionalPwA;
    private BEstadoActividad bEstadoActividad;
    private BPerfilPwA bPerfilPwA;
    private BEstadoRobot bEstadoRobot;
    private Map<String, List<String>> imgCuentos; //nomCuento //Lista de Strings -> url
    private List<Imagen> imgsPerfil;
    private BEstadoEmocionalRobot bEstadoEmocionalRobot;

    public RobotAgentBelieves(String cedula) {
        bEstadoEmocionalPwA = new BEstadoEmocionalPwA();
        bEstadoInteraccion = new BEstadoInteraccion();
        bEstadoRobot = new BEstadoRobot();
        bEstadoEmocionalRobot = new BEstadoEmocionalRobot();
        imgCuentos = new HashMap<>();
        imgsPerfil = new ArrayList<>();
        bEstadoActividad = new BEstadoActividad(cedula, this);
        bPerfilPwA = new BPerfilPwA(this);
//        getPerfilBD(cedula);
//        FBaseUtils.initResPwa(this);
    }

    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
    @Override
    public boolean update(InfoData si) {
        if(si!=null && si.getMessage() != null && si.getMessage().equals("emodata")) {
            EmotionalData se=(EmotionalData)si;
            System.out.println("RobotAgentBelieves update Received: "+se.getInfo() );
            bEstadoRobot.update(si);
            bEstadoEmocionalPwA.update(si);
        } else if(si!=null){
            SensorData infoRecibida = (SensorData) si;
            System.out.println("RobotAgentBelieves update Received: "+infoRecibida.getDataP() );
            switch (infoRecibida.getDataType()) {
                case ACTIVITY:
                    bEstadoActividad.update(si);
                    break;
                case INTERACTION:
                    bEstadoInteraccion.update(si);
                    break;
                case ROBOT:
                    bEstadoRobot.update(si);
                    break;
                case PROFILE:
                    bPerfilPwA.update(si);
                    break;
                default:
                    break;
            }
            return true;
        }

        return true;
    }

    private void getPerfilBD(String cedula) {
        //conectarConBD
        bPerfilPwA.getFromDB(cedula);
    }

    public BEstadoInteraccion getbEstadoInteraccion() {
        return bEstadoInteraccion;
    }

    public void setbEstadoInteraccion(BEstadoInteraccion bEstadoInteraccion) {
        this.bEstadoInteraccion = bEstadoInteraccion;
    }

    public BEstadoEmocionalPwA getbEstadoEmocionalPwA() {
        return bEstadoEmocionalPwA;
    }

    public void setbEstadoEmocionalPwA(BEstadoEmocionalPwA bEstadoEmocionalPwA) {
        this.bEstadoEmocionalPwA = bEstadoEmocionalPwA;
    }

    public BEstadoActividad getbEstadoActividad() {
        return bEstadoActividad;
    }

    public void setbEstadoActividad(BEstadoActividad bEstadoActividad) {
        this.bEstadoActividad = bEstadoActividad;
    }

    public BPerfilPwA getbPerfilPwA() {
        return bPerfilPwA;
    }

    public void setbPerfilPwA(BPerfilPwA bPerfilPwA) {
        this.bPerfilPwA = bPerfilPwA;
    }

    public BEstadoRobot getbEstadoRobot() {
        return bEstadoRobot;
    }

    public void setbEstadoRobot(BEstadoRobot bEstadoRobot) {
        this.bEstadoRobot = bEstadoRobot;
    }

    public Map<String, List<String>> getImgCuentos() {
        return imgCuentos;
    }

    public void setImgCuentos(Map<String, List<String>> imgCuentos) {
        this.imgCuentos = imgCuentos;
    }

    public List<Imagen> getImgsPerfil() {
        return imgsPerfil;
    }

    public void setImgsPerfil(List<Imagen> imgsPerfil) {
        this.imgsPerfil = imgsPerfil;
    }

    public List<Imagen> getImagexTag(String tag) {
        List<Imagen> imagenes = new ArrayList<>();

        for (Imagen i : imgsPerfil) {
            for (String t : i.getTags()) {
                if (t.equalsIgnoreCase(tag)) {
                    imagenes.add(i);
                }
            }
        }
        return imagenes;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }    

    public BEstadoEmocionalRobot getbEstadoEmocionalRobot() {
        return bEstadoEmocionalRobot;
    }

    public void setbEstadoEmocionalRobot(BEstadoEmocionalRobot bEstadoEmocionalRobot) {
        this.bEstadoEmocionalRobot = bEstadoEmocionalRobot;
    }
    
    
    
}
