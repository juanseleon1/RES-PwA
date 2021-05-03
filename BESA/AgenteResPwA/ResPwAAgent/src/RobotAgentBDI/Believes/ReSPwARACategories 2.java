/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

/**
 *
 * @author juans
 */
public enum ReSPwARACategories {

    NO(-0.1),NEUTRO(0),SI(0.1);
    private double cambio;    
    private ReSPwARACategories(double i)
    {
        cambio=i;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }
    
    
}
