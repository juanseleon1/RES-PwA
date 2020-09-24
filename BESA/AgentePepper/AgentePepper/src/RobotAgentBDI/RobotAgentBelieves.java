/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBelieves implements Believes{   
    
    private BEstadoEmocionalPwA bEstadoEmocionalPwA = new BEstadoEmocionalPwA();
    private BEstadoActividad bEstadoActividad = new BEstadoActividad();
    private BSelectorRolAgentePepper bSelectorRolAgentePepper = new BSelectorRolAgentePepper();
    private BPerfilPwA bPerfilPwA = new BPerfilPwA();
    private BInteraccionSensores bInteraccionSensores = new BInteraccionSensores();
    private BNivelBateria bNivelBateria = new BNivelBateria();
    private BEstadoInactivo bEstadoInactivo = new BEstadoInactivo();
    private BInteraccionPwA bPersonaInteractuando = new BInteraccionPwA();
    
   @Override
    public boolean update(InfoData si) {
        throw new UnsupportedOperationException(" "); //To change body of generated methods, choose Tools | Templates.
    }
}
