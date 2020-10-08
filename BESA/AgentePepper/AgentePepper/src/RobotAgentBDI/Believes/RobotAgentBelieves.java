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
    private BInteraccionSensores bInteraccionSensores = new BInteraccionSensores();
    private BNivelBateria bNivelBateria = new BNivelBateria();
    private BEstadoInactivo bEstadoInactivo = new BEstadoInactivo();
    private BInteraccionPwA bPersonaInteractuando = new BInteraccionPwA();
    
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
            case ACTIVIDAD:
                bEstadoActividad.update(si);
                break;
            case EMOCIONES:
                bEstadoEmocionalPwA.update(si);
                break;
            case INACTIVIDAD:
                bEstadoInactivo.update(si);
                break;
            case INTHABLA:
                bEstadoInteraccion.update(si);
                break;
            case INTSENSORES:
                bInteraccionSensores.update(si);
                break;
            case BATERIA:
                bNivelBateria.update(si);
                break;
            case RETROALIM:
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

    public BInteraccionSensores getbInteraccionSensores() {
        return bInteraccionSensores;
    }

    public void setbInteraccionSensores(BInteraccionSensores bInteraccionSensores) {
        this.bInteraccionSensores = bInteraccionSensores;
    }

    public BNivelBateria getbNivelBateria() {
        return bNivelBateria;
    }

    public void setbNivelBateria(BNivelBateria bNivelBateria) {
        this.bNivelBateria = bNivelBateria;
    }

    public BEstadoInactivo getbEstadoInactivo() {
        return bEstadoInactivo;
    }

    public void setbEstadoInactivo(BEstadoInactivo bEstadoInactivo) {
        this.bEstadoInactivo = bEstadoInactivo;
    }

    public BInteraccionPwA getbPersonaInteractuando() {
        return bPersonaInteractuando;
    }

    public void setbPersonaInteractuando(BInteraccionPwA bPersonaInteractuando) {
        this.bPersonaInteractuando = bPersonaInteractuando;
    }
        
        
    
    
}
