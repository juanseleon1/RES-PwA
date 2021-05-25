/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI;

import ServiceAgentResPwA.ServiceDataRequest;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public interface ResPwAStrategy{
    
    public abstract void execStrategy(Believes b);
    
}
