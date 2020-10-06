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
    
    private BEstadoEmocionalPwA bEstadoEmocionalPwA = new BEstadoEmocionalPwA();
    private BEstadoActividad bEstadoActividad = new BEstadoActividad();
    private BPerfilPwA bPerfilPwA = new BPerfilPwA();
    private BInteraccionSensores bInteraccionSensores = new BInteraccionSensores();
    private BNivelBateria bNivelBateria = new BNivelBateria();
    private BEstadoInactivo bEstadoInactivo = new BEstadoInactivo();
    private BInteraccionPwA bPersonaInteractuando = new BInteraccionPwA();
    
    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
   @Override
    public boolean update(InfoData si) {
        SensorData infoRecibida= (SensorData)si;
        switch (infoRecibida.getDataType()) {
            case ACTIVIDAD:
                
                break;
            case EMOCIONES:
                break;
            case INACTIVIDAD:
                break;
            case INTHABLA:
                break;
            case INTSENSORES:
                break;
            case BATERIA:
                break;
            case RETROALIM:
                break;
            default:
                break;
        }
        return true;
    }
    
    public void inicializarPerfil() {
        
    }
    
    
}
