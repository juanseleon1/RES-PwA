/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import SensorHandlerAgent.SensorData;
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

    public RobotAgentBelieves()
    {
     getPerfilBD();
    }    

    
    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
   @Override
    public boolean update(InfoData si) {
        SensorData infoRecibida= (SensorData)si;
        System.out.println("RobotAgentBelieves update Received: "+si);
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
    
    private void actualizarPerfilEnDB() {
        //conectarConBD
        bPerfilPwA.updateToDB();
    }
    
        private void getPerfilBD() {
        //conectarConBD
        bPerfilPwA.getFromDB();
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
    
    
        
       
}
