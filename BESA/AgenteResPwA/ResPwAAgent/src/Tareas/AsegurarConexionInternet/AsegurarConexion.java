/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.AsegurarConexionInternet;

import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class AsegurarConexion extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();

    public AsegurarConexion() {
//        System.out.println("--- Task AsegurarConexion PwA Iniciada ---");
    }

    @Override
    public boolean checkFinish(Believes believes) {
        System.out.println("--- Check Finish AsegurarConexion ---");
        return false;
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task AsegurarConexion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        //enviar mirar conexion
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task AsegurarConexion ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task AsegurarConexion ---");
    }
    
}
