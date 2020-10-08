/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BInteraccionSensores implements Believes{

    @Override
    public boolean update(InfoData si) {
        System.out.println("BInteraccionPwA update Received: "+si);
        return true;
    }
    
}
