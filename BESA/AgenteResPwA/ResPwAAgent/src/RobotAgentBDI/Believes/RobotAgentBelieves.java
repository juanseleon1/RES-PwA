/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import ResPwaUtils.FBaseUtils;
import ResPwaUtils.Imagen;
import SensorHandlerAgent.SensorData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBelieves implements Believes{   
    private BEstadoInteraccion bEstadoInteraccion = new BEstadoInteraccion();
    private BEstadoEmocionalPwA bEstadoEmocionalPwA = new BEstadoEmocionalPwA();
    private BEstadoActividad bEstadoActividad = new BEstadoActividad();
    private BPerfilPwA bPerfilPwA = new BPerfilPwA();
    private BEstadoRobot bEstadoRobot = new BEstadoRobot();
    private Map<String,List<String>> imgCuentos;
    private List<Imagen> imgsPerfil;
   
    public RobotAgentBelieves(String cedula)
    {
        imgCuentos=new HashMap<>();
        //getPerfilBD(cedula);
        FBaseUtils.initResPwa(this);
        imgsPerfil=new ArrayList<>();
    }    

    
    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
   @Override
    public boolean update(InfoData si) {
        SensorData infoRecibida= (SensorData)si;
        System.out.println("RobotAgentBelieves update Received: "+si);
        if(si!=null){
        switch (infoRecibida.getDataType()) {
            case ACTIVITY:
                bEstadoActividad.update(si);
                break;
            case EMOTIONS:
                bEstadoEmocionalPwA.update(si);
                break;
            case INTERACTION:
                bEstadoInteraccion.update(si);
                break;
            case PROFILE:
                bPerfilPwA.update(si);
                actualizarPerfilEnDB();
                break;
            default:
                break;
        }
        return true;
    }
        return true;
    }
    
    private void actualizarPerfilEnDB() {
        try {
            //conectarConBD
            bPerfilPwA.updateToDB();
        } catch (Exception ex) {
            Logger.getLogger(RobotAgentBelieves.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Map<String,List<String>> getImgCuentos() {
        return imgCuentos;
    }

    public void setImgCuentos(Map<String,List<String>> imgCuentos) {
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
        
}
